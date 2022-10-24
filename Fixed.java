
/**
 * Write a description of class Fixed here.
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Fixed extends Route
{
    public boolean quitarSeñal;
    /**
     * Constructor for objects of class Fixed, esta clase crea una ruta que no se puede borrar
     * Constructor for objects of class Road
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     */
    public Fixed(String locationA, int x1, int y1, String locationB, int x2, int y2)
    {
        super(locationA,x1,y1,locationB,x2,y2);
        ruta.changeColor("orange");
        quitarSeñal = true;
    }
    
    @Override
    public boolean quitarRuta(){
        return false;
    }
}
