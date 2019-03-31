/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import geometry.Line;
import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.*;
import static imageWriter.Writer.*;
import java.util.*;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Point> list = new ArrayList<>();
        Random rand = new Random(1337);

        for (int i = 0; i < 50; i++) {
            int x = rand.nextInt(900 + 1);
            int y = rand.nextInt(900 + 1);
            int value = rand.nextInt((255 - 0) + 1) + 0;
            list.add(new Point(x, y, value));
        }

        HashSet<Triangle> t = triangulate(list);
        double[][] out = interpolateInverseDistance(1000, 1000, list, 300, 1.5);
        writeToImage(out);
//        writeTrianglesToImage(1000, 1000, t);

        /*
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(0, 0, 0));
        list.add(new Point(10, 0, 10));
        list.add(new Point(0, 10, 20));
        list.add(new Point(10, 10, 30));
        
        
        double[][] out = interpolateInverseDistance(10, 10, list, 10, 0);
        for(double[] i : out) {
            System.out.println(Arrays.toString(i));
        }
         */
    }

}
