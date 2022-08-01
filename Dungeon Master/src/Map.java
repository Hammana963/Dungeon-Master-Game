import java.io.*;
import java.util.Scanner;

/**
 *  Map.java = creates a Map that the hero can uses to navigate the levels using the map#.txt
 *  files
 */
public class Map {
    // Defining Map as a singleton
    private static Map instance = null;

    private char[][] map = new char[5][5];
    private boolean[][] revealed = new boolean[5][5];

    /** default constructor
     */
    private Map() {
    }

    public static Map getInstance() {
        if(instance == null) {
            instance = new Map();
        }
        return instance;
   }

    /** loads the map that the hero is currently on depending on hero's levels
     *  @param mapNum the level of the hero which will determine which map file to open
     */
    public void loadMap(int mapNum) {
        String path = "";
        if (mapNum == 1) {
            path = "Map1.txt";
        }
        else if (mapNum == 2) {
            path = "Map2.txt";
        }
        else if (mapNum == 3) {
            path = "Map3.txt";
        }

        try {
            Scanner read = new Scanner(new File(path));
            String ch = "";

            while (read.hasNext()) {
                String line = read.next();
                //split text by each character into a string array
                String [] tokens = line.split(" ");
                //put each element from array into new string
                for (int k = 0; k < tokens.length; k++) {
                    ch += tokens[k];
                }
            }
            //put each element from string into char array
            int c = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = ch.charAt(c);
                    c++;
                }
            }
            read.close();
            for (int i = 0; i < map.length; ++i)
            {
                for (int j = 0; j < map[0].length; ++j)
                {
                    revealed[i][j] = false;
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File was not found");
        }
    }

    /** gets the character's location as a char
     *  @param p point object of where the player is on the map
     *  @return char of hero's location on map
     */
    public char getCharAtLoc(Point p) {
    /*if (map[p.x][p.y] == 'f')
    {
      for (int i = 0; i < map.length; ++i)
      {
        for (int j = 0; j < map[0].length; ++j)
        {
          revealed[i][j] = false;
        }
      }
    }*/
        if (map[p.x][p.y] == 's')
        {

            map[p.x][p.y] = 's';
        }

        return map[p.x][p.y];
    }

    /** magic attack for thunderclap that deals random damage within interval to entity
     *  @param e entity object
     *  @return string value of damage being applied to entity
     */
    public String mapToString(Point p) {
        //hero position is p
        String map_visual = "";

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                //Always show the start
                if(map[i][j] == 's') {
                    reveal(new Point(i,j));
                }
                if (revealed[i][j]) {
                    if (i == p.x && j == p.y) {
                        map_visual += '*' + " ";
                    }
                    else {
                        map_visual += map[i][j] + " ";
                    }
                }
                else if (i == p.x && j == p.y) {
                    map_visual += '*' + " ";
                }
                else {
                    map_visual += 'x' + " ";
                }
            }
            map_visual += "\n";
        }
        return map_visual;
    }

    /** finds the beginning of the level and places a point there
     *  @return point object at the start of the level
     */
    public Point findStart() {
        int x_cor = 0;
        int y_cor = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 's') {
                    x_cor = i;
                    y_cor = j;
                }
            }
        }
        return new Point(x_cor, y_cor);
    }

    /** reveal what is on the object at points where the hero has visited
     *  @param p point object
     */
    public void reveal(Point p) {
        revealed[p.x][p.y] = true;
    }

    /** removes what is in room after hero has visited said room
     *  @param p point that the hero previously visited
     */
    public void removeCharAtLoc(Point p) {
        if ((map[p.x][p.y] != 's') && (map[p.x][p.y] != 'f')) {
            map[p.x][p.y] = 'n';
        }
    }

}
