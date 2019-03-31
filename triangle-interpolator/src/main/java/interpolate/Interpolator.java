/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import geometry.Point;
import geometry.Triangle;
import java.util.ArrayList;
import java.util.HashSet;

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
    public static HashSet<Triangle> triangulate(ArrayList<Point> listOfPoints) {
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
     * @return matrix with interpolated values
     */
    public static double[][] interpolateMatrix(int width, int height, ArrayList<Point> listOfPoints) {
        double[][] output = new double[height][width];
        HashSet<Triangle> validTriangles = new HashSet<>();

        validTriangles = triangulate(listOfPoints);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point currentPosition = new Point(x, y);

                boolean found = false;
                for (Triangle t : validTriangles) {
                    if (t.isPointInsideTriangle(currentPosition)) {
                        double value = t.calcWeightOfPoint(currentPosition);
                        output[y][x] = value;
//                        double classified = calssifyValue(value, 0, 255, 30);
//                        output[y][x] = classified;

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
     * the value by distances to other points. General formula is something like:
     * sum(p.weight / d(p)^p) / sum(1/d(p)^p), where d(p) is distance from
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
     * @return matrix with interpolated values
     */
    public static double[][] interpolateInverseDistance(int width, int height, ArrayList<Point> listOfPoints, double serachRadius, double p) {
        double[][] output = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point currentPosition = new Point(x, y);

                double sum = 0;
                double distances = 0;

                boolean found = false;
                for (Point point : listOfPoints) {
                    double dist = point.calculateDistance(currentPosition);

                    if (dist < 0.0001) {
                        output[y][x] = point.getWeight();
                        found = true;
                        break;
                    } else if (Math.abs(dist - serachRadius) < 0.01) {
                        continue;
                    }

                    dist = Math.pow(dist, p);

                    sum += point.getWeight() / dist;
                    distances += 1 / dist;

                }

                if (found) {
                    continue;
                }

                double result;
                if (distances == 0) {
                    result = Double.NaN;
                }

                result = sum / distances;

                result = classifyValue(result, 0, 255, 25);

                output[y][x] = result;
            }
        }

        return output;
    }

    /**
     * Returns a equal interval classified value of input. That is done by
     * calculating a range of given minium and maximum value and divided by the
     * amount of classes.
     *
     * @param value Value to classify
     * @param minValue Maximum value used in set of values
     * @param maxValue Minium value used in set of values
     * @param classes Amount of classes used in classification
     * @return Classified value
     */
    public static double classifyValue(double value, double minValue, double maxValue, int classes) {
        if (Double.isNaN(value)) {
            return value;
        }

        double interval = (maxValue - minValue) / classes;

        double currentValue = minValue;
        int currenClass;
        for (currenClass = 0; currenClass < classes; currenClass++) {
            if (value >= currentValue && value < currentValue + interval) {
                if (currenClass == 0) {
                    return currentValue;
                }
                return currentValue + interval;
            }
            currentValue += interval;
        }

        if (value == maxValue) {
            return currentValue;
        }

        return currentValue + interval;
    }

}
