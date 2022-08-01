import java.util.Random;

public class Warlock extends EnemyDecorator implements Magical {
    public Warlock(Enemy e) {
        super(e, e.getName(), e.getMaxHp() + 1);
    }

    public String attack(Entity e) {
        System.out.println(super.attack(e));
        String damage;
        Random rand = new Random();
        int spellSelection = rand.nextInt(3) + 1;
        if (spellSelection == 1)
        {
            damage = magicMissile(e);
            return "used Magic Missle attack and dealt " + damage;
        }
        else if (spellSelection == 2)
        {
            damage = fireball(e);
            return "used Fireball attack and dealt " + damage;
        }
        else
        {
            damage = thunderclap(e);
            return "used Thunderclap attack and dealt " + damage;
        }
    }

    @Override
    public String magicMissile(Entity e) {
        int damage = (int) (Math.random() * 3); //0,1,2
        e.takeDamage(damage);
        return String.valueOf(damage);
    }

    @Override
    public String fireball(Entity e) {
        int damage = (int) (Math.random() * 4);
        e.takeDamage(damage);
        return String.valueOf(damage);
    }

    @Override
    public String thunderclap(Entity e) {
        int damage = (int) (Math.random() * 3) + 1;
        e.takeDamage(damage);
        return String.valueOf(damage);
    }

    public String getName() {
        return super.getName() + " Warlock";
    }
}
