/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jakub Nowak gr 5
 */
public class Polygon {
    private final float points[];
    
    public Polygon(float points[])
    {
        int numberOfPoints = points.length;
        this.points = new float[numberOfPoints];
        System.arraycopy(this.points, 0, points, 0, 1);
    }
    /**
     * 
     * @return area of a polygon
     */
    public float calculateArea()
    {
        float result = 0;
        //here calculations
        return result;
    }
}
