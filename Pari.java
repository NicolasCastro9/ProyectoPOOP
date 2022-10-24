
/**
 * Write a description of class Pari here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pari extends Sign
{

    /**
     * Constructor for objects of class Pari, señal que se crea dependiendo si su velocidad es par(es rosado y duplica su tamaño), o impar(es naranja y mantiene su tamaño estandar
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     * @param speedlimit1 es la velocidad en la señal
     */
    public Pari(String locationA, int x1, int y1, String locationB, int x2, int y2, int speedlimit1)
    {
        super(locationA, x1, y1, locationB, x2, y2, speedlimit1);
        if(speedlimit1 % 2 == 0){
            signal.changeColor("pink");
            signal.changeSize(signal.height * 2 , signal.width * 2);
        }else{
            signal.changeColor("orange");
        }
    }
}
