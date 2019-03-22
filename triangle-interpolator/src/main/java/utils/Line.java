package utils;

/**
 * Represents a line in 2D plane
 *
 * @see utils.Point
 *
 * @author lroni
 */
public class Line {

    private Point start;
    private Point end;
    private double length;

    /**
     * Creates a line with given start and end {@link utils.Point} and
     * calculates line's distance
     *
     *
     * @param start Start point
     * @param end End point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = start.calculateDistance(end);
    }

    /**
     * Returns start point
     *
     * @return Start point
     */
    public Point getStart() {
        return start;
    }

    /**
     * Returns end point
     *
     * @return End point
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Returns length of line
     *
     * @return length
     */
    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return start + " - " + end + " distance: " + length;
    }

}
