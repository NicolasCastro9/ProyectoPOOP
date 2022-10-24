
/**
 * Clase que crea el Sign
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Sign
{
   public Triangle signal = new Triangle();
    public String color;
    public String colorInicio;
    public String colorFinal;
    public int posicionInicioX;
    public int posicionInicioY;
    public int posicionFinalX;
    public int posicionFinalY;    
    public int speedlimit;
    private boolean isVisible;
    private boolean isWrong;
    private boolean isUnNecessary;
    /**
     * Constructor for objects of class Sign
     * @param locationA es el color de la interseccion que inicia la ruta
     * @param x1 posicion en x de la interseccion inicio
     * @param y1 posicion en y de la interseccion inicio
     * @param locationB es el color de la interseccion que finaliza la ruta
     * @param x2 posicion en x de la interseccion final
     * @param y2 posicion en y de la interseccion final
     * @param speedlimit1 es la velocidad en la señal
     */
    public Sign(String locationA, int x1, int y1, String locationB, int x2, int y2, int speedlimit1){
        
        this.speedlimit = speedlimit1;
        isVisible = false;
        this.colorInicio = locationA;
        this.posicionInicioX = x1;
        this.posicionInicioY = y1;
        this.colorFinal = locationB;
        this.posicionFinalX = x2;
        this.posicionFinalY = y2;
        isWrong = false;
        isUnNecessary = false;
        int puntox = ((x1 + 25) + (x2 +25)) / 2;
        int puntoy = ((y1+ 25) + (y2 + 25)) / 2;
        int puntoxx = (x1 + puntox) / 2;
        int puntoyy = (y1 + puntoy) / 2;
        signal.moveHorizontal(puntoxx);
        signal.moveVertical(puntoyy);
    }
    /**
     * Metodo que hace visible el objeto Sign en el canvas.
     */
    public void makeVisible(){
        signal.makeVisible();
    }
    /**
     * Metodo que hace invisible el objeto Sign en el canvas.
     */
    public void makeInvisible(){
        signal.makeInvisible();
    }
    /**
     * Metodo que cambia el color de la señal
     * @param newColor es el nuevo color
     */
    public void changeColor(String newColor){
        signal.changeColor(newColor);
        color = newColor;
    }
    /**
     * Metodo que cambia el tamaño de la señal
     * @param newHeigth es la nueva longitud de la señal
     * @param newWidth es el nuevo ancho de la señal
     */
    public void changeSize(int newHeight, int newWidth){
        signal.changeSize(newHeight, newWidth);
    }
    /**
     * Metodo que remueve a la señal
     */
    public void remove(){
        signal.erase();
    }
    /**
     * Metodo que cambia el color de una señal normal dependiendo de su velocidad
     * @param speedlimit es el limite de velocidad de la seña
     */
    public void changeColorRange(int speedlimit){
        if(speedlimit >= 0 && speedlimit < 5){
            signal.changeColor("light_gray");
            color = "light_gray";
        }else if(speedlimit >= 5 && speedlimit < 10){
            signal.changeColor("gray");
            color = "gray";
        }else if(speedlimit >= 10 && speedlimit < 20){
            signal.changeColor("dark_gray");
            color = "dark_gray";
        }else{
            signal.changeColor("black");
            color = "black";
        }        
    }
    /**
     * Metodo que verifica si a la ruta se le puede cambiar el valor del limite de la velocidad una vez creada
     */
    public boolean changeRouteSpeedValue(){
        return false;
    }
}
