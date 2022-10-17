import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
/**
 * Write a description of class Lineas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lineas
{
    // instance variables - replace the example below with your own
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean isVisible;
    private String color;
    private static int length = 600;
    private static int width = 600;
    private static int g = 10;
    /**
     * Constructor for objects of class Lineas
     */
    public Lineas()
    {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = "blue";
        isVisible = false;
    }

    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(length, width);
            canvas.draw(this, color, new Line2D.Double(x1,y1,x2,y2));
        }
    }
     public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(length, width);
            canvas.erase(this);
        }        
    }
    public void changePosition(int nx1,int ny1, int nx2, int ny2){
        erase();
        x1 = nx1;
        y1 = ny1;
        x2 = nx2;
        y2 = ny2;
        draw();
    }
        public void changeColor(String newColor){
        color = newColor;
        draw();
    }
}
