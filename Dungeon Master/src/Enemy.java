/**
 *  Enemy.java = extends the entity class allowing the user to generate an enemy with an attack
 */

public abstract class Enemy extends Entity { // define Enemy as a subclass of Entity

    /** constructor that creates an enemy that was randomly generated with a name and max hp,
     *  using super() to call entity class
     *  @param name string name of the enemy
     *  @param mHp int of enemy's max hp
     */
    public Enemy(String name, int mHp) {
        super(name, mHp);
    }


}
