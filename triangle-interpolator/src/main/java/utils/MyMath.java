/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import geometry.Point;
import java.util.ArrayList;

/**
 *
 * @author lroni
 */
public class MyMath {

    final static int INF = 9999999;

    public static double pow(double value, double exp) {
        throw new UnsupportedOperationException();
    }

    public static double sqrt(double value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the absolute value of give double.
     *
     * @param value value
     * @return absolute value of given value
     */
    public static double abs(double value) {
        return value >= 0 ? value : -value;
    }

    /**
     * Returns corner points for minimum bounding box for given set of points.
     *
     * @param points
     * @return Array of points in clockwise order, that is: top left, top right,
     * bottom right, bottom left
     */
    public static Point[] findMinimumBoundinBox(ArrayList<Point> points) {
        if (points.size() == 0) {
            return null;
        }

        double max_x = -INF;
        double max_y = -INF;
        double min_x = INF;
        double min_y = INF;

        for (Point p : points) {
            max_x = (p.getX() > max_x) ? p.getX() : max_x;
            max_y = (p.getY() > max_y) ? p.getY() : max_y;

            min_x = (p.getX() < min_x) ? p.getX() : min_x;
            min_y = (p.getY() < min_y) ? p.getY() : min_y;
        }

        Point topLeft = new Point(min_x, max_y);
        Point topRight = new Point(max_x, max_y);
        Point bottomRight = new Point(max_x, min_y);
        Point bottomLeft = new Point(min_x, min_y);

        Point[] output = {topLeft, topRight, bottomRight, bottomLeft};

        return output;
    }

    /**
     * Returns list of points which minimum bounding box is moved to origin.
     * This is done calculating first minimum bounding box for given list of
     * points and the calculating the delta of x and y coordinates. Origin is
     * assumed to be top left, so the calculated bounding box's top left point
     * is mapped to point (0,0).
     *
     * @param points list of points to shift
     * @return new list of given points shifted
     */
    public static ArrayList<Point> moveCoordinatesToOrigin(ArrayList<Point> points) {
        Point[] boundinBox = findMinimumBoundinBox(points);

        if (boundinBox == null) {
            return null;
        }

        ArrayList<Point> output = new ArrayList<>();

        double deltaX = boundinBox[3].getX();
//        double deltaY = boundinBox[0].getY()-boundinBox[3].getY();

        System.out.println("delta x: " + deltaX + " delta y: ");

        for (Point p : points) {

            double newX = p.getX() - deltaX;
            double newY = boundinBox[0].getY() - p.getY();

            Point shiftedPoint = new Point(newX, newY, p.getWeight());
            output.add(shiftedPoint);
        }

        return output;
    }

}
