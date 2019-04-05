package geometry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.MyArrayList;

/**
 *
 * @author lroni
 */
public class TriangleTest {

    Point P1;
    Point P2;
    Point P3;
    Point P4;
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
        P4 = new Point(99, 99);
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
    public void testIsPointInsideTriangleWhenOutside() {
        assertFalse(triangle.isPointInsideTriangle(P4));
        assertFalse(triangle.isPointInsideTriangle(null));
    }

    @Test
    public void testIsPointInsideTriangleWhenInside() {
        assertTrue(triangle.isPointInsideTriangle(new Point(1, 1)));
        assertTrue(triangle.isPointInsideTriangle(new Point(2.5, 2.5)));
    }

    @Test
    public void testIsPointInsideTriangleWhenOnSide() {
        assertTrue(triangle.isPointInsideTriangle(new Point(3, 3)));
        assertTrue(triangle.isPointInsideTriangle(new Point(0, 3)));
        assertTrue(triangle.isPointInsideTriangle(new Point(3, 3)));

        assertTrue(triangle.isPointInsideTriangle(P1));
        assertTrue(triangle.isPointInsideTriangle(P2));
        assertTrue(triangle.isPointInsideTriangle(P3));
    }

    @Test
    public void testCalculatePointWeightWhenOutside() {
        assertEquals(Double.NaN, triangle.calcWeightOfPoint(new Point(999, 999, 0)), 0.001);
        assertEquals(Double.NaN, triangle.calcWeightOfPoint(null), 0.001);
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

    @Test
    public void testIsPointOnTriangleSideWhenOutisde() {
        assertFalse(triangle.isPointOnTriangleSide(new Point(99, 99)));
        assertFalse(triangle.isPointOnTriangleSide(null));
    }

    @Test
    public void testIsPointOnTriangleSideWhenInside() {
        assertFalse(triangle.isPointOnTriangleSide(new Point(1, 1)));
        assertFalse(triangle.isPointOnTriangleSide(new Point(2.5, 2.5)));
    }

    @Test
    public void testIsPointOnTriangleSideWhenOnSide() {
        assertTrue(triangle.isPointOnTriangleSide(new Point(0, 3)));
        assertTrue(triangle.isPointOnTriangleSide(new Point(3, 0)));
        assertTrue(triangle.isPointOnTriangleSide(new Point(3, 3)));
    }

    @Test
    public void testGetCircumcenterCircle() {
        Triangle t2 = new Triangle(P1, P1, P1);
        Triangle t3 = new Triangle(new Point(1, 0), new Point(0, 3), new Point(3, 0));

        Circle c = triangle.getCircumcenterCircle();
        Circle c2 = t3.getCircumcenterCircle();

        assertEquals(c.getCentre(), new Point(3, 3));
        assertEquals(c2.getCentre(), new Point(2, 2));
        assertEquals(t2.getCircumcenterCircle(), null);
    }

    @Test
    public void testIsValidDelaunay() {
        Triangle t2 = new Triangle(P1, P1, P1);

        MyArrayList<Point> points = new MyArrayList<>();
        points.add(P1);
        points.add(P2);
        points.add(P3);

        assertTrue(triangle.isValidDelaunay(points));
        
        points.add(new Point(1, 1));
        
        assertFalse(triangle.isValidDelaunay(points));
        assertFalse(t2.isValidDelaunay(points));
    }

    @Test
    public void testTriangleEquals() {
        assertFalse(triangle.equals(null));
        assertFalse(triangle.equals("triangle"));

        assertTrue(triangle.equals(triangle));
        assertTrue(triangle.equals(new Triangle(P3, P2, P1)));

        assertFalse(triangle.equals(new Triangle(P1, P1, P1)));
    }

    @Test
    public void testTriangleHashCode() {
        int pointsHashCode = P1.hashCode() + P2.hashCode() + P3.hashCode();
        assertTrue(triangle.hashCode() == pointsHashCode);
    }

    @Test
    public void testTriangleToString() {
        String expected = P1 + " - " + P2 + " - " + P3;
        assertEquals(expected, triangle.toString());

    }

}
