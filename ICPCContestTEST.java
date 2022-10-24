

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ICPCContestTEST.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ICPCContestTEST
{
    /**
     * Default constructor for test class ICPCContestTEST
     */
    public ICPCContestTEST()
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
    public void solution1(){
        int[][]a = {{1,2,10},{1,3,5},{1,4,7},{2,5,9}};
        ICPCContest primerCaso = new ICPCContest();
        assertEquals(7,primerCaso.solve(2,a));
    }
    @Test
    public void solution2(){
        int[][]a = {{1,2,10},{1,3,5},{1,4,7},{2,5,9}};
        ICPCContest segundoCaso = new ICPCContest();
        assertEquals(9,segundoCaso.solve(100,a));
    }
    @Test
    public void ejemplo1(){
        int[][]a = {{1,2,4},{2,3,10},{2,4,9}};
        ICPCContest primerCaso = new ICPCContest();
        assertEquals(11,primerCaso.solve(3,a));
    }
    @Test
    public void ejemplo2(){
        int[][]a = {{1,2,3},{1,3,4},{1,4,5},{3,5,6}};
        ICPCContest primerCaso = new ICPCContest();
        assertEquals(4,primerCaso.solve(2,a));
    }
    @Test
    public void ejemplo3(){
        int[][]a = {{1,2,40},{1,3,5},{1,4,23}};
        ICPCContest primerCaso = new ICPCContest();
        assertEquals(15,primerCaso.solve(5,a));
    }
    @Test
    public void ejemplo4(){
        int[][]a = {{1,2,40},{1,3,5},{1,4,23}};
        ICPCContest primerCaso = new ICPCContest();
        assertEquals(52,primerCaso.solve(50,a));
    }
}
