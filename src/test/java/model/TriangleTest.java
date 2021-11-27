package model;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * TriangleTest tests the methods of triangle class.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class TriangleTest {

    /**
     * Test to check if method area correctly calculates area of a triangle.
     */
    @ParameterizedTest
    @MethodSource("triangleAndAreaProvider")
    void testAngle(Triangle triangle, float expectedArea) {
        double area = triangle.area();
        assertEquals(area, expectedArea, 0.01, "Areas are not equal");
    }

    static Stream<Arguments> triangleAndAreaProvider() {
        return Stream.of(
                arguments(new Triangle(new Point(0.0f, 0.0f), new Point(0.0f, 0.0f), new Point(0.0f, 0.0f)), 0.0f),
                arguments(new Triangle(new Point(5.0f, 0.0f), new Point(0.0f, 0.0f), new Point(0.0f, 0.0f)), 0.0f),
                arguments(new Triangle(new Point(0.0f, 0.0f), new Point(5.0f, 0.0f), new Point(0.0f, 0.0f)), 0.0f),
                arguments(new Triangle(new Point(0.0f, 0.0f), new Point(0.0f, 0.0f), new Point(5.0f, 0.0f)), 0.0f),
                arguments(new Triangle(new Point(0.0f, 0.0f), new Point(1.0f, 1.0f), new Point(2.0f, 0.0f)), 1.0f),
                arguments(new Triangle(new Point(0.0f, 0.0f), new Point(0.5f, 3.0f), new Point(1.0f, 0.0f)), 1.5f));
    }
}
