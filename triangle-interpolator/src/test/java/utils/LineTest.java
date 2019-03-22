package utils;

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


/**
 *
 * @author lroni
 */
public class LineTest {

    Point P1;
    Point P2;
    Point P3;

    public LineTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        P1 = new Point(0, 0, 0);
        P2 = new Point(5, 0, 0);
        P3 = new Point(0, 5, 0);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testLineStartPoint() {
        Line l = new Line(P1, P1);
        assertSame(P1, l.getStart());
    }

    @Test
    public void testLineEndPoint() {
        Line l = new Line(P1, P1);
        assertSame(P1, l.getEnd());
    }

    @Test
    public void testLineLenghtWhenSamePoints() {
        Line l = new Line(P1, P1);
        assertEquals(0, l.getLength(), 0.001);
    }

    @Test
    public void testLineLenghtWhenStraight() {
        Line l1 = new Line(P1, P2);
        Line l2 = new Line(P1, P3);

        assertEquals(5, l1.getLength(), 0.001);
        assertEquals(5, l2.getLength(), 0.001);
    }
    
    @Test
    public void testLineLenghtWhenDiagonal() {
        Line l1 = new Line(P2, P3);
        assertEquals(7.071, l1.getLength(), 0.001);
    }
}
