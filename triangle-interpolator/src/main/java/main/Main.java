/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static interpolate.Interpolator.*;
import static imageWriter.Writer.*;
import java.util.*;

/**
 *
 * @author lroni
 */
public class Main {
    
    public static void main(String[] args) {
        int[][] matrix = new int[1000][1000];
        
        for(int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
        
        double[][] out = interpolateMatrix(matrix);
        System.out.println(out);
        for(double[] i : out) {
            System.out.println(Arrays.toString(i));
        }
        
        writeToImage(out);
        
    }
    
}
