package controller;

import model.Point;
import model.Polygon;
import model.ConcavePolygonException;
import view.View;
import java.util.*;

/**
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class Main {

    /**
     * Program calculates area of polygon by the points given by user. When the
     * points are given incorrectly, program asks for number of angles of a
     * polygon, and then for each point.
     *
     * @param args The user should enter the coordinations of the points one by
     * one starting by first point position on the x axis. The points should be
     * given using the right hand rule.
     */
    public static void main(String args[]) {
        View view = new View();
        Scanner scan = new Scanner(System.in);
        List<Point> points = new LinkedList<>();
        if (args.length == 0 || args.length % 2 != 0 || args.length < 3) {
            int numberOfPoints = 0;
            while (numberOfPoints < 3) {
                view.showMessage("Invalid points. Type number of polygon angles (higher or equal 3): ");
                try {
                    numberOfPoints = scan.nextInt();
                } catch (InputMismatchException e) {
                    view.showError("Error: this is not a number (" + e.getMessage() + ")");
                    return;
                }
            }

            for (int i = 0; i < numberOfPoints; i++) {
                view.showMessage("Type x and y of " + Integer.toString(i + 1) + " point: ");
                float x;
                float y;
                try {
                    x = scan.nextFloat();
                    y = scan.nextFloat();
                } catch (InputMismatchException e) {
                    view.showError("Error: this is not a number (" + e.getMessage() + ")");
                    return;
                }
                points.add(new Point(x, y));
            }
        } else {
            for (int i = 0; i < args.length; i += 2) {
                float x;
                float y;
                try {
                    x = Float.parseFloat(args[i]);
                    y = Float.parseFloat(args[i + 1]);
                } catch (InputMismatchException e) {
                    view.showError("Error: this is not a number (" + e.getMessage() + ")");
                    return;
                }
                points.add(new Point(x, y));
            }
        }
        Polygon polygon = new Polygon(points);
        float result;
        try {
            result = polygon.area();
        } catch (ConcavePolygonException e) {
            view.showError(e.getMessage() + Double.toString(e.getSumOfAngles()));
            return;
        }
        view.showMessage("Area of polygon: " + Float.toString(result));
    }
}
