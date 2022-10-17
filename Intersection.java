import java.util.*;  

public class Intersection
{
    public String color;
    public int x;
    public int y;
    private Circle inter = new Circle();
    private boolean isVisible;
    /**
     * Constructor for objects of class Intersection
     */
    public Intersection(String color, int x, int y)
    {
        this.color = color;
        this.x = x;
        this.y = y;
        inter.changeColor(color);
        inter.moveHorizontal(x);
        inter.moveVertical(y);        
        isVisible = false;
    }
    public void makeVisible(){
        inter.makeVisible();
    }
    public void makeInvisible(){
        inter.makeInvisible();
    }
    public String getIntersection(){
        return color;
    }
    public void removeIntersection(){
        inter.erase();
    }
}
