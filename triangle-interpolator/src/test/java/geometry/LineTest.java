package geometry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import geometry.Line;
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
public class LineTest {

    Point P1;
    Point P2;
    Point P3;
    Point P4;
    double[] parameters1;

    private boolean pointIsOnLine(double[] solvedLine, Point point) {
        double result = solvedLine[0] * point.getX() + solvedLine[1] * point.getY();
        return result == solvedLine[2];
    }

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
        P1 = new Point(0, 0);
        P2 = new Point(5, 0);
        P3 = new Point(0, 5);
        P4 = new Point(0, 8);
        parameters1 = new double[3];
    }

    @After
    public void tearDown() {
    }

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
    public void testLineStartPointWhenNotDefined() {
        Line l = new Line(parameters1);
        assertNull(l.getStart());
    }

    @Test
    public void testLineEndPointWhenNotDefined() {
        Line l = new Line(parameters1);
        assertNull(l.getEnd());
    }

    @Test
    public void testLineParametersWhenPointsNotDefined() {
        Line l = new Line(parameters1);
        assertArrayEquals(parameters1, l.getParameters(), 0.001);
    }

    @Test
    public void testLineParametersWhenPointsDefined() {
        Line l = new Line(P1, P2);
        double[] expected = l.solveLine();
        assertArrayEquals(expected, l.getParameters(), 0.001);
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

    @Test
    public void testLineLenghtWhenPointsNotDefined() {
        Line l = new Line(parameters1);
        assertEquals(Double.NaN, l.getLength(), 0);
    }

    @Test
    public void testLineMidpointWhenSamePoints() {
        Line l = new Line(P1, P1);
        assertEquals(P1, l.getMidPoint());
    }

    @Test
    public void testLineMidpointWhenPointsNotDefined() {
        Line l = new Line(parameters1);
        assertNull(l.getMidPoint());
    }

    @Test
    public void testLineMidpointWhenStraight() {
        Line l1 = new Line(P1, P2);
        Line l2 = new Line(P1, P3);
        assertEquals(2.5, l1.getMidPoint().getX(), 0.001);
        assertEquals(2.5, l2.getMidPoint().getY(), 0.001);
    }

    @Test
    public void testLineMidpointWhenDiagonal() {
        Line l1 = new Line(P2, P3);
        Line l2 = new Line(P4, P2);

        assertEquals(new Point(2.5, 2.5), l1.getMidPoint());
        assertEquals(new Point(2.5, 4), l2.getMidPoint());
    }

    @Test
    public void testSolveLineWhenPointsNotDefined() {
        double[] params = {1.0, 1.0, 5.0};
        Line l = new Line(params);

        assertArrayEquals(params, l.solveLine(), 0.001);
    }

    @Test
    public void testSolveLineWhenSamePoint() {
        double[] expected = {0.0, 0.0, 0.0};
        Line l = new Line(P1, P1);

        assertArrayEquals(expected, l.solveLine(), 0.001);
    }

    @Test
    public void testSolveLineWhenStraight() {
        Line l1 = new Line(P1, P2);
        Line l2 = new Line(P1, P3);
        Line l3 = new Line(P1, P1);

        Point pointOnLine1 = new Point(2, 0);
        Point pointOnLine2 = new Point(0, 2);
        Point pointNotOnLine = new Point(99, 99);

        double[] values1 = l1.solveLine();
        double[] values2 = l2.solveLine();
        double[] values3 = {0.0, 0.0, 0.0};

        assertTrue(pointIsOnLine(values1, pointOnLine1));
        assertTrue(pointIsOnLine(values2, pointOnLine2));
        assertFalse(pointIsOnLine(values1, pointNotOnLine));
        assertFalse(pointIsOnLine(values2, pointNotOnLine));

        //when on same point
        assertArrayEquals(values3, l3.solveLine(), 0.0);
    }

    @Test
    public void testSolveLineWhenDiagonal() {
        Line l1 = new Line(P3, P2);
        Line l2 = new Line(P3, new Point(10, 0));

        Point pointOnLine1 = new Point(4, 1);
        Point pointOnLine2 = new Point(6, 2);
        Point pointNotOnLine = new Point(99, 99);

        double[] values1 = l1.solveLine();
        double[] values2 = l2.solveLine();

        //Test points on solved line
        assertTrue(pointIsOnLine(values1, pointOnLine1));
        assertTrue(pointIsOnLine(values2, pointOnLine2));

        //Test points not on solved line
        assertFalse(pointIsOnLine(values1, pointNotOnLine));
        assertFalse(pointIsOnLine(values2, pointNotOnLine));
    }

    @Test
    public void testFindIntersectWhenParallel() {
        Line l1 = new Line(P1, P2);
        Line l2 = new Line(P2, P1);

        assertNull(l1.findIntersect(l2));
    }

    @Test
    public void testFindIntersectWhenStraight() {
        Line l1 = new Line(P1, P2);
        Line l2 = new Line(new Point(1, 5), new Point(1, -1));
        Line l3 = new Line(new Point(3, 4), new Point(3, 1));

        assertEquals(new Point(1, 0), l1.findIntersect(l2));
        assertEquals(new Point(3, 0), l1.findIntersect(l3));
    }

    @Test
    public void testFindIntersectWhenDiagonal() {
        Line l1 = new Line(P2, P3);
        Line l2 = new Line(P1, new Point(5, 5));

        Line l3 = new Line(new Point(0, 4), new Point(8, 0));
        Line l4 = new Line(P1, new Point(10, 5));

        assertEquals(new Point(2.5, 2.5), l1.findIntersect(l2));
        assertEquals(new Point(4, 2), l3.findIntersect(l4));
    }

    @Test
    public void testfindPerpendicularLineWhenSamePoint() {
        Line l1 = new Line(P1, P1);
        Line l2 = new Line(parameters1);
        assertArrayEquals(parameters1, l1.findPerpendicularLine(), 0.001);
        assertArrayEquals(parameters1, l2.findPerpendicularLine(), 0.001);
    }

    @Test
    public void testfindPerpendicularLineWhenStraightOnX() {
        double[] param = {0.0, -5.0, 0.0};
        double[] expected = {5.0, 0.0, 0.0};

        Line l1 = new Line(P1, P2);
        Line l2 = new Line(param);

        assertArrayEquals(expected, l1.findPerpendicularLine(), 0.001);
        assertArrayEquals(expected, l2.findPerpendicularLine(), 0.001);
    }

    @Test
    public void testfindPerpendicularLineWhenStraightOnY() {
        double[] param = {5.0, 0.0, 0.0};
        double[] expected = {0.0, 5.0, 0.0};

        Line l1 = new Line(P1, P3);
        Line l2 = new Line(param);

        assertArrayEquals(expected, l1.findPerpendicularLine(), 0.001);
        assertArrayEquals(expected, l2.findPerpendicularLine(), 0.001);
    }

    @Test
    public void testfindPerpendicularLineWhenDiagonal() {
        double[] param1 = {5.0, -5.0, 0};
        double[] param2 = {4.0, -8.0, 0};

        double[] expected1 = {5.0, 5.0, 0.0};
        double[] expected2 = {8.0, 4.0, 0};

        Line l1 = new Line(P1, new Point(5, 5));
        Line l1ByParam = new Line(param1);
        Line l2 = new Line(P1, new Point(8, 4));
        Line l2ByParam = new Line(param2);

        assertArrayEquals(expected1, l1.findPerpendicularLine(), 0.001);
        assertArrayEquals(expected1, l1ByParam.findPerpendicularLine(), 0.001);

        assertArrayEquals(expected2, l2.findPerpendicularLine(), 0.001);
        assertArrayEquals(expected2, l2ByParam.findPerpendicularLine(), 0.001);
    }

    @Test
    public void testfindPerpendicularLineByPointWhenSamePoint() {
        Line l1 = new Line(P1, P1);
        Line l2 = new Line(parameters1);
        assertArrayEquals(parameters1, l1.findPerpendicularLineByPoint(P1), 0.001);
        assertArrayEquals(parameters1, l2.findPerpendicularLineByPoint(P1), 0.001);
    }

    @Test
    public void testfindPerpendicularLineByPointWhenStraightOnX() {
        double[] param = {0.0, -5.0, 0.0};
        double[] expected = {5.0, 0.0, 5.0};
        Point testPoint = new Point(1, 0);

        Line l1 = new Line(P1, P2);
        Line l2 = new Line(param);

        assertArrayEquals(expected, l1.findPerpendicularLineByPoint(testPoint), 0.001);
        assertArrayEquals(expected, l2.findPerpendicularLineByPoint(testPoint), 0.001);
    }

    @Test
    public void testfindPerpendicularLineByPointWhenStraightOnY() {
        double[] param = {5.0, 0.0, 0.0};
        double[] expected = {0.0, 5.0, 5.0};
        Point testPoint = new Point(0, 1);

        Line l1 = new Line(P1, P3);
        Line l2 = new Line(param);

        assertArrayEquals(expected, l1.findPerpendicularLineByPoint(testPoint), 0.001);
        assertArrayEquals(expected, l2.findPerpendicularLineByPoint(testPoint), 0.001);
    }

    @Test
    public void testfindPerpendicularLineByPointWhenDiagonal() {
        double[] param1 = {5.0, -5.0, 0.0};
        double[] param2 = {5.0, -8.0, 0.0};

        double[] expected1 = {5.0, 5.0, 10.0};
        double[] expected2 = {8.0, 5.0, 89.0};

        Line l1 = new Line(P1, new Point(5, 5));
        Line l1ByParam = new Line(param1);
        Line l2 = new Line(P1, new Point(8, 5));
        Line l2ByParam = new Line(param2);

        assertArrayEquals(expected1, l1.findPerpendicularLineByPoint(new Point(1, 1)), 0.001);
        assertArrayEquals(expected1, l1ByParam.findPerpendicularLineByPoint(new Point(1, 1)), 0.001);

        assertArrayEquals(expected2, l2.findPerpendicularLineByPoint(new Point(8, 5)), 0.001);
        assertArrayEquals(expected2, l2ByParam.findPerpendicularLineByPoint(new Point(8, 5)), 0.001);
    }

    @Test
    public void toStringWhenPointsDefined() {
        Line l = new Line(P1, P2);
        double[] lineParams = l.getParameters();
        String expected = P1.toString() + " - " + P2.toString() + " distance: " + l.getLength() + ", equation: " + lineParams[0] + "x + " + lineParams[1] + "y = " + lineParams[2];
        assertEquals(expected, l.toString());
    }

    @Test
    public void toStringWhenPointsNotDefined() {
        Line l = new Line(parameters1);
        String expected = "null - null distance: NaN, equation: " + parameters1[0] + "x + " + parameters1[1] + "y = " + parameters1[2];
        assertEquals(expected, l.toString());
    }

}
