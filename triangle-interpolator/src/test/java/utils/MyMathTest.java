/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import geometry.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lroni
 */
public class MyMathTest {

    Point p1;
    Point p2;
    Point p3;
    Point p4;

    MyArrayList<Point> list1;
    MyArrayList<Point> emptyList;

    public MyMathTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p1 = new Point(1, 2);
        p2 = new Point(2, 1);
        p3 = new Point(3, 3);
        p4 = new Point(5, 2);

        list1 = new MyArrayList<>();
        list1.add(p1);
        list1.add(p2);
        list1.add(p3);
        list1.add(p4);

        emptyList = new MyArrayList<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDoubleAbsoluteValue() {
        double v1 = 1.0;
        double v2 = -1.0;
        double v3 = 0.0;
        double v4 = -0.0;

        assertEquals(v1, MyMath.abs(v2), 0.001);
        assertEquals(v3, MyMath.abs(v4), 0.001);
    }

    @Test
    public void testRoundToInteger() {
        assertEquals(0, MyMath.round(0.0));
        assertEquals(1, MyMath.round(0.9));
        assertEquals(0, MyMath.round(0.4999));

        assertEquals(0, MyMath.round(-0.499));
        assertEquals(-1, MyMath.round(-0.99));
    }

    @Test
    public void testFindMinimumBoundinBox() {
        MyArrayList<Point> list2 = new MyArrayList<>();

        list2.add(p1);
        list2.add(p1);
        list2.add(p1);
        list2.add(p1);

        Point[] expected1 = {new Point(1, 3), new Point(5, 3), new Point(5, 1), new Point(1, 1)};
        Point[] expected2 = {p1, p1, p1, p1};

        assertArrayEquals(null, MyMath.findMinimumBoundingBox(emptyList));

        assertArrayEquals(expected1, MyMath.findMinimumBoundingBox(list1));
        assertArrayEquals(expected2, MyMath.findMinimumBoundingBox(list2));
    }

    @Test
    public void testMoveCoordinatesToOrigin() {
        MyArrayList<Point> expected = new MyArrayList<>();

        expected.add(new Point(0, 1));
        expected.add(new Point(2, 0));
        expected.add(new Point(4, 1));
        expected.add(new Point(1, 2));

        MyArrayList<Point> results = MyMath.moveCoordinatesToOrigin(list1);

        assertEquals(4, results.size());
        assertEquals(null, MyMath.moveCoordinatesToOrigin(emptyList));

        for (int i = 0; i < 4; i++) {
            assertTrue(results.indexOf(expected.get(i)) >= 0);
        }
    }
}
