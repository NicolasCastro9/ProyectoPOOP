
import java.util.*;
/**
 * Clase que crea la ruta
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Route
{
    public Lineas ruta = new Lineas();
    public String color;
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
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     */
    public Route (String locationA, int x1, int y1, String locationB, int x2, int y2)
    {
        this.colorInicio = locationA;
        this.colorFinal = locationB;
        this.posicionInicioX = x1;
        this.posicionInicioY = y1;
        this.posicionFinalX = x1;
        this.posicionFinalY = y1;
        intersecciones = new Intersection[2];
        intersecciones[0] = new Intersection(locationA,x1,y1);
        intersecciones[1] = new Intersection(locationB,x2,y2);
        ruta.changePosition(intersecciones[0].x+25,intersecciones[0].y+25,intersecciones[1].x+25,intersecciones[1].y+25);
        isVisible = false;
    }
    /**
     * Metodo que hace visible a la ruta
     */
    public void makeVisible(){
        ruta.makeVisible();
    }
    /**
     * Metodo que hace invisible a la ruta
     */
    public void makeInvisible(){
        ruta.makeInvisible();
    }
    /**
     *  Metodo que remueve la ruta
     */
    public void removeRoute(){
        ruta.erase();
    }
    /**
     * metodo que cambia el color de la ruta
     * @param newColor es el nuevo color de la ruta
     */
    public void changeColor(String newColor){
        ruta.changeColor(newColor);
        color = newColor;
    }
    /**
     * Metodo que verifica si una ruta admite señales
     * @return retorna si la ruta admite señales
     */
    public boolean ponerSeñales(){
        return true;
    }
    /**
     * Metodo que verifica si una ruta se puede eliminar
     */
    public boolean quitarRuta(){
        return true;
    }
}
