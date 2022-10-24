 
/**
 * Write a description of class Needy here.
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  4.0 (22/10/2022)
 */
public class Needy extends Intersection
{
    public int routeNumber;
    private String color;
    private  String[][] r; 
    /**
     * Constructor for objects of class Needy, esta subclase crea intersecciones que se borran si no tienen rutas
     * @param color, es el color de la interseccion
     * @param x, es la ubicacion en x
     * @param y, es la ubicacion en y
     */
    public Needy(String color, int x, int y)
    {
       super(color,x,y);
       this.color = color;
       inter.changeColor("light_gray");
    }
    /**
     * metodo que verifica si la interseccion tiene la capacidad de autoeliminarse
     * @return, retorna si la interseccion se puede autoeliminar
     */
    @Override
    public boolean seAutoelimina(String[][] r)
    {
        routeNumber = 1;
        for(int i = 0; i < r.length; i ++){
            if(r[i][0].contains(color)){
                routeNumber += 1;
            }
        }
        routeNumber -=1;
        if(routeNumber == 0){
            return true;
        }else{
            return false;
        }
    }
}
