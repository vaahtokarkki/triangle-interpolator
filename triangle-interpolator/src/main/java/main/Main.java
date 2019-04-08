package main;

import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.*;
import static io.Writer.*;
import java.util.*;
import utils.CsvParse;
import utils.MyArrayList;
import utils.MyHashSet;
import utils.MyMath;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {
        MyHashSet<Integer> set = new MyHashSet<>();
        for(int i=0;i<100;i++) {
            set.add(i);
        }
        System.out.println(set);
        for(int i:set) {
            System.out.println(i);
        }
        
        /*
        CsvParse parser = new CsvParse("hki.csv");
        MyArrayList<Point> list = parser.parsePointsFromFile(";", "x", "y", "weight");
        list = MyMath.moveCoordinatesToOrigin(list);
        list = MyMath.scaleCoordinates(1000, 1000, list);

        System.out.println("Generating Delaunay triangles");
        HashSet<Triangle> t = triangulate(list);
        System.out.println(t);
        System.out.println("Interpolating with barycentric coordinates");
//        double[][] barycentricInterpolation = interpolateMatrix(1000, 1000, list, 25);
        System.out.println("Interpolating with inverse distance weighting");
        double[][] idwInterpolation = interpolateInverseDistance(1000, 1000, list, 300, 2, 35);

        System.out.println("Writing barycentric");
//        writeToGrayscaleImage(barycentricInterpolation, "barycentric_test.jpg");
        System.out.println("Writing idw");

        writeToGrayscaleImage(idwInterpolation, "idw_test.jpg");
        writeTrianglesToImage(1000, 1000, t, "triangles.jpg");
        System.out.println("writing triangles");
         */

    }

}
