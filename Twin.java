
/**
 *  
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Twin extends Sign
{
    public Sign gemelo; 
    /**
     * Constructor for objects of class Twin, esta señal crea una gemela al otro lado de la ruta
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     * @param speedlimit1 es la velocidad en la señal
     */
    public Twin(String locationA, int x1, int y1, String locationB, int x2, int y2, int speedlimit1)
    {
        super(locationA, x1, y1, locationB, x2, y2, speedlimit1);
        signal.changeColor("red");
        this.gemelo();
    }
    /**
     * Metodo que crea el gemelo de la señal twin al otro lado de la ruta
     */
    public void gemelo(){
        Sign signal2 = new Sign(colorFinal,posicionFinalX,posicionFinalY,colorInicio,posicionInicioX,posicionInicioY,speedlimit);
        signal2.changeColor("red");
        this.gemelo = signal2;
    }
}
