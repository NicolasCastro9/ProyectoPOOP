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
 * @version  4.0 (22/10/2022)
 */
public class ICPC{
    private Canvas canvas;
    private int length;
    private int width;
    private static boolean ok;
    private boolean isVisible;
    public HashMap <String,Intersection> intersections; //Hashmap con las intersecciones
    public HashMap <String,Route> routes; //Hashmap con las rutas
    public HashMap <String,Sign> signs; //Hashmap con las señales
    public HashMap <String,Integer> signsSpeedLimit; // Hashmap con las velocidades de las señales
    public HashMap <Integer,String> colores; //Hashmap de los colores de las intersecciones
    public HashMap  <String,Integer> routesSpeedLimit; //Hashmap de las velocidades que tienen las rutas
    public HashMap <String,Sign> wrongSigns; //Hashmap que contiene las señales erroneas
    public HashMap <String,Integer> wrongSignsSpeedLimit; //Hashmap que contiene los las velocidades de las señales erroneas
    public HashMap <String,Sign> innSigns;//Hashmap que contiene las señales innecesarias
    public HashMap <String,Integer> innSignsSpeedLimit; //Hashmap que contiene los las velocidades de las señales innecesarias
    public static String[][] routesA; //matriz auxiliar
    public int[][] mRoutesSpeedLimit; //matriz del ejercicio
    public int costoSeñales; //Costo total de las señales
    /**
     * Constructor for objects of class ICPC
     * @param length es la longitud del canvas
     * @param width es el ancho del canvas
     */
    public ICPC(int length, int width)
    {
        if(length < 0 || width < 0){
            JOptionPane.showMessageDialog(null, "no se admiten numeros negativos"); //no permite que los valores ingresados sean negativos
        }else{
            Canvas canvas = Canvas.getCanvas(length, width);
            canvas.setVisible(false);
            intersections = new HashMap<String,Intersection>();
            routes = new HashMap<String,Route>();
            signs = new HashMap<String,Sign>();
            signsSpeedLimit = new HashMap<String,Integer>();
            colores = new HashMap<Integer,String>();
            routesSpeedLimit = new HashMap<String,Integer>();
            wrongSigns = new HashMap<String,Sign>();
            wrongSignsSpeedLimit = new HashMap <String,Integer>();
            innSigns = new HashMap<String,Sign>();
            innSignsSpeedLimit = new HashMap <String,Integer>();
            mRoutesSpeedLimit = new int [3][];
            ok = false;
            isVisible = false;            
        }
    }
    /**
     * Constructor para la clase ICPC que acepta el costo de las señales
     * @param length es la longitud del canvas
     * @param width es el ancho del canvas 
     * @param cost es el costo de cada señal
     */
    public ICPC(int length, int width, int cost){
        this(length,width);
        costoSeñales = cost;
    }
    /**
     * Constructor para la clase ICPC que acepta una matriz de valores
     * @param cost es el costo de cada señal
     * @param routespeedLimits es la matriz a ingresar
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
            intersections.put(color,inter);
            ok = true;
        }
    }
    /**
     * Metodo que crea un objeto de la clase Intersection.
     * @param type es el tipo de interseccion
     * @param color  es el color con el cual se identificara a la intersección.
     * @param x  es la posicion en el eje x de la intersección.
     * @param y  es la posicion en el eje y de la intersección.
     */
    public void addIntersection(String type,String color, int x, int y){
        if(intersections.containsKey(color) == true){
            JOptionPane.showMessageDialog(null, "color ya existe"); //si ya existe una interseccion con el color mandara el mensaje
            ok = false;
        }else{
            switch(type){
                case "hermit":
                    Hermit hermit = new Hermit(color,x,y);
                    intersections.put(color,hermit);
                    ok = true;
                    break;
                case "needy":
                    Needy needy = new Needy(color,x,y);
                    intersections.put(color,needy);
                    ok = true;
                    break;
                case "normal":
                    addIntersection(color,x,y);
                    ok = true;
                    break;
            }
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
        }else if(interA == interB){
            JOptionPane.showMessageDialog(null, "no se puede generar ruta"); //no se puede generar ruta donde las dos intersecciones es el mismo
            ok = false;
        }else{
            if(intersections.containsKey(interA) == true && intersections.containsKey(interB) == true && intersections.get(interA).permiteRutas(routes()) == true && intersections.get(interB).permiteRutas(routes())){
                Route route = new Route (intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y);
                routes.put(keyRoad,route);
                ok = true;
            }else{
                JOptionPane.showMessageDialog(null, "No se puede formar la ruta");//si no existe una de las intersecciones mandara el mensaje
                ok = false;
            }
        }
    }
    /**
     * Metodo que crea un objeto de la clase Route.
     * @param type, es el tipo de ruta
     * @param interA  es la intersección donde empieza la ruta.
     * @param interB  es la interseccioón donde termina la ruta.
     */
    public void addRoad(String type,String interA, String interB){
        String keyRoad = interA + " " + interB; 
        if(routes.containsKey(keyRoad) == true){
            JOptionPane.showMessageDialog(null, "ruta ya existe"); //si ya existe una ruta con el color mandara el mensaje
            ok = false;            
        }else if(interA == interB){
            JOptionPane.showMessageDialog(null, "no se puede generar ruta"); //no se puede generar ruta donde las dos intersecciones es el mismo
            ok = false;
        }else{
            if(intersections.containsKey(interA) == true && intersections.containsKey(interB) == true && intersections.get(interA).permiteRutas(routes()) == true && intersections.get(interB).permiteRutas(routes())){
               switch(type){
                    case "rebel":
                        Rebel rebel = new Rebel (intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y);
                        routes.put(keyRoad,rebel);
                        routeSpeedLimit(interA,interB,10);
                        ok = true;
                        break;
                    case "fixed":
                        Fixed fixed = new Fixed (intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y);
                        routes.put(keyRoad,fixed);
                        routeSpeedLimit(interA,interB,10);
                        ok = true;
                        break;
                    case "normal":
                        addRoad(interA,interB);
                        ok = true;
                        break;
                } 
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
            if(routes.containsKey(keyRoad) == true && routes.get(keyRoad).ponerSeñales() == true && routesSpeedLimit.containsKey(keyRoad)){
                Sign sign = new Sign(intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y,speedlimit);
                sign.changeColorRange(speedlimit); //la señal cambiara en un tono de grises dependiendo del valor
                signs.put(keyRoad,sign);
                signsSpeedLimit.put(keyRoad,speedlimit);
                if(speedlimit > routesSpeedLimit.get(keyRoad)){
                    sign.changeColor("magenta"); //si es una señal erronea sera de color magenta
                    wrongSignsSpeedLimit.put(keyRoad,speedlimit);
                    wrongSigns.put(keyRoad,sign);
                }
                for(String s : intersections.keySet()){
                    if(signs.containsKey(s + " " + interA)){
                        sign.changeColor("green"); // si la señal es innecesaria sera de color verde
                        innSignsSpeedLimit.put(keyRoad,speedlimit);
                        innSigns.put(keyRoad,sign);
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
     * Metodo que crea un objeto de la clase Sign.
     * @param type es el tipo de señal
     * @param interA  es la intersección donde empieza la ruta.
     * @param interB  es la interseccioón donde termina la ruta.
     * @param speedLimit  el limite de velocidad en la señal.
     */
    public void putSign(String type,String interA, String interB, int speedlimit){
        String keyRoad = interA + " " + interB;
        if(signs.containsKey(keyRoad) == true){
            JOptionPane.showMessageDialog(null, "señal ya existe"); //si ya existe una señal con el color mandara el mensaje
            ok = false;            
        }else{
            if(routes.containsKey(keyRoad) == true && routes.get(keyRoad).ponerSeñales() == true && routesSpeedLimit.containsKey(keyRoad)){
                switch(type){
                    case "twin":
                        Twin twin = new Twin(intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y,speedlimit);
                        signs.put(keyRoad,twin);
                        signsSpeedLimit.put(keyRoad,speedlimit);
                        signs.put(interB + " " + interA, twin.gemelo);
                        signsSpeedLimit.put(interB + " " + interA,speedlimit);
                        ok = true;
                        break;
                    case "cautious":
                        Cautious cautious = new Cautious(intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y,speedlimit);
                        int min = Collections.min(routesSpeedLimit.values());
                        cautious.minRutas(min);
                        signs.put(keyRoad,cautious);
                        signsSpeedLimit.put(keyRoad,min);
                        ok = true;
                        break;
                    case "normal":
                        putSign(interA,interB,speedlimit);
                        ok = true;
                        break;
                    case "pari":
                        Pari pari = new Pari(intersections.get(interA).color,intersections.get(interA).x,intersections.get(interA).y,intersections.get(interB).color,intersections.get(interB).x,intersections.get(interB).y,speedlimit);
                        signs.put(keyRoad,pari);
                        signsSpeedLimit.put(keyRoad,speedlimit);
                        ok = true;
                        break;
                }
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
                if(routes.containsKey(color + " " + s)){
                    delRoad(color,s);
                }
                if(routes.containsKey(s + " " + color)){
                    delRoad(s,color);
                }                
            }
            intersections.get(color).removeIntersection();
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
        if(routes.containsKey(code) == true && routes.get(code).quitarRuta() == true){
            if(signs.containsKey(code)){
                removeSign(locationA,locationB);
            }
            routes.get(code).removeRoute();
            signsSpeedLimit.remove(code);
            routes.remove(code);
            if(intersections.get(locationA).seAutoelimina(routes()) == true){
                delIntersection(locationA);
            }
            if(intersections.get(locationB).seAutoelimina(routes()) == true){
                delIntersection(locationB);
            }
            ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "No se puede eliminar la ruta"); //si no existe la ruta a eliminar mandara el mensaje
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
        if(signs.containsKey(code) == true && routes.get(code).ponerSeñales() == true){
            signsSpeedLimit.remove(signs.get(code));
            signs.get(code).remove();
            signs.remove(code);
            ok = true;
            if(wrongSigns.containsKey(code) == true ){
                wrongSigns.remove(code);
                wrongSignsSpeedLimit.remove(wrongSigns.get(code));
            }
            if(innSigns.containsKey(code) == true ){
                innSigns.remove(code);
                innSignsSpeedLimit.remove(innSigns.get(code));
            }
            if(signs.containsKey(interB + " " + interA)){
                signsSpeedLimit.remove(signs.get(interB + " " + interA));
                signs.get(interB + " " + interA).remove();
                signs.remove(interB + " " + interA);  
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se puede realizar la accion"); //si no existe la señal a eliminar mandara el mensaje
            ok = false;            
        }
    }
    /**
     * Metodo que devuelve las intersecciones que se encuentran en el canvas
     * @return retorna un array de String de todas las intersecciones en orden alfabetico e identificadas por su color
     */
    public String[] intersections(){
        String [] intersectionsreturn = intersections.keySet().toArray(new String[0]);
        Arrays.sort(intersectionsreturn);
        ok = true;
        return intersectionsreturn;
    }
    /** 
     * Metodo que devuelve las rutas que se encuentran en el canvas
     * @return retorna una matriz de String en la que cada array contiene  la ruta identificada por su clave y por la velocidad de esta
     */
    public  String[][] routes(){
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
        routesA = routesreturn;
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
        String[][] wsignsreturn = new String[wrongSigns.size()][2];
        String [] wseñales = wrongSigns.keySet().toArray(new String[0]);
        Integer[] wspeedsint = wrongSignsSpeedLimit.values().toArray(new Integer[0]);
        String[] wspeedstring = new String[wspeedsint.length];
        for (int i = 0; i < wspeedsint.length; i++) {
            wspeedstring[i] = String.valueOf(wspeedsint[i]);
            Arrays.sort(wseñales);
        }
        for(int i = 0; i < wrongSigns.size(); i ++){
            wsignsreturn[i][0] = wseñales[i];
            if(wrongSigns.containsKey(wseñales[i]) == true){
                wsignsreturn[i][1] = String.valueOf(wrongSignsSpeedLimit.get(wseñales[i]));    
            }
        }        
        ok = true;
        return wsignsreturn;
    }
    /**
     * Metodo que devuelve las señales innecesarias que se encuentran en el canvas
     * @return retorna una matriz de String en la que cada array contiene la clave de la señal innecesaria y la velocidad escrita en esta
     */
    public String[][] unNecessarySigns(){
        String[][] isignsreturn = new String[innSigns.size()][2];
        String [] iseñales = innSigns.keySet().toArray(new String[0]);
        Integer[] ispeedsint = innSignsSpeedLimit.values().toArray(new Integer[0]);
        String[] ispeedstring = new String[ispeedsint.length];
        for (int i = 0; i < ispeedsint.length; i++) {
            ispeedstring[i] = String.valueOf(ispeedsint[i]);
            Arrays.sort(iseñales);
        }
        for(int i = 0; i < innSigns.size(); i ++){
            isignsreturn[i][0] = iseñales[i];
            if(signs.containsKey(iseñales[i]) == true){
                isignsreturn[i][1] = String.valueOf(innSignsSpeedLimit.get(iseñales[i]));    
            }
        }        
        ok = true;
        return isignsreturn;
    }
    /**
     * Metodo que calcula el costo total de implementar señales
     * @return retorna el total gastado en implementar señales
     */
    public int totalSignsCost(){
        int totalCost = 0;
        for (Sign s : signs.values()){
            if(s.color != "green" && s.color !="magenta"){
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
            JOptionPane.showMessageDialog(null, "el simulador ya es invisible"); //si el simulador ya es invisible manda el mensaje
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
            JOptionPane.showMessageDialog(null, "el simulador ya es visible"); //si el simulador ya es visible manda el mensaje
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
     * @return retorna ok si la ultima accion se pudo realizar
     */
    public boolean ok(){
        return ok;
    }
    /**
     * Metodo privado auxiliar en el que se le asigna una clave numerica a cada color 
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