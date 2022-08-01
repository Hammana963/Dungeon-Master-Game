public class Froglok extends Enemy{
    /**
     * constructor that creates an enemy that was randomly generated with a name and max hp,
     * using super() to call entity class
     */
    public Froglok() {
        super("Froglok", 3);
    }
    @Override
    public String attack(Entity h) {
        // randomly set damage to a value between 1 and 3
        int damage = (int) (Math.random() * 3) + 1; //1-3 damage
        // deal the damage to the Hero
        h.takeDamage(damage);
        // put the int value into a String to be returned
        String d = "dealt " + String.valueOf(damage) + " to " + h.getName();
        return d;
    }
}
