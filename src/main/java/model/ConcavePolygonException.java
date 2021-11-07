package model;

/**
 * Class represents an exeption that should be thrown when area of a concave polygon is calculated.
 * 
 * @author Jakub Nowak gr 5
 * @version 1.0
 */
public class ConcavePolygonException extends Exception {
    /** 
     * Sum of an angles of a concave polygon.
     */
    private double sumOfAngles;
    /** 
     * Class constructor.
     */
    public ConcavePolygonException() {};
    /** 
     * Class constructor with a message.
     * @param message message of an exception
     */
    public ConcavePolygonException(String message)
    {
        super(message);
    }
    /** 
     * Class constructor with a message and sum of angles.
     * @param message message of an exception
     * @param sumOfAngles sum of angles of a concave polygon
     */
    public ConcavePolygonException(String message, double sumOfAngles)
    {
        super(message);
        this.sumOfAngles = sumOfAngles;
    }
    /** 
     * Method returns sum of angles.
     * @return sum of angles of a concave polygon
     */
    public double getSumOfAngles()
    {
        return sumOfAngles;
    }
}
