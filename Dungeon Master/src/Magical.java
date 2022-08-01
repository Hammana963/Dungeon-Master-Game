/**
 *  Magical.java = interface class that holds methods for magical spells
 */
public interface Magical {
    //initialize magic_menu
    static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap";

    /** interface method magic attack for magic missle that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String magicMissile(Entity e);

    /** interface method magic attack for fireball that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String fireball(Entity e);

    /** interface method magic attack for thunderclap that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String thunderclap(Entity e);
}

