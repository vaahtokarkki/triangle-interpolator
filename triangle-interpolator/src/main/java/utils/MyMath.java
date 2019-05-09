package utils;

import geometry.Point;

/**
 * Math functions
 */
public class MyMath {

    public final static int INF = 999999999;
    public final static double PI = 3.141592653589793;

    /**
     * Method calculates first value raised to the power of second value. Gives
     * approx (0.0001 error) correct answers bellow numbers like 2^33.
     *
     * If value is bellow zero and exponent is not an integer, result id NaN
     *
     * In general form a^b is a^c * a^n/m, where c is integer part of exponent
     * and n/m is fractal representation of remaining. a^c * a^n/m is same as
     * a^c * (m:th root of a)^n
     *
     * @param value base
     * @param exp exponent
     * @return value base to the power of exponent
     */
    public static double pow(double value, double exp) {
        if (exp == 0) {
            return 1;
        }

        int integerPart = (int) abs(exp);
        double decimalPart = abs(exp) - integerPart;

        if (value < 0 && decimalPart != 0) {
            return Double.NaN;
        }

        if (decimalPart == 0) {
            return pow(value, (int) exp);
        }

        double[] fractions = decompose(decimalPart);

        double integerPow = pow(value, integerPart);

        double nthRootOfValue = nthRoot(value, (int) fractions[1]);

        if (fractions[0] > 0) {
            nthRootOfValue = pow(nthRootOfValue, (int) fractions[0]);
        }

        if (exp < 0) {
            return 1 / (integerPow * nthRootOfValue);
        }

        return integerPow * nthRootOfValue;

    }

    /**
     * Method calculates first value raised to the power of second value. Gives
     * approx (0.0001 error) correct answers bellow numbers like 13^13.
     *
     * TODO: Better accuracy
     *
     * @param value base
     * @param exp exponent
     * @return value base to the power of exponent
     */
    public static double pow(double value, int exp) {
        if (exp == 0) {
            return 1;
        }

        double result = value;

        for (int i = 1; i < abs(exp); i++) {
            result *= value;
        }

        if (exp < 0) {
            return 1 / result;
        }

        return result;
    }

    /**
     * Calculates nth root of given value. Uses Newton's iterative method.
     *
     * @see MyMath#sqrt(double)
     *
     * @param value value
     * @param root nth root
     * @return nth root of given value
     */
    private static double nthRoot(double value, int root) {
        final double PRECISION = 0.00000000001;

        if (value < 0) {
            return -1;
        } else if (value == 0) {
            return 0;
        }

        double x1 = value;
        double x2 = value / root;

        while (MyMath.abs(x1 - x2) > PRECISION) {
            x1 = x2;
            x2 = ((root - 1.0) * x2 + value / Math.pow(x2, root - 1)) / root;
        }

        return x2;
    }

    /**
     * Return square root of given value. This is done by .
     * <a href="https://en.wikipedia.org/wiki/Newton%27s_method#Square_root_of_a_number">Newton's
     * iterative method</a>
     *
     * This implementation is a bit slower, but talking about tenth or hundredth
     * of millisecond.
     *
     * @param value value to calculate square root for
     * @return square root value, or NaN if value is negative
     */
    public static double sqrt(double value) {
        if (Double.isNaN(value) || value < 0) {
            return Double.NaN;
        } else if (value == 0) {
            return 0;
        }

        double guess = 0.5 * value;
        double estimate = 0.5 * (guess + value / guess);
        while (guess != estimate) {
            guess = estimate;
            estimate = 0.5 * (guess + value / guess);
        }
        return guess;
    }

    /**
     * Converts degrees to radians.
     *
     * @param a input degrees
     * @return given degrees as radians
     */
    public static double toRadians(double a) {
        return a * PI / 180;
    }

    public static double sin(double a) {
        throw new UnsupportedOperationException();
    }

    public static double cos(double a) {
        throw new UnsupportedOperationException();
    }

    public static double asin(double a) {
        throw new UnsupportedOperationException();
    }

