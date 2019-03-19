/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barycentricCalc;

import point.Point;

/**
 *
 * @author lroni
 */
public class CalculateBarycentric {

    public static double[] calcBarycentricWeights(int x, int y, Point p1, Point p2, Point p3) {
        int P1x = p1.getX();
        int P1y = p1.getY();
        int P2x = p2.getX();
        int P2y = p2.getY();
        int P3x = p3.getX();
        int P3y = p3.getY();

        double weightP1 = (double) ((P2y - P3y) * (x - P3x) + (P3x - P2x) * (y - P3y))
                / ((P2y - P3y) * (P1x - P3x) + (P3x - P2x) * (P1y - P3y));

        double weightP2 = (double) ((P3y - P1y) * (x - P3x) + (P1x - P3x) * (y - P3y))
                / ((P2y - P3y) * (P1x - P3x) + (P3x - P2x) * (P1y - P3y));

        double weightP3 = (double) 1 - weightP1 - weightP2 * 1.0;

        weightP1 = (double) Math.round(weightP1 * 100) / 100;
        weightP2 = (double) Math.round(weightP2 * 100) / 100;
        weightP3 = (double) Math.round(weightP3 * 100) / 100;

        double[] output = {weightP1, weightP2, weightP3};

        return output;
    }

    public static boolean insideTriangle(double[] weights) {

        for (double value : weights) {
            if (value < 0) {
                return false;
            }
        }

        return true;
    }

    
}
