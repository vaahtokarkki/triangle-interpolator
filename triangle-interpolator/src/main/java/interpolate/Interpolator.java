/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import utils.*;

/**
 *
 * @author lroni
 */
public class Interpolator {
    
    
    public static double[][] interpolateMatrix(int[][] matrix) {
        //For testing only
        Point p1 = new Point(500, 0, 250.0);
        Point p2 = new Point(0, 999, 140.0);
        Point p3 = new Point(999, 999, 0.0);
        
        Triangle triangle = new Triangle(p1, p2, p3);
        

        if (matrix == null || matrix.length == 0) {
            return null;
        }

        double[][] output = new double[matrix.length][matrix[0].length];

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                double interpolatedValue = triangle.calcWeightOfPoint(new Point(x, y, 0));

                if (Double.isNaN(interpolatedValue)) {
                    output[y][x] = -1.0;
                    continue;
                }

                output[y][x] = (double) Math.round(interpolatedValue * 100) / 100;;
            }
        }

        return output;
    }


}
