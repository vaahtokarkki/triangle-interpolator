/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import utils.MyArrayList;
import utils.MyHashSet;

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
        if (centre == null) {
            return;
        }

        this.centre = centre;
    }

    public void setRadius(double radius) {
        if (radius < 0) {
            return;
        }

        this.radius = radius;
    }

    public Point getCentre() {
        return centre;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Returns true if point is inside circle and false if it is outside. If the
     * point is on the circle's line, it is considered as inside.
     *
     * @param p Point to check
     * @return boolean value is the point inside circle
     */
    public boolean isPointInside(Point p) {
        if (p == null) {
            return false;
        }

        double xDelta = Math.pow(p.getX() - centre.getX(), 2);
        double yDelta = Math.pow(p.getY() - centre.getY(), 2);
        double radiusSquare = Math.pow(radius, 2);

        return yDelta + xDelta <= radiusSquare;
    }

    /**
     * Returns points which are inside or on the circle
     *
     * @param listOfPoints list of points where to search
     * @return array of points found, in none found returns empty array
     */
    public MyArrayList<Point> findPointsInside(MyArrayList<Point> listOfPoints) {
        MyArrayList<Point> output = new MyArrayList<>();

        if (listOfPoints == null || listOfPoints.size() == 0) {
            return output;
        }

        for (int i = 0; i < listOfPoints.size(); i++) {
            Point p = listOfPoints.get(i);
            if (this.isPointInside(p)) {
                output.add(p);
            }
        }

        return output;
    }

    /**
     * Returns points which are inside or on the circle and filters out from
     * results if given points are found
     *
     * @param listOfPoints list of points where to search
     * @param setOfPointsToExclude list of points which to exclude from output
     * @return array of points found, in none found returns empty array
     */
    public MyArrayList<Point> findPointsInside(MyArrayList<Point> listOfPoints, MyHashSet<Point> setOfPointsToExlude) {
        MyArrayList<Point> pointsInsideCircle = this.findPointsInside(listOfPoints);

        if (setOfPointsToExlude == null || setOfPointsToExlude.size() == 0) {
            return pointsInsideCircle;
        }

        if (pointsInsideCircle.size() == 0) {
            return pointsInsideCircle;
        }

        MyArrayList<Point> output = new MyArrayList<>();

        for (int i = 0; i < pointsInsideCircle.size(); i++) {
            Point p = pointsInsideCircle.get(i);
            if (!setOfPointsToExlude.contains(p)) {
                output.add(p);
            }
        }

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Circle c = (Circle) o;

        return c.centre == this.centre && c.radius == this.radius;
    }

    @Override
    public String toString() {
        return "centre: " + centre + ", radius: " + radius;
    }

}
