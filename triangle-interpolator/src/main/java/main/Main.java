package main;

import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.*;
import static io.Writer.*;
import java.util.*;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Point> list = new ArrayList<>();
        Random rand = new Random(1337); //Random() only for testing 

        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(900 + 1);
            int y = rand.nextInt(900 + 1);
            int value = rand.nextInt((255 - 100) + 1) + 100;
            list.add(new Point(x, y, value));
        }
        
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
