package geometry;

/**
 *
 * Represents a point in 2D plane
 *
 */
public class Point {

    private double x;
    private double y;
    private double weight;

    /**
     * Creates a point with x and y coordinates and specified weight
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param weight weight or value assigned to point
     */
    public Point(double x, double y, double weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.weight = 0;
    }

    /**
     * Gets point's x-coordinate
     *
     * @return x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets point's y-coordinate
     *
     * @return y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets point's weight
     *
     * @return weight or value
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets point's x-coordinate
     *
     * @param x new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets point's y-coordinate
     *
     * @param y new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets point's weight
     *
     * @param weight new weight or value
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Calculates distance from this point to given point
     *
     * @param point Point where to calculate distance from this point
     * @return distance
     */
    public double calculateDistance(Point point) {
        double deltaX = point.x - this.x;
        double deltaY = point.y - this.y;

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    @Override
    public String toString() {
        return "x: " + this.x + " y: " + this.y + ", weight: " + this.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Point p = (Point) o;
        return p.getX() == x && p.getY() == y
                && Double.compare(weight, p.weight) == 0;
    }

    @Override
    public int hashCode() {
        /*
        int hashCode = 0;
        hashCode = (int) Math.pow(hashCode * 31, x);
        hashCode = (int) Math.pow(hashCode * 31, y);
         */
        int hashCode = 23;
        hashCode = hashCode * 31 + (int) x;
        hashCode = hashCode * 31 + (int) y;
        if (!Double.isNaN(weight)) {
            hashCode = hashCode * 31 + (int) weight;
        }
        return hashCode;
    }

}
