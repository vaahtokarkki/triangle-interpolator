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
            int value = rand.nextInt((255 - 100) + 1) + 100;
            list.add(new Point(x, y, value));
        }

        HashSet<Triangle> t = triangulate(list);
        double[][] out = interpolateMatrix(1000, 1000, list);
        writeToImage(out);
        writeTrianglesToImage(1000, 1000, t);
    }

}
