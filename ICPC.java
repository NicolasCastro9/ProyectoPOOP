import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections; 
import javax.swing.JOptionPane;
/**
 * Clase que crea el ICPC
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  3.0 (27/09/2022)
 */
public class ICPC{
    private Canvas canvas;
    private int length;
    private int width;
    private boolean ok;
    private boolean isVisible;
    private HashMap <String,Intersection> intersections; //Hashmap con las intersecciones
    private HashMap <String,Route> routes; //Hashmap con las rutas
    private HashMap <String,Sign> signs; //Hashmap con las señales
    private HashMap<String,Integer> signsSpeedLimit; // Hashmap con las velocidades de las señales
    private HashMap<Integer,String> colores; //Hashmap de los colores de las intersecciones
    private HashMap<String,Integer> routesSpeedLimit; //Hashmap de las velocidades que tienen las rutas
    private int[][] mRoutesSpeedLimit; //matriz del ejercicio
    private int costoSeñales;
    /**
     * Constructor for objects of class ICPC
     */
    public ICPC(int length, int width)
    {
        if(length < 0 || width < 0){
            JOptionPane.showMessageDialog(null, "no se admiten numeros negativos"); //no permite que los valores ingresados sean negativos
        }else{
            Canvas canvas = Canvas.getCanvas(length, width);
            canvas.setVisible(true);
            intersections = new HashMap<String,Intersection>();
            routes = new HashMap<String,Route>();
            signs = new HashMap<String,Sign>();
            signsSpeedLimit = new HashMap<String,Integer>();
            colores = new HashMap<Integer,String>();
            routesSpeedLimit = new HashMap<String,Integer>();
            mRoutesSpeedLimit = new int [3][];
            ok = false;
            isVisible = true;            
        }
    }
    /**
     * Constructor para la clase ICPC que acepta el costo de las señales
     */
    public ICPC(int length, int width, int cost){
        this(length,width);
        costoSeñales = cost;
    }
    /**
     * Constructor para la clase ICPC que acepta una matriz de valores
     */
    public ICPC(int cost, int[][] routespeedLimits){
        this(700,700);
        costoSeñales = cost;
        mRoutesSpeedLimit = routespeedLimits;
        createColorDictionary();
        int a = 0;
        int b = 1;
        addIntersection(colores.get(mRoutesSpeedLimit[0][0]),0, 0);
        addIntersection(colores.get(mRoutesSpeedLimit[0][1]),0, 150);
        for(int i = 1; i < mRoutesSpeedLimit.length; i ++){
            if(mRoutesSpeedLimit[i][0] == mRoutesSpeedLimit[i-1][0]){
                if(intersections.containsKey(colores.get(mRoutesSpeedLimit[i][1])) == false){
                    a += 1;
                    addIntersection(colores.get(mRoutesSpeedLimit[i][1]),a * 150, b * 150);
                }
            }else{
                a = 0;
                if(intersections.containsKey(colores.get(mRoutesSpeedLimit[i][1])) == false){
                    b += 1;
                    addIntersection(colores.get(mRoutesSpeedLimit[i][1]),a * 150, b * 150);
                }
            }
        }
        for(int i = 0; i < mRoutesSpeedLimit.length; i ++){
            addRoad(colores.get(mRoutesSpeedLimit[i][0]),colores.get(mRoutesSpeedLimit[i][1]));
            String code = colores.get(mRoutesSpeedLimit[i][0]) + " " + colores.get(mRoutesSpeedLimit[i][1]);
            routesSpeedLimit.put(code,mRoutesSpeedLimit[i][2]);
        }
    }
    /**
     * Metodo que crea un objeto de la clase Intersection.
     * @param color  es el color con el cual se identificara a la intersección.
     * @param x  es la posicion en el eje x de la intersección.
     * @param y  es la posicion en el eje y de la intersección.
     */
    public void addIntersection(String color, int x, int y){
        if(intersections.containsKey(color) == true){
            JOptionPane.showMessageDialog(null, "color ya existe"); //si ya existe una interseccion con el color mandara el mensaje
            ok = false;
        }else{
            Intersection inter = new Intersection(color,x,y);
            inter.makeVisible();
            intersections.put(color,inter);
            ok = true;
        }
    }
    /**
     * Metodo que crea un objeto de la clase Route.
     * @param interA  es la intersección donde empieza la ruta.
     * @param interB  es la interseccioón donde termina la ruta.
     */    
    public void addRoad(String interA, String interB){
        String keyRoad = interA + " " + interB;        
        if(routes.containsKey(keyRoad) == true){
            JOptionPane.showMessageDialog(null, "ruta ya existe"); //si ya existe una ruta con el color mandara el mensaje
            ok = false;            
        }else{
            if(intersections.containsKey(interA) == true && intersections.containsKey(interB) == true){
                Route route = new Route (intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y);
                route.makeVisible();
                intersections.get(interA).makeInvisible();
                intersections.get(interB).makeInvisible();        
                intersections.get(interA).makeVisible();
                intersections.get(interB).makeVisible();
                routes.put(keyRoad,route);
                ok = true;
            }else{
                JOptionPane.showMessageDialog(null, "No se puede formar la ruta");//si no existe una de las intersecciones mandara el mensaje
                ok = false;
            }
        }
    }
    /**
     * Metodo que añade la velocidad a un hashmap relacionada a una ruta dada
     * @param interA es la intersección donde empieza la ruta
     * @param interB es la interseccion donde termina la ruta
     * @param speedlimit es la velocidad a agregar
     */
    public void routeSpeedLimit(String interA, String interB, int speedlimit){
        String keyRoad = interA + " " + interB;
        if(routes.containsKey(keyRoad) == true){
            routesSpeedLimit.put(keyRoad,speedlimit);
            ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "La ruta no existe"); //si no existe la ruta mandara el mensaje
            ok = false;
        }        
    }
    /**
     * Metodo que crea un objeto de la clase Sign.
     * @param interA  es la intersección donde empieza la ruta.
     * @param interB  es la interseccioón donde termina la ruta.
     * @param speedLimit  el limite de velocidad en la señal.
     */       
    public void putSign(String interA, String interB, int speedlimit){
        String keyRoad = interA + " " + interB;        
        if(signs.containsKey(keyRoad) == true){
            JOptionPane.showMessageDialog(null, "señal ya existe"); //si ya existe una señal con el color mandara el mensaje
            ok = false;            
        }else{
            if(routes.containsKey(keyRoad) == true){
                Sign sign = new Sign(intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y,speedlimit);
                sign.makeVisible();
                sign.changeColorRange(speedlimit); //la señal cambiara en un tono de grises dependiendo del valor
                signs.put(keyRoad,sign);
                signsSpeedLimit.put(keyRoad,speedlimit);
                if(speedlimit > routesSpeedLimit.get(keyRoad)){
                    sign.changeColor("magenta"); //si es una señal erronea sera de color magenta
                }
                for(String s : intersections.keySet()){
                    if(signs.containsKey(s + " " + interA)){
                        sign.changeColor("green"); // si la señal es innecesaria sera de color verde
                    }
                }
                ok = true;
            }else{
                JOptionPane.showMessageDialog(null, "No se puede poner la señal"); //si no existe la ruta mandara el mensaje
                ok = false;
            }
        }
    }
    /**
     * Metodo para remover una interseccion
     * @param color  es el color de la interseccion a eliminar
     */
    public void delIntersection(String color){
        if(intersections.containsKey(color) == true){
            for(String s : intersections.keySet()){
                delRoad(color,s);
                delRoad(s,color);
            }
            intersections.get(color).makeInvisible();
            intersections.remove(color);
            ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "No existe la interseccion"); //si no existe la interseccion a eliminar, mandara el mensaje
            ok = false;            
        }
    }
    /**
     * Metodo para remover una ruta
     * @param interA es la interseccion donde empieza la ruta
     * @param interB es la interseccion donde termina la ruta
     */    
    public void delRoad(String locationA, String locationB){
        String code = locationA + " " + locationB;        
        if(routes.containsKey(code) == true){
            routes.get(code).makeInvisible();
            routes.remove(code);
            removeSign(locationA,locationB);
            ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "No existe la ruta"); //si no existe la ruta a eliminar mandara el mensaje
            ok = false;
        }
    }
    /**
     * Metodo para remover una señal
     * @param interA es la interseccion donde empieza la ruta y se encuentra la señal
     * @param interB es la interseccion donde termina la ruta y se encuentra la señal
     */
    public void removeSign(String interA, String interB){
        String code = interA + " " + interB;
        if(signs.containsKey(code) == true ){
            signsSpeedLimit.remove(signs.get(code));
            signs.get(code).makeInvisible();
            signs.remove(code);
            ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "No existe la señal"); //si no existe la señal a eliminar mandara el mensaje
            ok = false;            
        }
    }
    /**
     * Metodo que devuelve las intersecciones que se encuentran en el canvas
     * @return retorna un array de String de todas las intersecciones en orden alfabetico e identificadas por su color
     */
    public String[] intesections(){
        String [] intersectionsreturn = intersections.keySet().toArray(new String[0]);
        Arrays.sort(intersectionsreturn);
        ok = true;
        return intersectionsreturn;
    }
    /**
     * Metodo que devuelve las rutas que se encuentran en el canvas
     * @return retorna una matriz de String en la que cada array contiene  la ruta identificada por su clave y por la velocidad de esta
     */
    public String[][] routes(){
        String[][] routesreturn = new String[routes.size()][2];
        String [] rutas = routes.keySet().toArray(new String[0]);        
        Arrays.sort(rutas);
        ok = true;
        for(int i = 0; i < routes.size(); i ++){
            routesreturn[i][0] = rutas[i];
            if(routesSpeedLimit.containsKey(rutas[i]) == true){
                routesreturn[i][1] = String.valueOf(routesSpeedLimit.get(rutas[i]));
            }
        }
        return routesreturn;
    }
    /**
     * Metodo que devuelve las señales que se encuentran en el canvas
     * @return retorna una matriz de String en la que cada array contiene la clave de la señal y la velocidad escrita en esta
     */
    public String[][] signs(){
        String[][] signsreturn = new String[signs.size()][2];
        String [] señales = signs.keySet().toArray(new String[0]);
        Integer[] speedsint = signsSpeedLimit.values().toArray(new Integer[0]);
        String[] speedstring = new String[speedsint.length];
        for (int i = 0; i < speedsint.length; i++) {
            speedstring[i] = String.valueOf(speedsint[i]);
            Arrays.sort(señales);
        }
        for(int i = 0; i < signs.size(); i ++){
            signsreturn[i][0] = señales[i];
            if(signs.containsKey(señales[i]) == true){
                signsreturn[i][1] = String.valueOf(signsSpeedLimit.get(señales[i]));    
            }
        }        
        ok = true;
        return signsreturn;
    }
    /**
     * Metodo que devuelve las señales erroneas que se encuentran en el canvas
     * @return retorna una matriz de String en la que cada array contiene la clave de la señal erronea y la velocidad escrita en esta
     */
    public String[][] wrongSigns(){
        String [] señales = signs.keySet().toArray(new String[0]);
        Integer[] speedsint = signsSpeedLimit.values().toArray(new Integer[0]);
        String[] speedstring = new String[speedsint.length];
        int a = 0;
        for(int i = 0 ; i < signs.size();i ++){
            if(signs.get(señales[i]).color != "magenta"){
                a = +1;
            }
        }
        String [][] wsignsreturn = new String[signs.size()-a][2];        
        for(int i = 0; i < signs.size(); i ++){
            speedstring[i] = String.valueOf(speedsint[i]);
            Arrays.sort(señales);
        }
        for(int i = 0; i < signs.size(); i++){
            if(signs.get(señales[i]).color == "magenta"){
                wsignsreturn[i][0] = señales[i];
                if(signs.containsKey(señales[i]) == true){
                    wsignsreturn[i][1] = String.valueOf(signsSpeedLimit.get(señales[i]));    
                }
            }
        }
        return wsignsreturn;
    }
    /**
     * Metodo que devuelve las señales innecesarias que se encuentran en el canvas
     * @return retorna una matriz de String en la que cada array contiene la clave de la señal innecesaria y la velocidad escrita en esta
     */
    public String[][] unNecessarySigns(){
        String [] señales = signs.keySet().toArray(new String[0]);
        Integer[] speedsint = signsSpeedLimit.values().toArray(new Integer[0]);
        String[] speedstring = new String[speedsint.length];
        int a = 0;
        for(int i = 0 ; i < signs.size();i ++){
            if(signs.get(señales[i]).color != "green"){
                a = +1;
            }
        }
        String [][] usignsreturn = new String[signs.size()-a][2];        
        for(int i = 0; i < signs.size(); i ++){
            speedstring[i] = String.valueOf(speedsint[i]);
            Arrays.sort(señales);
        }
        for(int i = 0; i < signs.size(); i++){
            if(signs.get(señales[i]).color == "green"){
                usignsreturn[i][0] = señales[i];
                if(signs.containsKey(señales[i]) == true){
                    usignsreturn[i][1] = String.valueOf(signsSpeedLimit.get(señales[i]));    
                }
            }
        }
        return usignsreturn;
    }
    /**
     * Metodo que calcula el costo total de implementar señales
     * @return retorna el total gastado en implementar señales
     */
    public int totalSignsCost(){
        int totalCost = 0;
        for (String s : signs.keySet()){
            if(s != "green" && s !="magenta"){
                totalCost += costoSeñales;
            }
        }
        return totalCost;
    }
    /**
     * Metodo que hace Invisible todos los elementos en el canvas
     */
    public void makeInvisible(){
        if(isVisible == false){
            ok = false;
        }else{
            for(Route r : routes.values()){
                r.makeInvisible();
            }
            for(Intersection i : intersections.values()){
                i.makeInvisible();
            }
            for(Sign s : signs.values()){
                s.makeInvisible();
            }
            isVisible = false;
            ok = true;
        }
    }
    /**
     * Metodo que hace visible todos los elementos del canvas
     */
    public void makeVisible(){
        if(isVisible == true){
            ok = false;
        }else{
            for(Route r : routes.values()){
                r.makeVisible();
            }
            for(Intersection i : intersections.values()){
                i.makeVisible();
            }
            for(Sign s : signs.values()){
                s.makeVisible();
            }
            isVisible = true;
            ok = true;
        }
    }
    /**
     * Metodo que da por finalizado el programa
     */
    public void finish(){
        System.exit(0);
    }
    /**
     * Metodo que devuelve si la ultima operacion se realizo con exito
     */
    public boolean ok(){
        return ok;
    }
    /**
     * Metodo privado en el que se le asigna una clave numerica a cada color 
     */
    private void createColorDictionary(){
        colores.put(1,"red");
        colores.put(2,"orange");
        colores.put(3,"blue");
        colores.put(4,"pink");
        colores.put(5,"cyan");
        colores.put(6,"yellow");
        colores.put(7,"magenta");
        colores.put(8,"green");
    }
}