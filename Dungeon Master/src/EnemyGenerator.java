import java.io.*;
import java.util.*;
import java.util.Random;

/**
 *  EnemyGenerator.java = generates a random enemy from Enemies.txt and determines if that
 *  enemy is magical or not
 */
public class EnemyGenerator {

    public static Enemy generateEnemy(int level) {
        //return new Troll();
        //return new Warrior(new Troll());
        //return new Warrior(new Warrior(new Orc()));
        //return new Warlock(new Warlock(new Troll()));
        //return new Warrior(new Warrior(new Warrior(new Troll())));

        Enemy enemy = null;
        Random rand = new Random();
        int enemyType = rand.nextInt(4) + 1; //1,2,3,4
        int decorationType = rand.nextInt(2) + 1; // 1,2
        if (enemyType == 1) {
            // create base level troll
            enemy = new Troll();
            //for every level over 1, decorate troll
            while (level > 1) {
                if (decorationType == 1) {
                    enemy = new Warrior(enemy);
                }
                else {
                    enemy = new Warlock(enemy);
                }
                level -= 1;
            }

        }
        else if (enemyType == 2) {

            // create base level Goblin
            enemy = new Goblin();
            //for every level over 1, decorate Goblin
            while (level > 1) {
                if (decorationType == 1) {
                    enemy = new Warrior(enemy);
                }
                else {
                    enemy = new Warlock(enemy);
                }
                level -= 1;
            }


        }
        else if (enemyType == 3) {
            // create base level Orc
            enemy = new Orc();
            //for every level over 1, decorate Orc
            while (level > 1) {
                if (decorationType == 1) {
                    enemy = new Warrior(enemy);
                }
                else {
                    enemy = new Warlock(enemy);
                }
                level -= 1;
            }
        }

        else if (enemyType == 4) {
            // create base level Froglok
            enemy = new Froglok();
            //for every level over 1, decorate Froglok
            while (level > 1) {
                if (decorationType == 1) {
                    enemy = new Warrior(enemy);
                }
                else {
                    enemy = new Warlock(enemy);
                }
                level -= 1;
            }

        }
        return enemy;


    }
}

/*
while (level > 1) {
                enemy = new Warrior(enemy);
                level -= 1;
            }
 */

