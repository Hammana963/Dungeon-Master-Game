/*
Names: Joseph Hammana, Adam Kramer, Jackson Cashman
Date:
Description: Dungeon Master Project
*/

/*
Bugs:
1. running from a monster onto a tile that isnt 'n' removes it without activiating it
*/

import java.util.*;

class Main {

    public static void store(Hero h)
    {
        boolean leaveShop = false;

        System.out.println("Welcome to the store.");
        while(!leaveShop)
        {
            System.out.println("Gold: " + h.getGold());
            System.out.println("Health: " + h.getHp() + "/" + h.getMaxHp());
            System.out.println("1. Health Potion (25 Gold) \n2. Key (50 Gold) \n3. Leave store");
            int storeSelection = CheckInput.getIntRange(1,3);
            if(storeSelection == 1)
            {
                if(h.getGold() < 25){
                    System.out.println("You do not have enough gold to buy a health potion.");
                }
                else{
                    System.out.println("You have bought a health potion.");
                    h.heal(5);
                    h.spendGold(25);
                }
            }
            else if(storeSelection == 2)
            {
                if(h.getGold() < 50){
                    System.out.println("You do not have enough gold to buy a key.");
                }
                else{
                    System.out.println("You have bought a key.");
                    h.pickUpKey();
                    h.spendGold(50);
                }
            }
            else
            {
                System.out.println("Good luck on your travels.");
                leaveShop = true;
            }
        }
    }

    /** sequence for when hero encounters a room with a monster, allows hero to fight or run away
     *  @param h hero object
     *  @param e enemy object
     *  @return boolean true if hero is still alive, false if hero is dead
     */
    public static boolean monsterRoom(Hero h, Enemy e)
    { // print the stats of the monster
        boolean heroAlive = true;
        System.out.println("A " + e.getName() + " blocks your path!");
        Map map = Map.getInstance();

        while((e.getHp() > 0) && (h.getHp() > 0)) {
            System.out.println(h.getName()+ ": " + h.getHp() + "/" + h.getMaxHp());
            System.out.println(e.getName() + ": " + e.getHp() + "/" + e.getMaxHp());
            System.out.println("1. Fight");
            System.out.println("2. Run Away");
            int fightOptions = CheckInput.getIntRange(1,2);
            if (fightOptions == 1)
            {
                heroAlive = fight(h, e);//die in main
                if (heroAlive == false)
                {
                    return false;
                }
            }
            else
            {
                int runDirection = (int) (Math.random() * 4);
                if(runDirection == 0)
                {
                    h.goNorth();
                }
                else if(runDirection == 1)
                {
                    h.goSouth();
                }
                else if(runDirection == 2)
                {
                    h.goEast();
                }
                else
                {
                    h.goWest();
                }
                break;
            } // else
        } // while
        return true;
    } // monsterRoom

