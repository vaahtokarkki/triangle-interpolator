/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import geometry.Point;
import geometry.Line;
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
        Point p1 = new Point(1, 1,0);
        Point p2 = new Point(4, 4);
        Point p3 = new Point(1, 8);
        Point p4 = new Point(2, 4);
        
        Point P1 = new Point(0, 0);
        Point P2 = new Point(5, 0);
        Point P3 = new Point(8, 5);
        
        Line ll = new Line(P1, P2);
        System.out.println(ll.getMidPoint());

        
        
        Triangle triangle = new Triangle(P1, P2, P3);
//        System.out.println(triangle.calcWeightOfPoint(kala));
                

        Triangle t = new Triangle(p1, p2, p3);
//        System.out.println(t.calcWeightOfPoint(new Point(2, 2, 0)));

        Line l1 = new Line(P1, P3);
        Line l2 = new Line(p3, p4);
        
        System.out.println(Arrays.toString(l1.solveLine()));
        System.out.println(Arrays.toString(l1.findPerpendicularLine()));
        System.out.println(Arrays.toString(l1.findPerpendicularLineByPoint(P3)));
        
        

//        System.out.println(l1.findIntersect(l2));

    }

}
