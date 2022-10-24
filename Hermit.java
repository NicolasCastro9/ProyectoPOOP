import java.util.*;

/**
 * Write a description of class Hermit here.
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  4.0 (22/10/2022)
 */
public class Hermit extends Intersection
{
    public int routeNumber;
    private String color;
    private  String[][] r; 
    /**
     * Constructor for objects of class Hermit, esta subclase crea intersecciones que solo permiten una ruta
     * @param color, es el color de la interseccion
     * @param x, es la ubicacion en x
     * @param y, es la ubicacion en y
     */
    public Hermit(String color, int x, int y)
    {
        super(color,x,y);
        this.color = color;
        inter.changeColor("black");
    }
    /**
     * Metodo que verifica si interseccion permite el ingreso de rutas
     * @param r es la matriz para la verificacion
     * @return retorna si esta interseccion admite rutas
     */
    @Override
    public boolean permiteRutas(String[][] r)
    {
        routeNumber = 0;
        for(int i = 0; i < r.length; i ++){
            if(r[i][0].contains(color)){
                routeNumber += 1;
            }
        }
        if(routeNumber < 1){
            return true;
        }else{
            return false;
        }
    }
    
}
