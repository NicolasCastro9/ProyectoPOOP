

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ICPCTEST.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ICPCTEST
{
    /**
     * Default constructor for test class ICPCTEST
     */
    public ICPCTEST()
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
}



