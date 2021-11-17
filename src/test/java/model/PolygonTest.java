package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jakub Nowak gr 5
 */
public class PolygonTest {

    private Polygon concavePolygon;
    private Polygon convexPolygon;
    
    @BeforeAll
    public void setUp()
    {
        concavePolygon = new Polygon();
        convexPolygon = new Polygon();
    }
    //test with concavePolygon or null
    @Test
    public void testConcavePolygonException() {
        try {
            concavePolygon.area();
            fail("ConcavePolygonException should be thrown");
        } catch (ConcavePolygonException e) {

        }
    }
    
    //test with convexPolygon or null
    @Test
    public void testArea() {
        try {
            float area = convexPolygon.area();
            float expectedArea = 1.0f;
            assertEquals(area, expectedArea);
        } catch (ConcavePolygonException e) {
            fail("ConcavePolygonException should not be thrown");
        }
    }
}
