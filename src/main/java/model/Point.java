package model;

/**
 * Point class represents point in a 2d space.
 *
 * @author Jakub Nowak gr 5
 * @version 3.0
 */
public class Point {

    /**
     * Position of a point on the x axis.
     */
    private float x;
    /**
     * Position of a point on the y axis.
     */
    private float y;

    /**
     * Class constructor forming a point at the coordinate system origin.
     */
    public Point() {
    }

    /**
     * Class constructor forming a point from coordinations.
     *
     * @param x position on x axis
     * @param y position on y axis
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method returns position of a point on the x axis.
     *
     * @return posistion on x axis
     */
    public float getX() {
        return x;
    }

    /**
     * Method returns position of a point on the y axis.
     *
     * @return position on y axis
     */
    public float getY() {
        return y;
    }

    /**
     * Method sets value on X axis of the point.
     *
     * @param x given value
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Method sets value on Y axis of the point.
     *
     * @param y given value
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Method calculating angle between middle point and two other points.
     *
     * @param pointL point on the left of the middle point
     * @param pointR point on the right of the middle point
     * @return angle between points
     */
    public double angle(Point pointL, Point pointR) {
        double numerator = pointR.y * (x - pointL.x) + y * (pointL.x - pointR.x) + pointL.y * (pointR.x - x);
        double denominator = (pointR.x - x) * (x - pointL.x) + (pointR.y - y) * (y - pointL.y);
        double ratio = numerator / denominator;

        double angleRad = Math.atan(ratio);
        double angleDeg = (angleRad * 180) / Math.PI;

        if (angleDeg < 0) {
            angleDeg = 180 + angleDeg;
        }

        return angleDeg;
    }
}
