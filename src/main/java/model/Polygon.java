package model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Polygon class represents polygon in a 2d space.
 *
 * @author Jakub Nowak gr 5
 * @version 3.0
 */
public class Polygon {

    /**
     * Points of the polygon.
     */
    private CircularLinkedList<Point> points;

    /**
     * Method checks if polygon is convex.
     *
     * @param sumOfAngles sum of angles of polygon
     * @return true if polygon is convex, false if polygon is concave
     */
    private boolean polygonIsConvex(double sumOfAngles) {
        double epsilon = 0.00001;
        double diff = sumOfAngles - ((points.size() - 2) * 180);
        return (Math.abs(diff) < epsilon);
    }

    /**
     * Method calculates sum of angles of the polygon
     *
     * @return sum of angles of the polygon
     */
    private double sumOfAngles() {
        double sumOfAngles = 0.0f;
        for (Point point : points) {
            sumOfAngles += point.angle(points.getPrev(point), points.getNext(point));
        }
        return sumOfAngles;
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
    }

    /**
     * Class constructor forming a polygon from given points.
     *
     * @param points points of polygon
     */
    public Polygon(Point... points) {

        this.points = new CircularLinkedList<>();
        if (points != null) {
            this.points.addAll(Arrays.asList(points));
        }
    }

    /**
     * Class constructor forming a polygon from given points.
     *
     * @param points points of polygon
     */
    public Polygon(List<Point> points) {
        this.points = new CircularLinkedList();
        if (points != null) {
            this.points.addAll(points);
        }
    }

    /**
     * Method sets value on x axis of point at given index
     *
     * @param index index of the point
     * @param x value to set
     */
    public void setPointX(int index, float x) {
        Point point = points.get(index);
        point.setX(x);
    }

    /**
     * Method sets value on y axis of point at given index
     *
     * @param index index of the point
     * @param y value to set
     */
    public void setPointY(int index, float y) {
        Point point = points.get(index);
        point.setY(y);
    }

    /**
     * Method returns number of points of polygon.
     *
     * @return number of points of polygon
     */
    public int getNumberOfPoints() {
        return points.size();
    }

    /**
     * Method return point of the polygon at given index.
     *
     * @param index index of the point
     * @return points of the polygon
     */
    public Point getPoint(int index) {
        return points.get(index);
    }
    
    /**
     * Method return list of points of the polygon.
     *
     * @return points of the polygon
     */
    public CircularLinkedList<Point> getPoints() {
        return points;
    }

    /**
     * Method adds point to the polygon
     *
     * @param point point to add
     */
    public void AddPoint(Point point) {
        points.add(point);
    }

    /**
     * Method removes last point from the polygon
     */
    public void RemovePoint() {
        points.remove(points.getLast());
    }

    /**
     * Method calculates area of a polygon.
     *
     * @throws ConcavePolygonException when polygon is concave, which means its
     * area cannot be calculated.
     * @return area of a polygon
     */
    public float area() throws ConcavePolygonException {
        double sumOfAngles = sumOfAngles();
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
