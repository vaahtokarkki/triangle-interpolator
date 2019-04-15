package utils;

import geometry.Point;

/**
 * Math functions
 */
public class MyMath {

    final static int INF = 999999999;

    public static double pow(double value, double exp) {
        throw new UnsupportedOperationException();
    }

    public static double sqrt(double value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns maximum of given two values
     *
     * @param a value 1
     * @param b value 2
     * @return maximum of a and b
     */
    public static double max(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    /**
     * Returns minimum of given two values
     *
     * @param a value 1
     * @param b value 2
     * @return minimum of a and b
     */
    public static double min(double a, double b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    /**
     * Returns given value rounded to nearest integer.
     *
     * @param value value to round
     * @return rounded value
     */
    public static int round(double value) {
        boolean negative = value < 0 ? true : false;

        int integerPart = (int) abs(value);
        double decimalPart = abs(value) - integerPart;

        if (decimalPart < 0.5) {
            if (negative) {
                return -integerPart;
            }
            return integerPart;
        }

        if (negative) {
            return -(integerPart + 1);
        }
        return integerPart + 1;
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
    public static Point[] findMinimumBoundingBox(MyArrayList<Point> points) {
        if (points.size() == 0) {
            return null;
        }

        double[] minAndMaxCoordinates = findMinAndMaxCoordinates(points);

        double xMax = minAndMaxCoordinates[0];
        double yMax = minAndMaxCoordinates[2];
        double xMin = minAndMaxCoordinates[1];
        double yMin = minAndMaxCoordinates[3];

        Point topLeft = new Point(xMin, yMax);
        Point topRight = new Point(xMax, yMax);
        Point bottomRight = new Point(xMax, yMin);
        Point bottomLeft = new Point(xMin, yMin);

        Point[] output = {topLeft, topRight, bottomRight, bottomLeft};

        return output;
    }

    private static double[] findMinAndMaxCoordinates(MyArrayList<Point> points) {
        double xMax = -INF;
        double yMax = -INF;
        double xMin = INF;
        double yMin = INF;

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);

            xMax = (p.getX() > xMax) ? p.getX() : xMax;
            yMax = (p.getY() > yMax) ? p.getY() : yMax;

            xMin = (p.getX() < xMin) ? p.getX() : xMin;
            yMin = (p.getY() < yMin) ? p.getY() : yMin;
        }

        double[] output = {xMax, xMin, yMax, yMin};

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
    public static MyArrayList<Point> moveCoordinatesToOrigin(MyArrayList<Point> points) {
        Point[] boundingBox = findMinimumBoundingBox(points);

        if (boundingBox == null) {
            return null;
        }

        MyArrayList<Point> output = new MyArrayList<>();

        double deltaX = boundingBox[3].getX();

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);

            double newX = p.getX() - deltaX;
            double newY = boundingBox[0].getY() - p.getY();

            Point shiftedPoint = new Point(newX, newY, p.getWeight());
            output.add(shiftedPoint);
        }

        return output;
    }

    /**
     * Returns a new list of points scaled to given width and height. That is
     * the points are scaled to fill the given width and height matrix.
     *
     * @param width
     * @param height
     * @param points
     * @return
     */
    public static MyArrayList<Point> scaleCoordinates(int width, int height, MyArrayList<Point> points) {
        if (points == null) {
            return null;
        }

        if ((width == 0 && height == 0) || points.size() == 0) {
            return points;
        }

        double[] minAndMaxCoordinates = findMinAndMaxCoordinates(points);

        double xMax = minAndMaxCoordinates[0];
        double yMax = minAndMaxCoordinates[2];
        double xMin = minAndMaxCoordinates[1];
        double yMin = minAndMaxCoordinates[3];

        //Original width and height
        double width2 = xMax - xMin;
        double height2 = yMax - yMin;

        double scalingFactor = MyMath.min((width / width2), (height / height2));

        MyArrayList<Point> output = new MyArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);

            double x = (p.getX() - xMin) * scalingFactor;
            double y = (p.getY() - yMin) * scalingFactor;

            Point scaledPoint = new Point(x, y, p.getWeight());
            output.add(scaledPoint);
        }

        return output;
    }

    /**
     * Returns max and min weights from given list of points.
     *
     * @param points list of points
     * @return an array as [min, max]
     */
    public static double[] getMaxAndMinValues(MyArrayList<Point> points) {
        if (points == null || points.size() == 0) {
            return null;
        }

        double maxWeight = -INF;
        double minWeight = INF;

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            maxWeight = max(maxWeight, p.getWeight());
            minWeight = min(minWeight, p.getWeight());
        }

        double[] output = {minWeight, maxWeight};

        return output;

    }

}
