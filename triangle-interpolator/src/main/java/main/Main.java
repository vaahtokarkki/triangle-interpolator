/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static interpolate.Interpolator.*;
import static imageWriter.Writer.*;
import point.Point;
import java.util.*;

/**
 *
 * @author lroni
 */
public class Main {

    public static void main(String[] args) {
        /*int[][] matrix = new int[1000][1000];
        
        for(int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
        
        double[][] out = interpolateMatrix(matrix);
        System.out.println(out);
        for(double[] i : out) {
            System.out.println(Arrays.toString(i));
        }
        
        writeToImage(out);
         */
        Point p1 = new Point(1, 1, 1.0);
        Point p2 = new Point(1, 4, 2.0);
        Point p3 = new Point(3, 4, 3.0);
        Point p4 = new Point(5, 2, 4.0);
        Point p5 = new Point(7, 3, 5.0);
        Point p6 = new Point(3, 5, 6.0);
//        Point p7 = new Point(4, 10, 0.0);

        Point[] list = {p1, p2, p3, p4, p5, p6};

        Point test = new Point(2, 6, 0);

        System.out.println(Arrays.toString(test.findClosestPoints(list, 3)));

    }

}
