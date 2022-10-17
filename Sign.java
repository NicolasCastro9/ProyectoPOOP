
/**
 * Clase que crea el Sign
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  2.0 (29/09/2022)
 */
public class Sign
{
    private Triangle signal = new Triangle();
    public String color;
    public String colorInicio;
    public String colorFinal;
    private int posicionInicioX;
    private int posicionInicioY;
    private int posicionFinalX;
    private int posicionFinalY;    
    public int speedlimit;
    private boolean isVisible;
    private boolean isWrong;
    private boolean isUnNecessary;
    /**
     * Constructor for objects of class Sign
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
    public void changeColor(String newColor){
        signal.changeColor(newColor);
        color = newColor;
    }
    public void remove(){
        signal.erase();
    }
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
}
