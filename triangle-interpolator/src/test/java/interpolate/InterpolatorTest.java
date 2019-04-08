/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

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
public class InterpolatorTest {

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testClassifyValue() {
        double[] minAndMax = new double[2];
        assertEquals(-1, interpolate.Interpolator.classifyValue(Double.NaN, minAndMax, 0));
        
        minAndMax[1] = 4;
        for (int i = 0; i < 5; i++) {
            assertEquals(i, interpolate.Interpolator.classifyValue(i, minAndMax, 5), 0.001);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(0, interpolate.Interpolator.classifyValue(i, minAndMax, 0), 0.001);
        }
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
}
