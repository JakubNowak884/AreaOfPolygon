package model;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * PointTest tests the methods of point class.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class PointTest {

    /**
     * Test to check if method angle calculates angle between three points
     * corretly.
     */
    @ParameterizedTest
    @MethodSource("pointsAndAngleProvider")
    void testAngle(Point L, Point M, Point R, double expectedAngle) {
        double angle = M.angle(L, R);
        assertEquals(expectedAngle, angle, 0.01);
    }

    static Stream<Arguments> pointsAndAngleProvider() {
        return Stream.of(
                arguments(new Point(0.0f, 0.0f), new Point(0.0f, 1.0f), new Point(1.0f, 0.0f), 45.0d),
                arguments(new Point(0.0f, 0.0f), new Point(2.0f, 2.0f), new Point(4.0f, 0.0f), 90.0d),
                arguments(new Point(0.0f, 0.0f), new Point(1.0f, 0.0f), new Point(2.0f, 0.0f), 0.0d));
    }
}
