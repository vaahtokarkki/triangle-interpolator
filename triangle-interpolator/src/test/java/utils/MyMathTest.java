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
    public void testRoundToDouble() {
        // 1 decimal
        for (double i = -10.0; i < 10; i += 0.1) {
            double expected = Math.round(i * 100.0) / 100.0;
            double result = MyMath.round(i, 1);

            assertEquals(expected, result, 0.1);
        }

        // 2 decimals
        for (double i = -1.0; i <= 1; i += 0.01) {
            double expected = Math.round(i * 1000.0) / 1000.0;
            double result = MyMath.round(i, 2);

            assertEquals(expected, result, 0.01);
        }

        // 3 decimals
        for (double i = -1.0; i <= 1; i += 0.001) {
            double expected = Math.round(i * 10000.0) / 10000.0;
            double result = MyMath.round(i, 3);

            assertEquals(expected, result, 0.001);
        }
    }

    @Test
    public void testMax() {
        assertEquals(0, MyMath.max(0, 0), 0.001);
        assertEquals(1, MyMath.max(0, 1), 0.001);
        assertEquals(1, MyMath.max(1, 0), 0.001);
        assertEquals(101, MyMath.max(100, 101), 0.001);
        assertEquals(0, MyMath.max(0, -10), 0.001);
        assertEquals(-8, MyMath.max(-10, -8), 0.001);
    }

    @Test
    public void testMin() {
        assertEquals(0, MyMath.min(0, 0), 0.001);
        assertEquals(0, MyMath.min(0, 1), 0.001);
        assertEquals(0, MyMath.min(1, 0), 0.001);
        assertEquals(100, MyMath.min(100, 101), 0.001);
        assertEquals(-10, MyMath.min(0, -10), 0.001);
        assertEquals(-10, MyMath.min(-10, -8), 0.001);
    }

    @Test
    public void testSqrt() {
        assertEquals(Double.NaN, MyMath.sqrt(Double.NaN), 0);
        assertEquals(Double.NaN, MyMath.sqrt(-1), 0);
        assertEquals(0, MyMath.sqrt(0), 0);

        for (int i = 0; i < 100; i++) {
            double result = MyMath.sqrt(i);
            double expected = Math.sqrt(i);
            assertEquals(expected, result, 0.000001);
        }

        for (double i = 0; i < 1; i += 0.001) {
            double result = MyMath.sqrt(i);
            double expected = Math.sqrt(i);
            assertEquals(expected, result, 0.000001);
        }
    }

    @Test
    public void testPowIntegerExponent() {
        //powers of 2
        for (int i = -10; i < 10; i++) {
            double expected = Math.pow(2, i);
            double result = MyMath.pow(2, i);

            assertEquals(expected, result, 0.0001);
        }

        //powers of decimal value
        for (int i = -10; i < 10; i++) {
            double expected = Math.pow(1.55, i);
            double result = MyMath.pow(1.55, i);

            assertEquals(expected, result, 0.0001);
        }

        for (int i = -10; i < 10; i++) {
            double expected = Math.pow(i, i);
            double result = MyMath.pow(i, i);

            assertEquals(expected, result, 0.0001);
        }

        //big exponents
        for (int i = 1000; i < 2000; i++) {
            double expected = Math.pow(2, i);
            double result = MyMath.pow(2, i);

            assertEquals(expected, result, 0.0001);
        }
    }

    @Test
    public void testPowDoubleExponent() {
        //Powers of 2
        for (double i = -10; i <= 10;) {
            double expected = Math.pow(2, i);
            double result = MyMath.pow(2, i);

            assertEquals(expected, result, 0.0001);

            i = MyMath.round((i + 0.1) * 100.0) / 100.0;
        }

        for (double i = -10; i <= 10;) {
            double expected = Math.pow(i, i);
            double result = MyMath.pow(i, i);

            assertEquals(expected, result, 0.0001);

            i = MyMath.round((i + 0.1) * 100.0) / 100.0;
        }

        //Decimal values
        for (double i = -10; i <= 10;) {
            double expected = Math.pow(1.55, i);
            double result = MyMath.pow(1.55, i);

            assertEquals(expected, result, 0.0001);

            i = MyMath.round((i + 0.1) * 100.0) / 100.0;
        }

        //Negative values with negative exponent
        for (double i = -2.0; i > -20.0;) {
            double expected = Math.pow(-2, i);
            double result = MyMath.pow(-2, i);

            assertEquals(expected, result, 0.0001);
            i = MyMath.round((i - 0.1) * 100.0) / 100.0;
        }

        //Negative values with positive exponent
        for (double i = 0; i < 10;) {
            double expected = Math.pow(-2, i);
            double result = MyMath.pow(-2, i);

            assertEquals(expected, result, 0.0001);
            i = MyMath.round((i + 0.1) * 100.0) / 100.0;
        }

    }

    @Test
    public void testGetMaxAndMinValues() {
        MyArrayList<Point> list2 = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(new Point(i, i, i));
            list2.add(new Point(i, i, i * -1.0));
        }
        double[] expected1 = {0.0, 9.0};
        double[] expected2 = {-9.0, 0.0};

        assertArrayEquals(expected1, MyMath.getMaxAndMinValues(list1), 0.001);
        assertArrayEquals(expected2, MyMath.getMaxAndMinValues(list2), 0.001);
        assertEquals(null, MyMath.getMaxAndMinValues(new MyArrayList<Point>()));
        assertEquals(null, MyMath.getMaxAndMinValues(null));

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

    @Test
    public void testScaleCoordinates() {
        MyArrayList<Point> input = new MyArrayList<>();
        input.add(new Point(0, 0));
        input.add(new Point(1, 0));
        input.add(new Point(0, 1));
        input.add(new Point(1, 1));

        MyArrayList<Point> result = MyMath.scaleCoordinates(10, 10, input);
        assertEquals(input.size(), result.size());
        for (int i = 0; i < 4; i++) {
            Point p = input.get(i);
            Point expectedPoint = new Point(p.getX() * 10, p.getY() * 10);

            assertTrue(result.indexOf(expectedPoint) >= 0);
        }

        result = MyMath.scaleCoordinates(0, 0, input);
        assertEquals(input.size(), result.size());
        for (int i = 0; i < 4; i++) {
            Point p = input.get(i);
            assertTrue(result.indexOf(p) >= 0);
        }

        assertEquals(null, MyMath.scaleCoordinates(10, 10, null));
        assertEquals(0, MyMath.scaleCoordinates(10, 10, emptyList).size());
    }
    
    @Test
    public void testPI() {
        assertEquals(Math.PI, MyMath.PI, 0.000000000000001);
    }
    
    @Test
    public void testToRadians() {
        for(double i=-500;i<500;i+=0.5) {
            assertEquals(Math.toRadians(i), MyMath.toRadians(i), 0.0001);
        }
    }
}