    /** sequence for hero fighting a monster
     *  @param h hero object
     *  @param e enemy object
     *  @return boolean if hero is alive true, false if hero is dead
     */
    public static boolean fight(Hero h, Enemy e)
    {
        Random rand = new Random();
        System.out.println(h.attack(e));
        if(e.getHp() < 1)
        {
            System.out.println("You defeated the " + e.getName());
            int goldFromEnemy = rand.nextInt(8) + 3;
            System.out.println("You have collected " + goldFromEnemy + " gold from the " + e.getName() + ".");
            h.collectGold(goldFromEnemy);
            Map.getInstance().reveal(h.getLoc());
            Map.getInstance().removeCharAtLoc(h.getLoc());
            return true;
        }
        // enemy attacks hero
        System.out.print(e.getName() + " ");
        System.out.println(e.attack(h));

        if(h.getHp() == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        // a. prompt the user to enter a name, then construct a Hero with that name
        Map map = Map.getInstance();
        Scanner in = new Scanner(System.in);
        boolean heroAlive = true;
        EnemyGenerator gen = new EnemyGenerator();

        System.out.print("What is your name, traveler? ");
        String player = in.nextLine();
        Hero h = new Hero(player);

        // b. display the hero with the map and have the user choose a direction
        while (h.getHp() >= 1)
        {
            //calls toString method from Hero
            System.out.println(h);

            // choose direction
            System.out.println("1. Go North");
            System.out.println("2. Go South");
            System.out.println("3. Go East");
            System.out.println("4. Go West");
            System.out.println("5. Quit");
            char c;

            // c. get the resulting character from the hero’s direction methods
            int menuChoice = CheckInput.getIntRange(1,5);
            if(menuChoice == 1)
            {
                c = h.goNorth();
                while (c == 'm') {
                    map.reveal(h.getLoc());
                    Enemy e = gen.generateEnemy(h.getLevel());
                    heroAlive = monsterRoom(h, e);
                    c = map.getCharAtLoc(h.getLoc());

                    if (heroAlive == false)
                    {
                        System.out.println(h.getName() + " has died. \nGame over.");
                        System.exit(0);
                    }
                }
                if (c == 'i') {
                    Random rand = new Random();
                    int keyOrHealth = rand.nextInt(2);
                    if(keyOrHealth == 0){ //health potion
                        System.out.println("You found a Health Potion! You drink it to restore your health.");
                        h.heal(5);
                    }
                    else if (keyOrHealth == 1){ //key
                        System.out.println("You found a Key!");
                        h.pickUpKey();
                    }
                }
                else if(c == 's') { // shop
                    store(h);
                }
                if (c == 'f') { // if hero has a key, advance to next level. if no key, restart while loop
                    if(h.hasKey() == true){
                        h.useKey();
                        map.reveal(h.getLoc());
                        map.removeCharAtLoc(h.getLoc());
                        h.levelup();
                    }
                    else{

                        map.reveal(h.getLoc());
                        continue;
                    }
                }
                map.reveal(h.getLoc());
                map.removeCharAtLoc(h.getLoc());

            }
            else if(menuChoice == 2) {
                c = h.goSouth();
                while (c == 'm') {
                    map.reveal(h.getLoc());
                    Enemy e = gen.generateEnemy(h.getLevel());
                    heroAlive = monsterRoom(h, e);
                    c = map.getCharAtLoc(h.getLoc());
                    if (heroAlive == false)
                    {
                        System.out.println(h.getName() + " has died. \nGame over.");
                        System.exit(0);
                    }
                }
                if (c == 'i') {
                    Random rand = new Random();
                    int keyOrHealth = rand.nextInt(2);
                    if(keyOrHealth == 0){ //health potion
                        System.out.println("You found a Health Potion! You drink it to restore your health.");
                        h.heal(5);
                    }
                    else if (keyOrHealth == 1){ //key
                        System.out.println("You found a Key!");
                        h.pickUpKey();
                    }
                    map.reveal(h.getLoc());
                    map.removeCharAtLoc(h.getLoc());
                }
                else if(c == 's') { // shop
                    store(h);
                }
                else if(c == 'f') { // if hero has a key, advance to next level. if no key, restart while loop
                    if(h.hasKey() == true){
                        h.useKey();
                        map.reveal(h.getLoc());
                        map.removeCharAtLoc(h.getLoc());
                        h.levelup();
                    }
                    else{

                        map.reveal(h.getLoc());
                        continue;
                    }
                }
                map.reveal(h.getLoc());
                map.removeCharAtLoc(h.getLoc());

            }
            else if(menuChoice == 3) {
                c = h.goEast();
                while (c == 'm') {
                    map.reveal(h.getLoc());
                    Enemy e = gen.generateEnemy(h.getLevel());
                    heroAlive = monsterRoom(h, e);
                    c = map.getCharAtLoc(h.getLoc());
                    if (heroAlive == false)
                    {
                        System.out.println(h.getName() + " has died. \nGame over.");
                        System.exit(0);
                    }
                }
                if (c == 'i') {
                    Random rand = new Random();
                    int keyOrHealth = rand.nextInt(2);
                    if(keyOrHealth == 0){ //health potion
                        System.out.println("You found a Health Potion! You drink it to restore your health.");
                        h.heal(5);
                    }
                    else if (keyOrHealth == 1){ //key
                        System.out.println("You found a Key!");
                        h.pickUpKey();
                    }
                }
                else if(c == 's') { // shop
                    store(h);
                }
                else if(c == 'f') { // if hero has a key, advance to next level. if no key, restart while loop
                    if(h.hasKey() == true){
                        h.useKey();
                        map.reveal(h.getLoc());
                        map.removeCharAtLoc(h.getLoc());
                        h.levelup();
                    }
                    else{

                        map.reveal(h.getLoc());
                        continue;
                    }
                }
                map.reveal(h.getLoc());
                map.removeCharAtLoc(h.getLoc());

            }
            else if(menuChoice == 4) {
                c = h.goWest();
                while (c == 'm') {
                    map.reveal(h.getLoc());
                    Enemy e = gen.generateEnemy(h.getLevel());
                    heroAlive = monsterRoom(h, e);
                    c = map.getCharAtLoc(h.getLoc());
                    if (heroAlive == false)

                    {
                        System.out.println(h.getName() + " has died. \nGame over.");
                        System.exit(0);
                    }
                }
                if (c == 'i') {
                    Random rand = new Random();
                    int keyOrHealth = rand.nextInt(2);
                    if(keyOrHealth == 0){ //health potion
                        System.out.println("You found a Health Potion! You drink it to restore your health.");
                        h.heal(5);
                    }
                    else if (keyOrHealth == 1){ //key
                        System.out.println("You found a Key!");
                        h.pickUpKey();
                    }
                }
                else if(c == 's') { // shop
                    store(h);
                }
                else if(c == 'f') { // if hero has a key, advance to next level. if no key, restart while loop
                    if(h.hasKey() == true){
                        h.useKey();
                        map.reveal(h.getLoc());
                        map.removeCharAtLoc(h.getLoc());
                        h.levelup();
                    }
                    else{

                        map.reveal(h.getLoc());
                        continue;
                    }
                }
                map.reveal(h.getLoc());
                map.removeCharAtLoc(h.getLoc());

            }
            else { // if the uses selects "Quit"
                System.exit(0);
            } // else
        }//while
    }// main
}  // Main