/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Polygon;
import view.View;

/**
 *
 * @author Jakub Nowak gr 5
 */
public class Main 
{
    public static void main(String args[])
    {
        View view = new View();
        if (args == null)
        {
            
        }
        if (args.length % 2 == 0)
        {
            float points[] = new float[args.length];
            for (int i = 0; i < args.length; i++)
            {
                points[i] = Float.parseFloat(args[i]);
            }
            
            Polygon polygon = new Polygon(points);
            float result = polygon.calculateArea();
            view.showResult(Float.toString(result));
        }
        else
        {
            view.showError("odd number of points");
        }
    }
}
