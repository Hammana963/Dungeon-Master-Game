/**
 *  Hero.java = creates a hero that can navigate the map and battle monsters extending
 *  the abstract Entity class and implementing the Magical interface class
 */
public class Hero extends Entity implements Magical
{

    private Point loc;
    private int level = 1;
    private int gold = 0;
    private int key = 0;

    /** constructor that creates a hero with a name, loads the map and places the player on
     *  the starting location, and gives a max hp. Uses super() to call the entity constructor
     *  @param n string name of the hero
     */
    public Hero(String n)
    {
        super(n, 50);
        Map map = Map.getInstance();
        map.loadMap(level);
        loc = map.findStart();
        gold = 70;
    }

    /** magic attack for magic missle that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String magicMissile (Entity e)
    {
        int damage = (int) (Math.random() * 3) + 2;
        e.takeDamage(damage);
        return String.valueOf(damage);
    }

    /** magic attack for fireball that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String fireball (Entity e)
    {
        int damage = (int) (Math.random() * 3) + 3;
        e.takeDamage(damage);
        return String.valueOf(damage);
    }

    /** magic attack for thunderclap that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String thunderclap (Entity e)
    {
        int damage = (int) (Math.random() * 3) + 4;
        e.takeDamage(damage);
        return String.valueOf(damage);
    }

    /** attack method that allows hero to use physical attack or magical attack
     *  @Override overrides attack() method from abstract entity class
     *  @param e entity object
     *  @return string of hero doing damage to entity
     */
    @Override
    public String attack(Entity e)
    {
        String damage = "";
        System.out.println("Choose your attack method:");
        System.out.println("1. Physical Attack\n2. Magic Attack");

        int attackSelection = CheckInput.getIntRange(1,2);
        if (attackSelection == 1)
        {
            // randomly set damage to a value between 2 and 5
            int dam = (int) (Math.random() * 3) + 1;
            damage = String.valueOf(dam);
            // deal the damage to the Enemy
            e.takeDamage(dam);
        }
        else {
            System.out.println("1. Magic Missile\n2. Fireball\n3. Thunderclap");
            int magicSelection = CheckInput.getIntRange(1,3);
            if(magicSelection == 1) {
                damage = magicMissile(e);
            }
            else if (attackSelection == 2) {
                damage = fireball(e);
            }
            else {
                damage = thunderclap(e);
            }
        }
        return getName() + " dealt " + damage + " damage to " + e.getName();
    }

    /** allows hero to proceed to the next level
     */
    public void levelup() {
        level += 1;
        int mapIteration = level % 3;
        if(mapIteration == 0) {
            mapIteration = 3;
        }
        Map map = Map.getInstance();
        map.loadMap(mapIteration);
        System.out.println("You descended further into the dungeon.");
    }

    public int getLevel() {
        return level;
    }

    /** allows character to move north on the map
     *  @return char of the new location on the map the hero is in
     */
    public char goNorth()
    {
        loc.x -= 1;
        if (loc.x < 0) {
            System.out.println("You hit a wall!");
            loc.x += 1;
        }
        Map map = Map.getInstance();
        char room = map.getCharAtLoc(loc);
        if ((room == 'f') && !hasKey()) {
            System.out.println("You do not have a key.");
        }


        return room;
    }

    /** allows character to move south on the map
     *  @return char of the new location on the map the hero is in
     */
    public char goSouth()
    {
        loc.x += 1;
        if (loc.x > 4) {
            System.out.println("You hit a wall!");
            loc.x -= 1;
        }
        Map map = Map.getInstance();
        char room = map.getCharAtLoc(loc);
        if ((room == 'f') && !hasKey()) {
            System.out.println("You do not have a key.");
        }

        return room;
    }

    /** allows character to move east on the map
     *  @return char of the new location on the map the hero is in
     */
    public char goEast()
    {
        loc.y += 1;
        if (loc.y > 4) {
            System.out.println("You hit a wall!");
            loc.y -= 1;
        }
        Map map = Map.getInstance();
        char room = map.getCharAtLoc(loc);
        if ((room == 'f') && !hasKey()) {
            System.out.println("You do not have a key.");
        }

        return room;
    }

    /** allows character to move west on the map
     *  @return char of the new location on the map the hero is in
     */
    public char goWest()
    {
        loc.y -= 1;
        if (loc.y < 0) {
            System.out.println("You hit a wall!");
            loc.y += 1;
        }
        Map map = Map.getInstance();
        char room = map.getCharAtLoc(loc);
        if ((room == 'f') && !hasKey()) {
            System.out.println("You do not have a key.");
        }
        return room;
    }

    public Point getLoc() {
        return loc;
    }

    public int getGold()
    {
        return gold;
    }

    public void collectGold(int g)
    {
        //g equals to the random amount of gold collected after killing enemy
        gold += g;
    }

    public void spendGold(int g)
    {
        //g equals to the amount of gold spent at the shop (25 or 50)
        gold -= g;
    }

    public boolean hasKey()
    {
        if (key > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void pickUpKey()
    {
        key += 1;
    }

    public boolean useKey()
    {
        if(hasKey())
        {
            key -= 1;
            System.out.println("You have used a key.");
            return true;
        }
        else
        {
            return false;
        }
    }

    /** prints the name, hp, max hp, level, and location of hero on the map
     *  @Override toString() method from entity class
     *  @return string value of the hero's properties
     */
    @Override
    public String toString() {
        Map map = Map.getInstance();
        return getName() + "\nHP: " + getHp() + "/" + getMaxHp() + "\nLevel: " + level + "\n" + map.mapToString(loc);
    }
}