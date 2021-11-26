package model;

import java.util.Iterator;
import java.util.List;

/**
 * Polygon class represents polygon in a 2d space.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class Polygon {

    /**
     * Points of the polygon.
     */
    private CircularLinkedList<Point> points;
    /**
     * Sum of angles of a polygon.
     */
    private double sumOfAngles;

    /**
     * Method checks if polygon is convex.
     *
     * @param sumOfAngles sum of angles of polygon
     * @return true if polygon is convex, false if polygon is concave
     */
    private boolean polygonIsConvex(double sumOfAngles) {
        return (sumOfAngles == (points.size() - 2) * 180);
    }

    /**
     * Class constructor forming a 2x2 square with the bottom left corner at the
     * coordinate system origin.
     */
    public Polygon() {
        points = new CircularLinkedList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 2));
        points.add(new Point(2, 2));
        points.add(new Point(2, 0));

        for (Point point : points) {
            sumOfAngles += point.angle(points.getPrev(point), points.getNext(point));
        }
    }

    /**
     * Class constructor forming a polygon from given points.
     *
     * @param points points of polygon
     */
    public Polygon(Point... points) {

        this.points = new CircularLinkedList<>();

        for (int i = 0; i < points.length; i++) {
            this.points.add(points[i]);
        }

        for (Point point : this.points) {
            sumOfAngles += point.angle(this.points.getPrev(point), this.points.getNext(point));
        }
    }

    /**
     * Class constructor forming a polygon from given points.
     *
     * @param points points of polygon
     */
    public Polygon(List<Point> points) {
        this.points = new CircularLinkedList();
        this.points.addAll(points);

        for (Point point : this.points) {
            sumOfAngles += point.angle(this.points.getPrev(point), this.points.getNext(point));
        }
    }

    /**
     * Method calculates area of a polygon.
     *
     * @throws ConcavePolygonException when polygon is concave, which means its
     * area cannot be calculated.
     * @return area of a polygon
     */
    public float area() throws ConcavePolygonException {
        if (!polygonIsConvex(sumOfAngles)) {
            throw new ConcavePolygonException("Given polygon is convex, sum of angles is equal: ", sumOfAngles);
        }

        float area = 0.0f;

        Iterator<Point> i = points.iterator();
        Point point = i.next();
        while (point != points.get(points.size() - 2)) {
            area += new Triangle(point, points.getNext(point), points.getLast()).area();
            point = i.next();
        }

        return area;
    }
}
