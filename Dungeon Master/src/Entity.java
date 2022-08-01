/**
 *  Entity.java = Abstract class that creates an entity (hero or enemy) that has a name, hp,
 *  maxHp, and participate in battle
 */
public abstract class Entity {
    //initialize variables
    private String name;
    private int maxHp;
    private int hp;

    /** constructor that creates some entity with a name, and max hp
     *  @param n string of the entity's name
     *  @param mHp int of entity's max hp
     */
    public Entity(String n, int mHp) {
        name = n;
        maxHp = mHp;
        hp = maxHp;
    }

    /** astract method attack decleration
     *  @param e some entity that is being attacked
     *  @return string of some entity attacking some entity
     */
    public abstract String attack(Entity e);

    /** getter that gets name of the entity
     *  @return string of entity's name
     */
    public String getName() {
        return name;
    }

    /** getter that gets hp of the entity
     *  @return int of entity's hp
     */
    public int getHp() {
        return hp;
    }

    /** getter that gets max hp of the entity
     *  @return int of entity's max hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /** allows the entity to heal hp
     *  @param h the int amount of health being healed
     */
    public void heal(int h) {
        hp = hp + h;
        if(hp > maxHp) {
            hp = maxHp;
        }
    }

    /** takes damage from the entity's hp
     *  @param d int amount of damage being taken from hp
     */
    public void takeDamage(int d) {
        hp = hp - d;
        if(hp < 0) {
            hp = 0;
        }
    }

    /** prints the entity's name, hp, and max hp
     *  @Override overrides the default toString() method
     *  @return string of the entity's properties
     */
    @Override
    public String toString() {
        return("Name: " + name + "\nHP: " + hp + "/" + maxHp);
    }

}