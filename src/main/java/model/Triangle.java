package model;

/**
 * Triangle class represents triangle in a 2d space.
 *
 * @author Jakub Nowak gr 5
 * @version 1.0
 */
public class Triangle {
     /** 
      * First point of a triangle.
      */
    private Point a;
     /** 
      * Second point of a triangle.
      */
    private Point b;
     /** 
      * Third point of a triangle.
      */
    private Point c;
    /** 
    * Class constructor forming a equilateral triangle of height 2 with the bottom left corner at the coordinate system origin.
    */
    public Triangle() 
    {
        a = new Point(0, 0);
        b = new Point(1, 2);
        c = new Point(2, 0);
    }
    /** 
    * Class constructor forming a triangle from three given points in a 2d space.
    */
    public Triangle(Point a, Point b, Point c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /** 
    * Method calculating area of a triangle.
    */
    public float area()
    {
        float dif1 = (b.getX() - a.getX())*(c.getY() - a.getY());
        float dif2 = (b.getY() - a.getY())*(c.getX() - a.getX());
        return Math.abs(dif1 - dif2)/2;
    }
}
