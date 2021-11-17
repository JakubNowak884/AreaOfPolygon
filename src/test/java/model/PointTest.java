package model;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Jakub Nowak gr 5
 */
public class PointTest {

    @ParameterizedTest
    @MethodSource("pointsAndAngleProvider")
    void testAngle(Point L, Point M, Point R, double expectedAngle) {
        double angle = M.angle(L, R);
        assertEquals(angle, expectedAngle, 0.01);
    }

    static Stream<Arguments> pointsAndAngleProvider() {
        return Stream.of(
                arguments(new Point(0.0f, 1.0f), new Point(0.0f, 0.0f), new Point(2.0f, 0.0f), 90.0d),
                arguments(new Point(0.0f, 0.0f), new Point(1.0f, 0.0f), new Point(2.0f, 0.0f), 0.0d));
    }
}
