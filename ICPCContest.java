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
 * @author (your name) 
 * @version (a version number or a date)
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
    /**
     * Constructor for objects of class ICPCContest
     */
    public ICPCContest()
    {
        solution = 0;
        routeWithoutsignal = new HashMap<String,Route>();
        oldRoutes = new HashMap<String,Integer>();
    }
    public int solve(int cost, int[][]routesSpeedLimits){
        icpc = new ICPC(cost,routesSpeedLimits);
        for(int i = 0; i < icpc.mRoutesSpeedLimit.length; i ++){
            String code = icpc.colores.get(icpc.mRoutesSpeedLimit[i][0]) + " " + icpc.colores.get(icpc.mRoutesSpeedLimit[i][1]);
            icpc.putSign(icpc.colores.get(icpc.mRoutesSpeedLimit[i][0]),icpc.colores.get(icpc.mRoutesSpeedLimit[i][1]),icpc.mRoutesSpeedLimit[i][2]);
            if(icpc.signs.get(code).color == "green"){
                oldRoutes.put(code, icpc.routesSpeedLimit.get(code));
                icpc.removeSign(icpc.colores.get(icpc.mRoutesSpeedLimit[i][0]),icpc.colores.get(icpc.mRoutesSpeedLimit[i][1]));
                routeWithoutsignal.put(code, icpc.routes.get(code));
            }
        }
        solution1 += icpc.signs.size() * cost;
        for(String s : icpc.routes.keySet()){
            if(icpc.signs.containsKey(s) == false){
                icpc.signsSpeedLimit.remove(s);
                icpc.routes.get(s).changeColor("green");
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
        int max = Collections.max(icpc.signsSpeedLimit.values());
        for(Integer s : icpc.routesSpeedLimit.values()){
            solution2 += Math.abs(max - s);
        }
        if(solution1 < solution2){
            return solution1;
        }else{
            return solution2;
        }
    }
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
        }
    }
}
    