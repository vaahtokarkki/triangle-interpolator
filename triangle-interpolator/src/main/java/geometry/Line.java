package geometry;

import java.util.Arrays;

/**
 * Represents a line in 2D plane.
 *
 * Line can be used by given start and end point or for line can be given
 * equation parameters in form of ax + bx = c. If there is no start and end
 * point defined, some features are unavailable.
 *
 * @see geometry.Point
 *
 * @author lroni
 */
public class Line {

    private Point start;
    private Point end;
    private double length;
    private double[] parameters;

    /**
     * Creates a line with given start and end {@link geometry.Point} and
     * calculates line's distance
     *
     *
     * @param start Start point
     * @param end End point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = start.calculateDistance(end);
        this.parameters = this.solveLine();
    }

    /**
     * Creates a line with given parameters. Notice that without start and end
     * point some feature are unavailable.
     *
     * @param parameters Line's equation in form ax + bx = c, where a is the
     * first element in array, b is the second element and c is the third
     * element
     */
    public Line(double[] parameters) {
        this.parameters = parameters;
        this.start = null;
        this.end = null;
        this.length = Double.NaN;
    }

    /**
     * Returns start point
     *
     * @return Start point
     */
    public Point getStart() {
        return start;
    }

    /**
     * Returns end point
     *
     * @return End point
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Returns length of line
     *
     * @return length
     */
    public double getLength() {
        return length;
    }

    public double[] getParameters() {
        return parameters;
    }

    /**
     * Returns the midpoint of this line, that is average of the start and end
     * points
     *
     * @return Point representing midpoint of this line or null if start and end
     * is not defined.
     */
    public Point getMidPoint() {
        if (start == null || end == null) {
            return null;
        }

        double avgX = (start.getX() + end.getX()) / 2;
        double avgY = (start.getY() + end.getY()) / 2;

        return new Point(avgX, avgY);
    }

    /**
     * Returns parameters for line which passes trough start and end point in
     * this object
     *
     * @return line in format ax + by = c, where a is the first element in
     * output array, b is the second element and c is the third element
     */
    public double[] solveLine() {
        double a = end.getY() - start.getY();
        double b = start.getX() - end.getX();
        double c = a * (start.getX()) + b * (start.getY());

        double[] output = {a, b, c};

        return output;
    }

    /**
     * Returns line in perpendicular, that is -bx + ay = c
     *
     * @return line in format ax + by = c, where a is the first element in
     * output array, b is the second element and c is the third element
     */
    public double[] findPerpendicularLine() {
        double a = -parameters[1];
        double b = parameters[0];
        double c = parameters[2];

        double[] output = {a, b, c};

        return output;
    }

    /**
     * Returns line in perpendicular to this line so it goes through given point
     *
     * @param p Point by the perpendicular line should go
     * @return parameters for line in format ax + by = c, where a is the first
     * element in output array, b is the second element and c is the third
     * element
     */
    public double[] findPerpendicularLineByPoint(Point p) {
        double[] values = this.findPerpendicularLine();
        double c = values[0] * p.getX() + values[1] * p.getY();

        double[] output = {values[0], values[1], c};

        return output;
    }

    /**
     * Returns point where this line and the given line intersects.
     *
     * @param line Line to intersect
     * @return the intersect point or null if lines are parallel
     */
    public Point findIntersect(Line line) {
        double[] line2 = line.solveLine();

        double determinant = parameters[0] * line2[1] - line2[0] * parameters[1];

        if (determinant == 0) {
            return null;
        }

        double x = (line2[1] * parameters[2] - parameters[1] * line2[2]) / determinant;
        double y = (parameters[0] * line2[2] - line2[0] * parameters[2]) / determinant;

        return new Point(x, y);
    }

    @Override
    public String toString() {
        return start + " - " + end + " distance: " + length + " eq: " + Arrays.toString(parameters);
    }

}
