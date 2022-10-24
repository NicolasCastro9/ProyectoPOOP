import java.util.HashMap;
import java.util.Collections; 
/**
 * Write a description of class Cautious here.
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Cautious extends Sign
{
    public int min;
    public int speedlimit1;
    /**
     * Constructor for objects of class Cautious, crea una señal que se ajusta a la velocidad minima de la ciudad
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     * @param speedlimit1 es la velocidad en la señal
     */
    public Cautious(String locationA, int x1, int y1, String locationB, int x2, int y2, int speedlimit1)
    {
        super(locationA, x1, y1, locationB, x2, y2, speedlimit1);
        this.changeColor("blue");
    }
    /**
     * metodo que define el limite de velocidad de la señal como el minimo
     */
    public void minRutas(int r){
        this.min = r;
    }
}
