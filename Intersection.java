import java.util.*;  

public class Intersection
{
    public String color;
    public int x;
    public int y;
    private Circle inter;
    private boolean isVisible;
    /**
     * Constructor for objects of class Intersection
     */
    public Intersection(String colori, int xp, int yp)
    {
        color = colori;
        x = xp;
        y = yp;
        isVisible = false;
    }
    private void drawIntersection(){
        if(isVisible){
            inter = new Circle();        
            inter.changeColor(color);
            inter.moveHorizontal(x);
            inter.moveVertical(y);
        }
    }
    private void removeIntersection(){
        inter.erase();
    }
    public void makeVisible(){
        isVisible = true;
        drawIntersection();
        inter.makeVisible();
    }
    public void makeInvisible(){
        removeIntersection();
        isVisible = false;
    }
    public String getIntersection(){
        return color;
    }
}
