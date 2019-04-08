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
public class MyHashSetTest {

    MyHashSet<Integer> set;

    private void addItemsToSet(int amount) {
        for (int i = 0; i < amount; i++) {
            set.add(i);
        }
    }

    public MyHashSetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        set = new MyHashSet<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetSize() {
        assertEquals(0, set.size());

        addItemsToSet(10);

        assertEquals(10, set.size());

        set.remove(1);
        assertEquals(9, set.size());
    }

    @Test
    public void testSetAdd() {
        set.add(1);
        set.add(1);

        assertTrue(set.contains(1));
        assertEquals(1, set.size());
    }

    @Test
    public void testSetContains() {
        assertFalse(set.contains(1));
        assertFalse(set.contains(null));

        addItemsToSet(10);
        for (int i = 0; i < 10; i++) {
            assertTrue(set.contains(i));
        }

        set.add(null);
        assertTrue(set.contains(null));
    }

    @Test
    public void testSetRemove() {
        assertFalse(set.remove(99));

        addItemsToSet(10);

        assertTrue(set.remove(5));
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                assertFalse(set.contains(i));
                continue;
            }
            assertTrue(set.contains(i));
        }

        set.add(null);
        assertTrue(set.remove(null));
    }

    @Test
    public void testIterator() {
        addItemsToSet(10);
        int i = 0;
        for(int value:set) {
            assertEquals(value, i);
            i++;
        }
    }

    @Test
    public void testSettoString() {
        assertEquals("[]", set.toString());
        addItemsToSet(5);
        assertEquals("[0, 1, 2, 3, 4]", set.toString());
    }

}
