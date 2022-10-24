import java.util.*;  

public class Intersection
{
    public String color;
    public int x;
    public int y;
    public Circle inter = new Circle();
    private boolean isVisible;
    /**
     * Constructor for objects of class Intersection
     * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
     * @version  4.0 (22/10/2022)
     */
    public Intersection(String color, int x, int y)
    {
        this.color = color;
        this.x = x;
        this.y = y;
        inter.changeColor(color);
        inter.moveHorizontal(x);
        inter.moveVertical(y);        
        isVisible = false;
    }
    /**
     * Metodo que hace visible a la interseccion
     */
    public void makeVisible(){
        inter.makeVisible();
    }
    /**
     * metodo que hace invisible a la interseccion
     */
    public void makeInvisible(){
        inter.makeInvisible();
    }
    /**
     * metodo que retorna el identificador de la interseccion(el color)
     * @return color
     */
    public String getIntersection(){
        return color;
    }
    /**
     * metodo que elimina la interseccion
     */
    public void removeIntersection(){
        inter.erase();
    }
    /**
     * metodo que verifica si la interseccion admite rutas
     * @return retorna si la interseccion admite rutas
     */
    public boolean permiteRutas(String[][] r){
        return true;
    }
    /**
     * metodo que verifica si la interseccion tiene la capacidad de autoeliminarse
     * @return, retorna si la interseccion se puede autoeliminar
     */
    public boolean seAutoelimina(String[][] r){
        return false;
    }
}
