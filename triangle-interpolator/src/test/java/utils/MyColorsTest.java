/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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
public class MyColorsTest {

    public MyColorsTest() {
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
    public void testGetSequentialColorForClass() {
        int length = MyColors.SEQUENTIAL.length;

        assertEquals(MyColors.DEFAULT, MyColors.getSequentialColorForClass(0, 0));
        assertEquals(MyColors.DEFAULT, MyColors.getSequentialColorForClass(Double.NaN, 0));

        assertEquals(MyColors.SEQUENTIAL[0], MyColors.getSequentialColorForClass(0, 3));
        assertEquals(MyColors.SEQUENTIAL[length / 2], MyColors.getSequentialColorForClass(1, 3));
        assertEquals(MyColors.SEQUENTIAL[length - 1], MyColors.getSequentialColorForClass(2, 3));

        for (int i = 0; i < length; i++) {
            assertEquals(MyColors.SEQUENTIAL[i], MyColors.getSequentialColorForClass(i, length));
        }
        
        for (int i = 0; i < length; i++) {
            assertEquals(MyColors.SEQUENTIAL[i], MyColors.getSequentialColorForClass(i, 99));
        }
    }

    @Test
    public void testGetDivergingColorForClass() {
        int length = MyColors.DIVERGING.length;

        assertEquals(MyColors.DEFAULT, MyColors.getDivergingColorForClass(0, 0));
        assertEquals(MyColors.DEFAULT, MyColors.getDivergingColorForClass(Double.NaN, 0));

        assertEquals(MyColors.DIVERGING[0], MyColors.getDivergingColorForClass(0, 3));
        assertEquals(MyColors.DIVERGING[length / 2], MyColors.getDivergingColorForClass(1, 3));
        assertEquals(MyColors.DIVERGING[length - 1], MyColors.getDivergingColorForClass(2, 3));

        for (int i = 0; i < length; i++) {
            assertEquals(MyColors.DIVERGING[i], MyColors.getDivergingColorForClass(i, length));
        }
        
        for (int i = 0; i < length; i++) {
            assertEquals(MyColors.DIVERGING[i], MyColors.getDivergingColorForClass(i, 99));
        }
    }
}
