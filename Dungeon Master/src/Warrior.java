public class Warrior extends EnemyDecorator{

    public Warrior(Enemy e) {
        super(e, e.getName(), e.getMaxHp() + 2);
    }

    public String attack(Entity e) {
        //attack method for type of enemy
        System.out.println(super.attack(e));
        // randomly set damage to a value between 1 and 3
        int damage = (int) (Math.random() * 3) + 1;
        // deal the damage to the Hero
        e.takeDamage(damage);
        // put the int value into a String to be returned
        //attack for warrior decoration
        return "dealt " + String.valueOf(damage) + " to " + e.getName();
    }

    public String getName() {
        return super.getName() + " Warrior";
    }
}
