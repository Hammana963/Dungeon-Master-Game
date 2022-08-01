public abstract class EnemyDecorator extends Enemy{
    private Enemy enemy;

    public EnemyDecorator(Enemy e, String n, int h) {
        super(n, h);
        enemy = e;
    }

    //what does this do
    public String attack(Entity e) {
        // passes in type of enemy (Goblin, Troll, Orc, Froglok)
        //calls attack method for the type of enemy
        return enemy.attack(e);

    }

}
