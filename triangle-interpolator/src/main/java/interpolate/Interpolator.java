/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import point.Point;

/**
 *
 * @author lroni
 */
public class Interpolator {
    
    
    public static double[][] interpolateMatrix(int[][] matrix) {
        //For testin only
        Point p1 = new Point(500, 0, 250.0);
        Point p2 = new Point(0, 999, 140.0);
        Point p3 = new Point(999, 999, 0.0);
        

        if (matrix == null || matrix.length == 0) {
            return null;
        }

        double[][] output = new double[matrix.length][matrix[0].length];

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                double[] weights = barycentricCalc.CalculateBarycentric.calcBarycentricWeights(x, y, p1, p2, p3);

                if (!barycentricCalc.CalculateBarycentric.insideTriangle(weights)) {
                    output[y][x] = -1.0;
                    continue;
                }

                double interpolatedValue = p1.getWeight() * weights[0]
                        + p2.getWeight() * weights[1]
                        + p3.getWeight() * weights[2];
                output[y][x] = (double) Math.round(interpolatedValue * 100) / 100;;
            }
        }

        return output;
    }


}
