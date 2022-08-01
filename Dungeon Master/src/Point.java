/**
 *  Point.java = creates a point that keep track of the player's position and what's on the map
 */
public class Point
{
    //initilize point coordinates
    public int x;
    public int y;

    /** constructor that creates a x and y position on map when object is created
     *  @param x x-coordiante of the point on the map
     *  @param y y-coordiante of the point on the map
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

}