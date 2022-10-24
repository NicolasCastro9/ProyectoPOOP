
/**
 * Write a description of class Rebel here.
 *  
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Rebel extends Route
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Road, esta clase crea rutas que no aceptan señales
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     */
    public Rebel(String locationA, int x1, int y1, String locationB, int x2, int y2)
    {
        super(locationA,x1,y1,locationB,x2,y2);
        ruta.changeColor("red");
    }
    /**
     * Metodo que verifica si una ruta admite señales
     * @return retorna si la ruta admite señales
     */
    @Override
    public boolean ponerSeñales(){
        return false;
    }
}
