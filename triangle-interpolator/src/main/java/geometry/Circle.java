/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

/**
 * Used in Triangle to check if the triangle is a valid delaunay triangle
 *
 * @author lroni
 */
public class Circle {

    private Point centre;
    private double radius;

    /**
     * Creates a circle with given centre and radius
     *
     * @param centre Point where circle's centre is
     * @param radius Radius of circle
     */
    public Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Returns points which are inside or on the circle
     *
     * @param listOfPoints list of points where to search
     * @return array of points found, in none found returns empty array
     */
    public Point[] findPointsInside(Point[] listOfPoints) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns points which are inside or on the circle and filters out from
     * results if given points are found
     *
     * @param listOfPoints list of points where to search
     * @param listOfPointsToExclude list of points which to exclude from output
     * @return array of points found, in none found returns empty array
     */
    public Point[] findPointsInside(Point[] listOfPoints, Point[] listOfPointsToExclude) {
        throw new UnsupportedOperationException();
    }

}
