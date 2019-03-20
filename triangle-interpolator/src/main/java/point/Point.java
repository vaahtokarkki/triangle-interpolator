/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point;

import java.util.*;

/**
 *
 * @author lroni
 */
public class Point {

    private int x;
    private int y;
    private double weight;

    public Point(int x, int y, double weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double calculateDistance(Point point) {
        int deltaX = point.x - this.x;
        int deltaY = point.y - this.y;

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    public Point[] findClosestPoints(Point[] listOfPoints, int n) {
        Point[] output = new Point[n];
        ArrayList<PointPair> listOfDistances = new ArrayList<>();

        for (Point p : listOfPoints) {
            double distance = Math.abs(p.calculateDistance(this));
            listOfDistances.add(new PointPair(this, p, distance));
        }

        Collections.sort(listOfDistances, new Comparator<PointPair>() {
            public int compare(PointPair pair1, PointPair pair2) {
                if(pair1.getDistance() < pair2.getDistance()) {
                    return -1;
                }
                
                if(pair1.getDistance() < pair2.getDistance()) {
                    return 1;
                }
                
                return 0;
            }
        });

        
        listOfDistances.forEach(v ->{
            System.out.println(v.getP2()+" distance: "+v.getDistance());
        });
        
        for (int i = 0; i < n; i++) {
            output[i] = listOfDistances.get(i).getP2();
        }

        return output;
    }

    @Override
    public String toString() {
        return "x: " + this.x + " y: " + this.y + ", weight: " + this.weight;
    }

}
