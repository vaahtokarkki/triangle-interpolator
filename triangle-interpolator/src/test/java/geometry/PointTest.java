package geometry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PointTest {

    Point P1;
    Point P2;
    Point P3;
    Point P4;

    public PointTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        P1 = new Point(0, 0);
        P2 = new Point(0, 3);
        P3 = new Point(3, 4);
        P4 = new Point(7, 4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPointXCoordinateCorrect() {
        assertEquals(0.0, P1.getX(), 0.001);
    }

    @Test
    public void testPointYCoordinateCorrect() {
        assertEquals(0.0, P1.getY(), 0.001);
    }

    @Test
    public void testSetPointXCoordinateCorrect() {
        P1.setX(1);
        assertEquals(1.0, P1.getX(), 0.001);
    }

    @Test
    public void testSetPointYCoordinateCorrect() {
        P1.setY(1);
        assertEquals(1.0, P1.getY(), 0.001);
    }

    @Test
    public void testSetPointWeightCorrect() {
        P1.setWeight(1.0);
        assertEquals(1.0, P1.getWeight(), 0.001);
    }

    @Test
    public void testPointDistanceCorrect() {
        assertEquals(0.0, P1.calculateDistance(P1), 0.001);
        assertEquals(0.0, P3.calculateDistance(P3), 0.001);
    }

    @Test
    public void testPointStartghtDistanceCorrect() {
        assertEquals(3.0, P1.calculateDistance(P2), 0.001);
        assertEquals(4.0, P3.calculateDistance(P4), 0.001);
    }

    @Test
    public void testPointDiagonallyDistanceCorrect() {
        assertEquals(5.0, P1.calculateDistance(P3), 0.001);
        assertEquals(7.071, P2.calculateDistance(P4), 0.001);
    }

    @Test
    public void testEqualsReturnsCorrect() {
        Point equalPoint = new Point(0, 0);
        Point notEqualPoint = new Point(0, 0, 1);

        assertFalse(P1.equals(null));
        assertFalse(P1.equals("point"));
        assertFalse(P1.equals(P3));

        assertTrue(P1.equals(equalPoint));
        assertFalse(P1.equals(notEqualPoint));
    }
}
