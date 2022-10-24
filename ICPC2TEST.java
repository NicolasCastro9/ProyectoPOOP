

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ICPC2TEST.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ICPC2TEST
{
    /**
     * Default constructor for test class ICPCTEST
     */
    public ICPC2TEST()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void addIntersectionOK()
    {
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("orange", 200, 500);
        iCPC1.addIntersection("cyan", 500, 200);
        assertEquals(true, iCPC1.ok());
    }

    @Test
    public void addRouteOk()
    {
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        assertEquals(true, iCPC1.ok());
    }

    @Test
    public void routeSpeedLimitOk()
    {
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        assertEquals(true, iCPC1.ok());
    }
    @Test
    public void putSignOk()
    {
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        iCPC1.putSign("cyan", "magenta", 10);
        assertEquals(true, iCPC1.ok());
    }
    @Test
    public void delIntersectionOk(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.delIntersection("green");
        assertEquals(true, iCPC1.ok());
    }
    @Test
    public void delRouteOk(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.delRoad("red","green");
        assertEquals(true, iCPC1.ok());
    }
    @Test
    public void removeSignOk()
    {
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.routeSpeedLimit("red", "green", 9);
        iCPC1.routeSpeedLimit("green", "cyan", 10);
        iCPC1.putSign("red", "green", 9);
        iCPC1.putSign("green", "cyan", 10);
        iCPC1.removeSign("red", "green");
        assertEquals(true, iCPC1.ok());
    }
    @Test
    public void intersectionsOk()
    {
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("orange", 200, 500);
        iCPC1.addIntersection("cyan", 500, 200);
        assertEquals("cyan", iCPC1.intersections()[0]);
        assertEquals("orange", iCPC1.intersections()[1]);
        assertEquals("red", iCPC1.intersections()[2]);
    }
    @Test
    public void routesOk(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.routeSpeedLimit("red", "green", 9);
        iCPC1.routeSpeedLimit("green", "cyan", 10);
        assertEquals("green cyan", iCPC1.routes()[0][0]);
        assertEquals("red green", iCPC1.routes()[1][0]);
        assertEquals("10", iCPC1.routes()[0][1]);
        assertEquals("9", iCPC1.routes()[1][1]);
    }
    @Test
    public void singsOk(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addIntersection("red", 234, 400);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.addRoad("cyan", "red");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        iCPC1.routeSpeedLimit("cyan", "red", 20);
        iCPC1.putSign("cyan", "magenta", 10);
        iCPC1.putSign("cyan", "red", 20);
        assertEquals("cyan magenta", iCPC1.signs()[0][0]);
        assertEquals("cyan red", iCPC1.signs()[1][0]);
        assertEquals("10", iCPC1.signs()[0][1]);
        assertEquals("20", iCPC1.signs()[1][1]);
    }
    @Test
    public void wrongSignalOk(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addIntersection("red", 234, 400);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.addRoad("cyan", "red");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        iCPC1.routeSpeedLimit("cyan", "red", 20);
        iCPC1.putSign("cyan", "magenta", 10);
        iCPC1.putSign("cyan", "red", 300);
        assertEquals("cyan red", iCPC1.wrongSigns()[0][0]);
        assertEquals("300", iCPC1.wrongSigns()[0][1]);        
    }
    @Test
    public void unNecessarySignsOk(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addIntersection("red", 234, 400);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.addRoad("magenta", "red");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        iCPC1.routeSpeedLimit("magenta", "red", 20);
        iCPC1.putSign("cyan", "magenta", 10);
        iCPC1.putSign("magenta", "red", 20);
        assertEquals("magenta red", iCPC1.unNecessarySigns()[0][0]);
        assertEquals("20", iCPC1.unNecessarySigns()[0][1]);   
    }
    @Test
    public void totalSignsCostOk(){
        ICPC iCPC1 = new ICPC(600, 600,2);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addIntersection("red", 234, 400);
        iCPC1.addIntersection("blue", 500, 500);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.addRoad("cyan", "red");
        iCPC1.addRoad("cyan", "blue");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        iCPC1.routeSpeedLimit("cyan", "red", 20);
        iCPC1.routeSpeedLimit("cyan", "blue", 10);
        iCPC1.putSign("cyan", "magenta", 10);
        iCPC1.putSign("cyan", "red", 20);
        iCPC1.putSign("cyan", "blue", 10);
        assertEquals(6,iCPC1.totalSignsCost());
    }
    @Test
    public void addIntersectionNOOKAlreadyExist(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("orange", 200, 500);
        iCPC1.addIntersection("orange", 500, 200);
        assertEquals(false, iCPC1.ok());
    }
    @Test
    public void addRouteNOOKAlreadyExist(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.addRoad("green", "cyan");
        assertEquals(false, iCPC1.ok());
    }
    @Test
    public void addRouteNOOKsameIntersection(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.addRoad("green", "green");
        assertEquals(false, iCPC1.ok());
    }
    @Test
    public void addRouteNOOKMissingIntersection(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.addRoad("green", "blue");
        assertEquals(false, iCPC1.ok()); 
    }
    @Test
    public void routeSpeedLimitNOOKMissingRoute(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.routeSpeedLimit("cyan", "red", 10);
        assertEquals(false, iCPC1.ok());        
    }
    @Test
    public void putSignNOOKMissingRoute(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("cyan", 0, 0);
        iCPC1.addIntersection("magenta", 400, 234);
        iCPC1.addRoad("cyan", "magenta");
        iCPC1.routeSpeedLimit("cyan", "magenta", 10);
        iCPC1.putSign("cyan", "blue", 10);
        assertEquals(false, iCPC1.ok());
    }
    @Test
    public void delIntersectionNOOKMissingIntersection(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.delIntersection("magenta");
        assertEquals(false, iCPC1.ok());
    }
    @Test
    public void delRoadNOOKMissingRoute(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.delRoad("green","red");
        assertEquals(false, iCPC1.ok());
    }
    @Test
    public void removeSignNOOKMissingSign(){
        ICPC iCPC1 = new ICPC(600, 600);
        iCPC1.addIntersection("red", 0, 0);
        iCPC1.addIntersection("green", 300, 200);
        iCPC1.addIntersection("cyan", 500, 100);
        iCPC1.addRoad("red", "green");
        iCPC1.addRoad("green", "cyan");
        iCPC1.routeSpeedLimit("red", "green", 9);
        iCPC1.routeSpeedLimit("green", "cyan", 10);
        iCPC1.putSign("red", "green", 9);
        iCPC1.putSign("green", "cyan", 10);
        iCPC1.removeSign("cyan", "green");
        assertEquals(false, iCPC1.ok());
    }
}




