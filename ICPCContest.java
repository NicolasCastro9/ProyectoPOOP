import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections; 
import javax.swing.JOptionPane;
import java.lang.Math;
/**
 * Write a description of class ICPCContest here.
 * 
 * @author (Nicolás Castro Jaramillo y Marco Antonio Alvarez) 
 * @version  4.0 (22/10/2022)
 */
public class ICPCContest
{
    private int[][] routesSpeedLimits;
    private ICPC icpc;
    private int solution1;
    private int solution2;
    private int solution;
    private HashMap<String,Route> routeWithoutsignal;
    private HashMap<String,Integer> oldRoutes;
    private static boolean ok;
    /**
     * Constructor for objects of class ICPCContest
     */
    public ICPCContest()
    {
        solution = 0;
        routeWithoutsignal = new HashMap<String,Route>();
        oldRoutes = new HashMap<String,Integer>();
        ok = false;
    }
    /**
     * Metodo que resuelve el problema B de ICPC
     * @param cost es el costo por cada señal
     * @param routesSpeedLimits es la matriz que contiene las intersecciones y la velocidad permitida entre las rutas
     * @return solution retorna solution1 si sale mas barato colocar señales o solution2  si sale mas barato aumentar la velocidad permitida de las rutas
     */
    public int solve(int cost, int[][]routesSpeedLimits){
        icpc = new ICPC(cost,routesSpeedLimits);
        for(int i = 0; i < icpc.mRoutesSpeedLimit.length; i ++){
            String code = icpc.colores.get(icpc.mRoutesSpeedLimit[i][0]) + " " + icpc.colores.get(icpc.mRoutesSpeedLimit[i][1]);
            icpc.putSign(icpc.colores.get(icpc.mRoutesSpeedLimit[i][0]),icpc.colores.get(icpc.mRoutesSpeedLimit[i][1]),icpc.mRoutesSpeedLimit[i][2]); //coloca señales en todas las rutas
            if(icpc.signs.get(code).color == "green"){
                oldRoutes.put(code, icpc.routesSpeedLimit.get(code));
                icpc.removeSign(icpc.colores.get(icpc.mRoutesSpeedLimit[i][0]),icpc.colores.get(icpc.mRoutesSpeedLimit[i][1])); //elimina las señales innecesarias
                routeWithoutsignal.put(code, icpc.routes.get(code));
            }
        }
        solution1 += icpc.totalSignsCost();
        for(String s : icpc.routes.keySet()){
            if(icpc.signs.containsKey(s) == false){
                icpc.signsSpeedLimit.remove(s);
                icpc.routes.get(s).changeColor("green");  //las rutas sin señales se les aumentara la velocidad y se les cambiara el color
            }
        }
        for(String s : routeWithoutsignal.keySet()){
            for(String s2 : icpc.routes.keySet()){
                if(routeWithoutsignal.get(s).colorInicio == icpc.routes.get(s2).colorFinal){
                    icpc.signsSpeedLimit.put(s,icpc.signsSpeedLimit.get(s2));
                    solution1 += Math.abs(icpc.signsSpeedLimit.get(s2) - oldRoutes.get(s)); 
                }
            }
        }
        int max = Collections.max(icpc.signsSpeedLimit.values()); //es el valor maximo en el hashmap de velocidad en las señales
        for(Integer s : icpc.routesSpeedLimit.values()){
            solution2 += Math.abs(max - s);
        }
        if(solution1 < solution2){
            ok = true;
            return solution1;
        }else{
            ok = true;
            return solution2;
        }
    }
    /**
     * metodo que simula la solucion del problemaB de ICPC pero sin solucionar directamente el problema
     * @param cost es el costo por cada señal
     * @param routesSpeedLimits es la matriz que contiene las intersecciones y la velocidad permitida entre las rutas
     */
    public void simulate(int cost, int[][]routesSpeedLimits){
        solve(cost,routesSpeedLimits);
        if(solution1 < solution2){
            icpc.makeVisible();
        }else{
            for(String s : icpc.signs.keySet()){
                icpc.signs.get(s).changeColor("white");
            }
            int max = Collections.max(icpc.signsSpeedLimit.values());
            for(String s : icpc.routes.keySet()){
                if(icpc.signsSpeedLimit.get(s) != max){
                    icpc.routes.get(s).changeColor("green");
                }
            }
            icpc.makeVisible();
            ok = true;
        }
    }
    /**
     * metodo que devuelve true si el ultimo procedimiento se realizo con exito.
     */
    public boolean ok(){
        return ok;
        }
}
    