    /**
     * Decompose a double to fractions, for example 0.2 -> 1/5. This is done
     * with <a href="https://en.wikipedia.org/wiki/Continued_fraction">continued
     * fractions</a>.
     *
     * @param x value to decompose
     * @return an array where [top, bottom]
     */
    private static double[] decompose(double x) {
        final double PRECISION = 0.00000000001;
        double top = 1, tempTop = 0;
        double bottom = 0, tempBottom = 1;
        double guess = x;

        while (MyMath.abs(x - top / bottom) > x * PRECISION) {
            double newGuess = (int) guess;
            double helper = top;

            top = newGuess * top + tempTop;
            tempTop = helper;
            helper = bottom;
            bottom = newGuess * bottom + tempBottom;
            tempBottom = helper;

            guess = 1 / (guess - newGuess);

        }

        double[] output = {top, bottom};
        return output;
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
     * Returns given value rounded to nearest integer. If value is negative and
     * distance to nearest integer is same, value is rounded towards zero.
     *
     * @param value value to round
     * @return rounded value
     */
    public static int round(double value) {
        boolean negative = value < 0;

        int integerPart = (int) abs(value);

        double decimalPart = abs(value) - integerPart;

        if (negative) {
            if (decimalPart > 0.5) {
                return -(integerPart + 1);
            }
            return -integerPart;
        }

        if (decimalPart >= 0.5) {
            return integerPart + 1;
        }
        return integerPart;

    }

    /**
     * Returns given value rounded to given precision
     *
     * @param x value to round
     * @param precision precision, that is how many decimals
     * @return rounded value
     */
    public static double round(double x, int precision) {
        double p = 1.0;
        for (int i = 0; i < precision; i++) {
            p *= 10;
        }

        return MyMath.round(x * p) / p;
    }

    /**
     * Returns largest integer that is less or equal to given value.
     *
     * @param x value to floor
     * @return floored value
     */
    public static int floor(double x) {
        return (int) x;
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
     * Returns the absolute value of give double.
     *
     * @param value
     * @return
     */
    public static int abs(int value) {
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

        double[] scalingCoefficients = getScalingCoefficients(points);
        double coefficientX = scalingCoefficients[0];
        double coefficientY = scalingCoefficients[1];

        MyArrayList<Point> pointsMovedToOrigin = moveCoordinatesToOrigin(points);
        double[] minAndMaxCoordinates = findMinAndMaxCoordinates(pointsMovedToOrigin);

        double xMax = minAndMaxCoordinates[0];
        double yMax = minAndMaxCoordinates[2];
        double xMin = minAndMaxCoordinates[1];
        double yMin = minAndMaxCoordinates[3];

        double width2 = (xMax - xMin) * coefficientX;
        double height2 = (yMax - yMin) * coefficientY;

        double scalingFactor = MyMath.min((width / width2), (height / height2));

        double translateX = MyMath
                .abs((xMax * scalingFactor * coefficientX) - (xMin * scalingFactor * coefficientX) - width) / 2;
        double translateY = MyMath
                .abs((yMax * scalingFactor * coefficientY) - (yMin * scalingFactor * coefficientY) - height) / 2;

        MyArrayList<Point> output = new MyArrayList<>();
        for (int i = 0; i < pointsMovedToOrigin.size(); i++) {
            Point p = pointsMovedToOrigin.get(i);

            double x = p.getX() * (scalingFactor * coefficientX) + translateX;
            double y = p.getY() * (scalingFactor * coefficientY) + translateY;

            Point scaledPoint = new Point(x, y, p.getWeight());
            output.add(scaledPoint);
        }

        return output;
    }

    /**
     * Returns scaling coefficients for X and Y axis. Coefficients keeps aspect
     * ratio correct.
     *
     * @param points list of points where to get the coefficients
     * @return 2 element array as [coefficientX, coefficientY]
     */
    private static double[] getScalingCoefficients(MyArrayList<Point> points) {
        double[] minAndMaxCoordinates = findMinAndMaxCoordinates(points);

        double xMax = minAndMaxCoordinates[0];
        double yMax = minAndMaxCoordinates[2];
        double xMin = minAndMaxCoordinates[1];
        double yMin = minAndMaxCoordinates[3];

        Point p1 = new Point(xMin, yMin);
        Point p2 = new Point(xMax, yMin);
        Point p3 = new Point(xMin, yMax);

        // Original width and height in km
        double widthInKm = p1.calculateHaversineDistance(p2);
        double heightInKm = p1.calculateHaversineDistance(p3);

        double coefficientX, coefficientY;
        if (widthInKm > heightInKm) {
            coefficientX = widthInKm / heightInKm;
            coefficientY = 1;
        } else {
            coefficientY = heightInKm / widthInKm;
            coefficientX = 1;
        }

        double[] output = {coefficientX, coefficientY};

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

}
