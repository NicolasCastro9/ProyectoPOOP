import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.*;
import java.awt.geom.*;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections; 
/**
 * Clase que crea la ruta
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Route
{
    private Lineas ruta;
    public String colorInicio;
    public int posicionInicioX;
    public int posicionInicioY;
    public String colorFinal;
    public int posicionFinalX;
    public int posicionFinalY;
    private Intersection[] intersecciones;
    private boolean isVisible;
    /**
     * Constructor for objects of class Road
     */
    public Route (String locationA, int x1, int y1, String locationB, int x2, int y2)
    {
        intersecciones = new Intersection[2];
        colorInicio = locationA;
        posicionInicioX = x1;
        posicionInicioY = y1;
        colorFinal = locationB;
        posicionFinalX = x2;
        posicionFinalY = y2;
        isVisible = false;
    }
    /**
     * Metodo que dibuja la ruta en el canvas
     */
    private void drawRoute(){
        if(isVisible){
            intersecciones[0] = new Intersection(colorInicio,posicionInicioX,posicionInicioY);
            intersecciones[1] = new Intersection(colorFinal,posicionFinalX,posicionFinalY);                   
            ruta = new Lineas();
            ruta.changePosition(intersecciones[0].x+25,intersecciones[0].y+25,intersecciones[1].x+25,intersecciones[1].y+25);
            ruta.makeVisible();
        }
    }
    /**
     * Metodo que borra la ruta en el canvas
     */
    private void removeRoute(){
        ruta.erase();
    }
    /**
     * Metodo que hace visible a la ruta
     */
    public void makeVisible(){
        isVisible = true;
        drawRoute();
    }
    /**
     * Metodo que hace invisible a la ruta
     */
    public void makeInvisible(){
        removeRoute();
        isVisible = false;
    }
}
