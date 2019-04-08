package interpolate;

import geometry.Point;
import geometry.Triangle;
import java.util.Arrays;
import java.util.HashSet;
import utils.MyArrayList;
import utils.MyMath;

/**
 *
 * @author lroni
 */
public class Interpolator {

    /**
     * Creates a set of valid Delaunay triangles based on given list of points.
     * This algorithm runs in O(n^3) time.
     *
     * @param listOfPoints list of points where to create Delaunay triangles
     * @return set of Delaunay triangles, null if there is none
     */
    public static HashSet<Triangle> triangulate(MyArrayList<Point> listOfPoints) {
        HashSet<Triangle> validTriangles = new HashSet<>();

        for (int i = 0; i < listOfPoints.size(); i++) {
            for (int z = 0; z < listOfPoints.size(); z++) {
                for (int n = 0; n < listOfPoints.size(); n++) {
                    Point p1 = listOfPoints.get(i);
                    Point p2 = listOfPoints.get(z);
                    Point p3 = listOfPoints.get(n);

                    Triangle t = new Triangle(p1, p2, p3);
                    if (t.isValidDelaunay(listOfPoints)) {
                        validTriangles.add(t);
                    }
                }
            }
        }
        return validTriangles;
    }

    /**
     * Interpolates values to a matrix by given list of points. Algorithm first
     * creates a set of valid Delaunay triangles by given list of points. With
     * triangles it calculates using barycentric coordinates a value for every
     * element in matrix. If the pixel is outside of any triangle it gets value
     * "NaN". After interpolation this matrix can be processed to a image file.
     *
     * @param width width of output matrix
     * @param height height of output matrix
     * @param listOfPoints list of points from to interpolate values
     * @param classes in how many different values interpolated values are
     * classified, usually between 10-50
     * @return matrix with interpolated values
     */
    public static double[][] interpolateMatrix(int width, int height, MyArrayList<Point> listOfPoints, int classes) {
        double[][] output = new double[height][width];
        HashSet<Triangle> validTriangles = new HashSet<>();

        validTriangles = triangulate(listOfPoints);

        double[] minAndMaxValues = MyMath.getMaxAndMinValues(listOfPoints);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point currentPosition = new Point(x, y);

                boolean found = false;
                for (Triangle t : validTriangles) {
                    if (t.isPointInsideTriangle(currentPosition)) {
                        double value = t.calcWeightOfPoint(currentPosition);
                        double classified = classifyValue(value, minAndMaxValues, classes);
                        classified = getGrayscaleValueForClass(classified, classes);
                        output[y][x] = classified;
                        found = true;
                        break;
                    }

                }

                if (found) {
                    continue;
                }

                output[y][x] = Double.NaN;
            }
        }
        return output;
    }

    /**
     * Interpolates values to a matrix by given list of points with inverse
     * distance weight method. This method calculates values for element in
     * matrix by taking all points inside the given search radius and calculates
     * the value by distances to other points. General formula is something
     * like: sum(p.weight / d(p)^p) / sum(1/d(p)^p), where d(p) is distance from
     * current element to the point inside search radius.
     *
     * If there is not any points inside search radius, element gets value NaN
     *
     *
     *
     * @param width width of output matrix
     * @param height height of output matrix
     * @param listOfPoints list of points from to interpolate values
     * @param serachRadius search radius, that is how far away points affect
     * interpolation
     * @param p distance to the power of p, usually value between 1 and 2
     * @param classes in how many different values interpolated values are
     * classified, usually between 10-50
     * @return matrix with interpolated values
     */
    public static double[][] interpolateInverseDistance(int width, int height, MyArrayList<Point> listOfPoints, double serachRadius, double p, int classes) {
        double[][] output = new double[height][width];
        double[] minAndMaxValues = MyMath.getMaxAndMinValues(listOfPoints);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point currentPosition = new Point(x, y);

                double sum = 0;
                double distances = 0;

                for (int i = 0; i < listOfPoints.size(); i++) {
                    Point point = listOfPoints.get(i);
                    double dist = point.calculateDistance(currentPosition);

                    if (MyMath.abs(dist - serachRadius) < 0.1) {
                        continue;
                    }

                    dist = Math.pow(dist, p);

                    sum += point.getWeight() / dist;
                    distances += 1 / dist;

                }

                if (distances == 0) {
                    output[y][x] = Double.NaN;
                    continue;
                }

                double result = sum / distances;

                result = classifyValue(result, minAndMaxValues, classes);
                result = getGrayscaleValueForClass(result, classes);

                output[y][x] = result;
            }
        }
        

        return output;
    }

    /**
     * Returns a equal interval classified value of input. That is done by
     * calculating a range of given minium and maximum value and divided by the
     * amount of classes. Output is integer between 0 and amount of classes.
     *
     * @param value Value to classify
     * @param minAndMaxValues arrays of min and max values used in
     * interpolation, see {@link MyMath#getMaxAndMinValues(utils.MyArrayList)}
     * @param classes Amount of classes used in classification
     * @return Classified value, or -1 if value is NaN
     */
    public static int classifyValue(double value, double[] minAndMaxValues, int classes) {
        if (Double.isNaN(value) || minAndMaxValues.length < 2) {
            return -1;
        }

        double minValue = minAndMaxValues[0];
        double maxValue = minAndMaxValues[1];

        double interval = ((maxValue - minValue) + 1) / classes;

        double currentValue = minValue;
        int currentClass;
        for (currentClass = 0; currentClass < classes; currentClass++) {
            if (value >= currentValue && value < currentValue + interval) {
                if (currentClass == 0) {
                    return 0;
                }
                return currentClass;
            }
            currentValue += interval;
        }

        return classes;
    }

    /**
     * Returns a grayscale value for given class. Values are in range 0-255,
     * class 0 is always value of 0 and highest class is always value of 255.
     *
     *
     * @param classifiedValue class to get grayscale value for
     * @param classes amount of classes used
     * @return value between 0 and 255, if classes is 0 returns -1
     */
    public static double getGrayscaleValueForClass(double classifiedValue, int classes) {
        if (classes <= 0) {
            return -1;
        }

        if (classifiedValue == 0 || classes == 1) {
            return 0;
        } else if ((int) classifiedValue == classes - 1 || (int) classifiedValue == classes) {
            return 255;
        }

        double width = 255.0 / classes;

        double currentValue = width;
        for (int i = 0; i < classes; i++) {
            if (i == classifiedValue) {
                return (int) currentValue;
            }
            currentValue += width;
        }

        return currentValue;
    }

}
