/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import utils.Point;
import static barycentricCalc.CalculateBarycentric.*;

/**
 *
 * @author lroni
 */
public class BarycentricCalcTest {

    Point P1;
    Point P2;
    Point P3;

    public BarycentricCalcTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        P1 = new Point(0, 0, 10.0);
        P2 = new Point(5, 0, 10.0);
        P3 = new Point(0, 5, 10.0);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCalculationWhenOnPoint1() {
        double[] expected = {1.0, 0.0, 0.0};
        assertArrayEquals(expected, calcBarycentricWeights(P1.getX(), P1.getY(), P1, P2, P3), 0);
    }
    
    @Test
    public void testCalculationWhenOnPoint2() {
        double[] expected = {0.0, 1.0, 0.0};
        assertArrayEquals(expected, calcBarycentricWeights(P2.getX(), P2.getY(), P1, P2, P3), 0);
    }
    
    @Test
    public void testCalculationWhenOnPoint3() {
        double[] expected = {0.0, 0.0, 1.0};
        assertArrayEquals(expected, calcBarycentricWeights(P3.getX(), P3.getY(), P1, P2, P3), 0);
    }
    
    // TODO: More weight calculation tests

    @Test
    public void testInsideTriangleWhenOutside() {
        double[] weights = {0.5, 0.0, -1.0};
        assertFalse(insideTriangle(weights));
    }

    @Test
    public void testInsideTriangleWhenInside() {
        double[] weights = {0.5, 0.0, 1.0};
        assertTrue(insideTriangle(weights));
    }
}
