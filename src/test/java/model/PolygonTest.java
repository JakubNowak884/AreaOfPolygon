package model;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * PolygonTest tests the methods of polygon class.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class PolygonTest {

    /**
     * Test to check if method area throws an exception when polygon is concave.
     */
    @ParameterizedTest
    @MethodSource("concavePolygonProvider")
    public void testConcavePolygonException(Polygon concavePolygon) {
        try {
            concavePolygon.area();
            fail("Exception should be thrown");
        } catch (ConcavePolygonException e) {

        }
    }

    static Stream<Arguments> concavePolygonProvider() {
        return Stream.of(
                arguments(new Polygon(new Point(0.0f, 0.0f))),
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(0.0f, 0.0f))),
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(0.0f, 0.0f), new Point(0.0f, 0.0f))),
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(2.0f, 0.0f), new Point(5.0f, 0.0f), new Point(8.0f, 0.0f))),
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(0.0f, 2.0f), new Point(1.0f, 1.0f), new Point(2.0f, 2.0f), new Point(2.0f, 0.0f))));
    }

    /**
     * Test to check if method area correctly calculates area of a convex
     * polygon.
     */
    @ParameterizedTest
    @MethodSource("convexPolygonAndAreaProvider")
    public void testConvexPolygonException(Polygon convexPolygon, float expectedArea) {
        try {
            float area = convexPolygon.area();
            assertEquals(expectedArea, area, "Areas are not equal");
        } catch (ConcavePolygonException e) {
            fail("ConcavePolygonException should not be thrown");
        }
    }

    static Stream<Arguments> convexPolygonAndAreaProvider() {
        return Stream.of(
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(2.0f, 0.0f)), 0.0f),
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(0.5f, 3.0f), new Point(1.0f, 0.0f)), 1.5f),
                arguments(new Polygon(new Point(0.0f, 0.0f), new Point(0.0f, 2.0f), new Point(1.0f, 3.0f), new Point(2.0f, 2.0f), new Point(2.0f, 0.0f)), 5.0f));
    }
}
