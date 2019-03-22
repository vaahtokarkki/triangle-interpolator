package utils;

import java.util.*;

/**
 *
 * Represents a point in 2D plane
 *
 *
 */
public class Point {

    private int x;
    private int y;
    private double weight;

    /**
     * Creates a point with x and y coordinates and specified weight
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param weight weight or value assigned to point
     */
    public Point(int x, int y, double weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    /**
     * Gets point's x-coordinate
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets point's y-coordinate
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets point's weight
     *
     * @return weight or value
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets point's x-coordinate
     *
     * @param x new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets point's y-coordinate
     *
     * @param y new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets point's weight
     *
     * @param weight new weight or value
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Calculates distance from this point to given point
     *
     * @param point Point where to calculate distance from this point
     * @return distance
     */
    public double calculateDistance(Point point) {
        int deltaX = point.x - this.x;
        int deltaY = point.y - this.y;

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    /**
     * Creates and sorts an array array of given points by their distance to
     * this point, ordered by closest point first
     *
     * @param listOfPoints array of points to sort
     * @return new sorted array of points
     */
    public Point[] findClosestPoints(Point[] listOfPoints) {
        int n = listOfPoints.length;
        Point[] output = new Point[n];
        ArrayList<Line> listOfDistances = new ArrayList<>();

        for (Point p : listOfPoints) {
            listOfDistances.add(new Line(this, p));
        }

        Collections.sort(listOfDistances, new Comparator<Line>() {
            public int compare(Line line1, Line line2) {
                if (line1.getLength() < line2.getLength()) {
                    return -1;
                }

                if (line1.getLength() > line2.getLength()) {
                    return 1;
                }

                return 0;
            }
        });

        for (int i = 0; i < n; i++) {
            output[i] = listOfDistances.get(i).getEnd();
        }

        return output;
    }

    @Override
    public String toString() {
        return "x: " + this.x + " y: " + this.y + ", weight: " + this.weight;
    }

}
