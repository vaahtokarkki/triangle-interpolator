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
public class MyArrayListTest {

    MyArrayList<Integer> list;

    private void addTestItemsToList(MyArrayList<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    public MyArrayListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        list = new MyArrayList<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testListSize() {
        assertEquals(0, list.size());

        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        assertEquals(5, list.size());

        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        assertEquals(10, list.size());
    }

    @Test
    public void testGetFromList() {
        addTestItemsToList(list);

        for (int i = 0; i < 10; i++) {
            assertEquals(new Integer(i), list.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFromListThrowsException() {
        list.add(1);

        list.get(1);
        list.get(10);
        list.get(-1);
        list.get(list.size());
    }

    @Test
    public void testSetToList() {
        addTestItemsToList(list);

        assertEquals(new Integer(0), list.set(0, 99));
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                assertEquals(new Integer(99), list.get(i));
                continue;
            }
            assertEquals(new Integer(i), list.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetToListThrowsException() {
        list.set(99, 0);
        list.set(-5, 0);
    }

    @Test
    public void testIndexOf() {
        addTestItemsToList(list);
        list.add(null);

        assertEquals(0, list.indexOf(0));
        assertEquals(5, list.indexOf(5));
        assertEquals(-1, list.indexOf(999));

        assertEquals(10, list.indexOf(null));
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(2));

        assertEquals(2, list.size());
        assertEquals(new Integer(3), list.get(list.size() - 1));

        assertFalse(list.remove(-1));
        assertEquals(2, list.size());

        assertFalse(list.remove(99));
        assertEquals(2, list.size());
    }

    @Test
    public void testListToString() {
        assertEquals("[]", list.toString());

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals("[1, 2, 3]", list.toString());

    }
}
