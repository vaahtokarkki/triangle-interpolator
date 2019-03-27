package geometry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import geometry.Point;
import geometry.Triangle;
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
public class TriangleTest {

    Point P1;
    Point P2;
    Point P3;
    Triangle triangle;

    public TriangleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        P1 = new Point(0, 0, 0.0);
        P2 = new Point(6, 0, 10.0);
        P3 = new Point(0, 6, 20.0);
        triangle = new Triangle(P1, P2, P3);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testVertex1IsCorrect() {
        assertEquals(P1, triangle.getVertex1());
    }

    @Test
    public void testVertex2IsCorrect() {
        assertEquals(P2, triangle.getVertex2());
    }

    @Test
    public void testVertex3IsCorrect() {
        assertEquals(P3, triangle.getVertex3());
    }

    @Test
    public void testCalculatePointWeightWhenOutside() {
        assertEquals(Double.NaN, triangle.calcWeightOfPoint(new Point(999, 999, 0)), 0.001);
    }

    @Test
    public void testCalculatePointWeightWhenOnVertex() {
        assertEquals(P1.getWeight(), triangle.calcWeightOfPoint(P1), 0.001);
        assertEquals(P2.getWeight(), triangle.calcWeightOfPoint(P2), 0.001);
        assertEquals(P3.getWeight(), triangle.calcWeightOfPoint(P3), 0.001);
    }

    @Test
    public void testCalculatePointWeightWhenOnSide() {
        assertEquals(5.0, triangle.calcWeightOfPoint(new Point(3.0, 0.0, 0)), 0.001);
        assertEquals(10.0, triangle.calcWeightOfPoint(new Point(0.0, 3.0, 0)), 0.001);
        assertEquals(15.0, triangle.calcWeightOfPoint(new Point(3.0, 3.0, 0)), 0.001);
    }

    @Test
    public void testCalculatePointWeightWhenInside() {
        assertEquals(10, triangle.calcWeightOfPoint(new Point(2, 2, 0)), 0.111);
        assertEquals(5, triangle.calcWeightOfPoint(new Point(1, 1, 0)), 0.111);
    }
}
