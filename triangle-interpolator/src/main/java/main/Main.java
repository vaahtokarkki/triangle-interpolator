/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static interpolate.Interpolator.*;
import static imageWriter.Writer.*;
import utils.*;
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
        Point p1 = new Point(0, 0, 0.0);
        Point p2 = new Point(6, 0, 10.0);
        Point p3 = new Point(0, 6, 20.0);
        Triangle t = new Triangle(p1, p2, p3);
        System.out.println(t.calcWeightOfPoint(new Point(2, 2, 0)));


    }

}
