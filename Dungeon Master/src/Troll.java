public class Troll extends Enemy{
    /**
     * constructor that creates an enemy that was randomly generated with a name and max hp,
     * using super() to call entity class
     */
    public Troll() {
        super("Troll", 5);
    }
    @Override
    public String attack(Entity h) {
        // randomly set damage to a value between 0 and 5
        int damage = (int) (Math.random() * 6); //0-5 damage
        // deal the damage to the Hero
        h.takeDamage(damage);
        // put the int value into a String to be returned
        String d = "dealt " + String.valueOf(damage) + " to " + h.getName();
        return d;
    }
}
