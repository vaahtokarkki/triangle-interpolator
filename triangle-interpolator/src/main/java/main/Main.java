/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.*;
import static imageWriter.Writer.*;
import java.util.*;
import utils.CsvParse;
import utils.MyArrayList;
import utils.MyHashSet;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {
        /*
        ArrayList<Point> list = new ArrayList<>();
        Random rand = new Random(1337);

        for (int i = 0; i < 150; i++) {
            int x = rand.nextInt(900 + 1);
            int y = rand.nextInt(900 + 1);
            int value = rand.nextInt((255 - 100) + 1) + 100;
            list.add(new Point(x, y, value));
        }

        HashSet<Triangle> t = triangulate(list);
//        double[][] out = interpolateMatrix(1000, 1000, list);
        double[][] out = interpolateInverseDistance(1000, 1000, list, 100, 1.5);

        writeToImage(out);
        writeTrianglesToImage(1000, 1000, t);
         */
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
//        Point P1 = new Point(30, 50, 1);
//        Point P2 = new Point(50, 40, 2);
//        Point P3 = new Point(90, 30, 3);
//        Point P4 = new Point(20, 20, 4);
//
//        ArrayList<Point> list = new ArrayList<>();
//        list.add(P1);
//        list.add(P2);
//        list.add(P3);
//        list.add(P4);
//        
//        ArrayList<Point> moved = MyMath.moveCoordinatesToOrigin(list);
//        
//        System.out.println(Arrays.toString(MyMath.findMinimumBoundinBox(list)));
//        System.out.println(list);
//        System.out.println(moved);
//        System.out.println(Arrays.toString(MyMath.findMinimumBoundinBox(moved)));
        Point p1 = new Point(0, 0, 10);
        Point p2 = new Point(3, 3, 30);
        Point p3 = new Point(6, 0, 10);
        
        Point pp = new Point(0.1, 0.1, 0);
        Point ppp = new Point(0.1, 0.1, Double.NaN);
        System.out.println(ppp.equals(new Point(0.1, 0.1, Double.NaN)));
        double d1 = Double.NaN;
        double d2 = Double.NaN;
        System.out.println(Double.compare(d2, d1));
        
        System.out.println(pp.hashCode());
        System.out.println(ppp.hashCode());


        CsvParse parser = new CsvParse("wat.csv");
        System.out.println(parser.parsePointsFromFile(";", "x", "y", "weight"));

    }

}
