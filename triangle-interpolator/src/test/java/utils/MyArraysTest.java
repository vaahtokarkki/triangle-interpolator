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
public class MyArraysTest {

    public MyArraysTest() {
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
    public void testArraysContains() {
        Integer[] array = new Integer[10];
        Object[] array2 = new Object[10];

        assertFalse(utils.MyArrays.contains(array, 1));

        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }

        assertTrue(utils.MyArrays.contains(array, 1));
        assertFalse(utils.MyArrays.contains(array, null));
        assertFalse(utils.MyArrays.contains(array, 999));
        assertTrue(utils.MyArrays.contains(array2, null));
    }
}
