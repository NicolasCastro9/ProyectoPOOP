
/**
 * Clase que crea el Sign
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Sign
{
    public String colorInicio;
    public String colorFinal;
    public String color;
    private  int posicionInicioX;
    private  int posicionInicioY;
    private  int posicionFinalX;
    private  int posicionFinalY;    
    public int speedlimit;
    private Route ruta;
    private Triangle signal;
    private boolean isVisible;
    private boolean isWrong;
    private boolean isUnNecessary;
    /**
     * Constructor for objects of class Sign
     */
    public Sign(String locationA, int x1, int y1, String locationB, int x2, int y2, int speedlimit1){
        speedlimit = speedlimit1;
        isVisible = false;
        colorInicio = locationA;
        posicionInicioX = x1;
        posicionInicioY = y1;
        colorFinal = locationB;
        posicionFinalX = x2;
        posicionFinalY = y2;
        colorFinal = locationB;
        isWrong = false;
        isUnNecessary = false;
        color = "black";
    }
    /**
     * Metodo que dibuja el objeto de la clase Sign
     */
    private void drawSign(){
        if(isVisible){
            ruta = new Route(colorInicio,posicionInicioX,posicionInicioY,colorFinal,posicionFinalX,posicionFinalY);
            int puntox = ((ruta.posicionInicioX + 25) + (ruta.posicionFinalX +25)) / 2;
            int puntoy = ((ruta.posicionInicioY + 25) + (ruta.posicionFinalY + 25)) / 2;
            int puntoxx = (ruta.posicionInicioX + puntox) / 2;
            int puntoyy = (ruta.posicionInicioY + puntoy) / 2;
            signal = new Triangle();
            signal.moveHorizontal(puntoxx);
            signal.moveVertical(puntoyy);
            signal.changeColor(color);
            signal.makeVisible();
        }
    }
    /**
     * Metodo que borra el objeto Sign del canvas.
     */
    private void removeSign(){
        signal.erase();
    }
    /**
     * Metodo que hace visible el objeto Sign en el canvas.
     */
    public void makeVisible(){
        isVisible = true;
        drawSign();
    }
    /**
     * Metodo que hace invisible el objeto Sign en el canvas.
     */
    public void makeInvisible(){
        removeSign();
        isVisible = false;
    }
    public void changeColor(String newColor){
        makeInvisible();
        color = newColor;
        makeVisible();
    }
    public void changeColorRange(int speedlimit){
        if(speedlimit >= 0 && speedlimit < 5){
            signal.changeColor("light_gray");
        }else if(speedlimit >= 5 && speedlimit < 10){
            signal.changeColor("gray");
        }else if(speedlimit >= 10 && speedlimit < 20){
            signal.changeColor("dark_gray");
        }else{
            signal.changeColor("black");
        }        
    }
}
