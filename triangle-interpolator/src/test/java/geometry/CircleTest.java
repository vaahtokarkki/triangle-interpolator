/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;


import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.MyArrayList;

/**
 *
 * @author lroni
 */
public class CircleTest {

    Point P1;
    Point P2;
    Point P3;
    Point P4;
    Point P5;

    Circle c1;
    double radius;

    MyArrayList<Point> points;

    public CircleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        P1 = new Point(0, 0);
        P2 = new Point(1, 1);
        P3 = new Point(10, 0);
        P4 = new Point(-5, -5);
        P5 = new Point(99, 99);

        radius = 10.0;
        c1 = new Circle(P1, radius);

        points = new MyArrayList<>();
        points.add(P1);
        points.add(P2);
        points.add(P3);
        points.add(P4);
        points.add(P5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        Circle c2 = new Circle(P1, 10);
        assertEquals(c1, c2);
    }

    @Test
    public void testGetCentre() {
        assertEquals(c1.getCentre(), P1);
    }

    @Test
    public void testGetRadius() {
        assertEquals(radius, c1.getRadius(), 0.001);
    }

    @Test
    public void testSetCentre() {
        Point newCentre = new Point(1, 1);

        c1.setCentre(newCentre);
        assertEquals(newCentre, c1.getCentre());

        c1.setCentre(null);
        assertEquals(newCentre, c1.getCentre());
    }

    @Test
    public void testSetRadius() {
        c1.setRadius(1.11);
        assertEquals(1.11, c1.getRadius(), 0.001);

        c1.setRadius(-1);
        assertEquals(1.11, c1.getRadius(), 0.001);
    }

    @Test
    public void testIsPointInsideWhenOutside() {
        assertFalse(c1.isPointInside(P5));
        assertFalse(c1.isPointInside(null));
    }

    @Test
    public void testIsPointInsideWhenInside() {
        assertTrue(c1.isPointInside(P2));
        assertTrue(c1.isPointInside(P4));
    }

    @Test
    public void testIsPointInsideWhenOnCircle() {
        assertTrue(c1.isPointInside(P3));
        assertTrue(c1.isPointInside(new Point(-10, 0)));
    }

    @Test
    public void testFindPointsInside() {
        Circle c2 = new Circle(P1, 5);

        MyArrayList<Point> result = c1.findPointsInside(points);
        MyArrayList<Point> result2 = c2.findPointsInside(points);

        assertEquals(4, result.size());
        assertEquals(2, result2.size());
        assertEquals(0, c1.findPointsInside(null).size());
        assertEquals(0, c1.findPointsInside(new MyArrayList<>()).size());
    }

    @Test
    public void testFindPointsInsideWithExclude() {
        HashSet<Point> set = new HashSet<>();
        set.add(P1);
        set.add(P2);
        set.add(P5);

        MyArrayList<Point> result = c1.findPointsInside(points, set);
        MyArrayList<Point> result2 = c1.findPointsInside(points, null);
        MyArrayList<Point> result3 = c1.findPointsInside(null, set);
        MyArrayList<Point> result4 = c1.findPointsInside(null, null);
        MyArrayList<Point> result5 = c1.findPointsInside(points, new HashSet<>());

        assertEquals(2, result.size());
        assertEquals(4, result2.size());
        assertEquals(0, result3.size());
        assertEquals(0, result4.size());
        assertEquals(4, result5.size());
    }

    @Test
    public void testEquals() {
        assertFalse(c1.equals(null));
        assertFalse(c1.equals("circle"));

        assertTrue(c1.equals(c1));
        assertTrue(c1.equals(new Circle(P1, radius)));

        assertFalse(c1.equals(new Circle(P5, 1)));
        assertFalse(c1.equals(new Circle(P1, 1)));

    }

    @Test
    public void testToString() {
        assertEquals("centre: " + P1 + ", radius: " + radius, c1.toString());
    }
}
