/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point;

/**
 *
 * @author lroni
 */
public class PointPair {
    
    private Point p1;
    private Point p2;
    private double distance;

    public PointPair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.distance = p1.calculateDistance(p2);
    }

    public PointPair(Point p1, Point p2, double distance) {
        this.p1 = p1;
        this.p2 = p2;
        this.distance = distance;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return p1+" - "+p2+" distance: "+distance;
    }
    
    
    
    
    
    
    
    
    
}
