package model;

/**
 * Polygon class represents polygon in a 2d space.
 *
 * @author Jakub Nowak gr 5
 * @version 1.0
 */
public class Polygon {
     /** 
      * Points of the polygon.
      */
    private Point points[];
     /** 
      * Sum of angles of a polygon.
      */
    private double sumOfAngles;
     /** 
      * Method checks if a value is out of bounds. Used for having an access to a first or last point of a table in iterations e.g.: "i+1" may be a value and the "tab.length" is bounds.
      * 
      * @param value value to check
      * @param bounds range of a value
      * @return difference of value and bounds if value is higher or equal than bounds, sum of value and bounds when value is lower than 0, value otherwise.
      */
    private int checkIfOutOfBounds(int value, int bounds)
    {
        if (value >= bounds)
        {
            return value - bounds;
        }
        else if (value < 0)
        {
            return bounds + value;
        }
        else
            return value;
    }
    /** 
      * Method checks if a value is out of bounds. Used for having an access to a first or last point of a table in iterations e.g.: "i+1" may be a value and the "tab.length" is bounds.
      * 
      * @param sumOfAngles sum of angles of polygon.
      * @return true if polygon is convex, false if polygon is concave.
      */
    private boolean polygonIsConvex(double sumOfAngles)
    {
        return (sumOfAngles == (points.length-2)*180);
    }
    /** 
     * Class constructor forming a 2x2 square with the bottom left corner at the coordinate system origin.
     */
    public Polygon() 
    {
        points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 2);
        points[2] = new Point(2, 2);
        points[3] = new Point(2, 0);
        for (int i = 0; i < points.length; i++)
        {
            sumOfAngles += points[i].angle(points[checkIfOutOfBounds(i-1, points.length)], points[checkIfOutOfBounds(i+1, points.length)]);
        }
    }
    /** 
     * Class constructor forming a polygon from given points.
     * @param points points of polygon
     */        
    public Polygon(Point points[])
    {
        this.points = new Point[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
        for (int i = 0; i < points.length; i++)
        {
            sumOfAngles += points[i].angle(points[checkIfOutOfBounds(i-1, points.length)], points[checkIfOutOfBounds(i+1, points.length)]);
        }
    }
    /**
     * Method calculates area of a polygon.
     * @throws ConcavePolygonException when polygon is concave, which means its area cannot be calculated.
     * @return area of a polygon
     */
    public float area() throws ConcavePolygonException
    {
        if (!polygonIsConvex(sumOfAngles))
            throw new ConcavePolygonException("given polygon is convex, sum of angles is equal: ", sumOfAngles);
        
        float area = 0;
        Triangle triangles[] = new Triangle[points.length - 2];
        int pC = 0; //points counter
        int nextPoint = 1;
        for (int i = 0; i < points.length - 2; i++)
        {
            triangles[i] = new Triangle(points[pC], points[checkIfOutOfBounds(pC + nextPoint, points.length)], points[checkIfOutOfBounds(pC + nextPoint*2, points.length)]);
            pC += 2;
            if (pC == points.length)
            {
                nextPoint *= 2;
                pC = 0;
            }
            else if (pC + 1 == points.length)
            {
                nextPoint *= 2;
                pC--;
            }
            area += triangles[i].area();
        }
        
        return area;
    }
}
