package main;

import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.*;
import static io.Writer.*;
import java.util.*;
import utils.CsvParse;
import utils.MyArrayList;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {

        CsvParse parser = new CsvParse("test_data.csv");
        MyArrayList<Point> list = parser.parsePointsFromFile(";", "x", "y", "weight");

        System.out.println("Generating Delaunay triangles");
        HashSet<Triangle> t = triangulate(list);
        System.out.println("Interpolating with barycentric coordinates");
        double[][] barycentricInterpolation = interpolateMatrix(1000, 1000, list);
        System.out.println("Interpolating with inverse distance weighting");
        double[][] idwInterpolation = interpolateInverseDistance(1000, 1000, list, 200, 1.5);

        writeToGrayscaleImage(barycentricInterpolation, "barycentric_test.jpg");
        writeToGrayscaleImage(idwInterpolation, "idw_test.jpg");
        writeTrianglesToImage(1000, 1000, t, "triangles.jpg");
    }

}
