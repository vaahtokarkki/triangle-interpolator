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
     * @param listOfPoints list of points where to interpolate values
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
    public static double calssifyValue(double value, double minValue, double maxValue, int classes) {
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
