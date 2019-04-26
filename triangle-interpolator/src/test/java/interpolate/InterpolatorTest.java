/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import geometry.Point;
import geometry.Triangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.MyArrayList;
import utils.MyHashSet;

/**
 *
 * @author lroni
 */
public class InterpolatorTest {

    Point p1;
    Point p2;
    Point p3;
    Point p4;

    Triangle t1;
    Triangle t2;

    MyArrayList<Point> list;

    public InterpolatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p1 = new Point(0, 0);
        p2 = new Point(0, 6);
        p3 = new Point(6, 3);
        p4 = new Point(-10, 3);

        list = new MyArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testClassifyValue() {
        double[] minAndMax = new double[2];
        assertEquals(-1, Interpolator.classifyValue(Double.NaN, minAndMax, 0));

        minAndMax[1] = 4;
        for (int i = 0; i < 5; i++) {
            assertEquals(i, Interpolator.classifyValue(i, minAndMax, 5), 0.001);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(0, Interpolator.classifyValue(i, minAndMax, 0), 0.001);
        }

        minAndMax = new double[1];
        assertEquals(-1, Interpolator.classifyValue(1.0, minAndMax, 0));
        assertEquals(-1, Interpolator.classifyValue(1.0, null, 0));
    }

    @Test
    public void testGetGrayscaleValueForClass() {
        assertEquals(-1, interpolate.Interpolator.getGrayscaleValueForClass(0, 0), 0.01);

        for (int i = 1; i <= 3; i++) {
            int expected = i == 1 ? 0 : i * 85;
            assertEquals(expected, interpolate.Interpolator.getGrayscaleValueForClass(i - 1, 3), 0.01);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(0, interpolate.Interpolator.getGrayscaleValueForClass(i, 1), 0.01);
        }
    }

    @Test
    public void testTriangulate() {
        MyHashSet<Triangle> result = Interpolator.triangulate(list);
        MyHashSet<Triangle> expected = new MyHashSet<>();
        t1 = new Triangle(p1, p2, p3);
        t2 = new Triangle(p1, p2, p4);
        expected.add(t1);
        expected.add(t2);

        assertEquals(expected.size(), result.size());
        for (Triangle t : result) {
            assertTrue(expected.contains(t));
        }

        Point p5 = new Point(10, 3);
        list.add(p5);
        expected.add(new Triangle(p1, p3, p5));
        expected.add(new Triangle(p2, p3, p5));

        result = Interpolator.triangulate(list);
        assertEquals(expected.size(), result.size());
        for (Triangle t : result) {
            assertTrue(expected.contains(t));
        }

        result = Interpolator.triangulate(new MyArrayList<Point>());
        expected = new MyHashSet<>();
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void testInterpolateInverseDistance() {
        double[][] expected = new double[10][10];
        MyArrayList<Point> list = new MyArrayList<>();
        MyArrayList<Point> emptyList = new MyArrayList<>();

        list.add(new Point(60.0000, 25.0000, 0.0));
        double[][] result = Interpolator.interpolateInverseDistance(10, 10, list, 100, 2, 1);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], result[i], 0.01);
        }

        double[][] resultWhenEmpty = Interpolator.interpolateInverseDistance(10, 10, emptyList, 100, 2, 1);
        for(int y=0;y<resultWhenEmpty.length;y++) {
            for(int x=0;x<resultWhenEmpty.length;x++) {
                assertEquals(Double.NaN, resultWhenEmpty[y][x], 0);
            }
        }
    }
    
    @Test
    public void testInterpolateMatrix() {
        
    }
}
