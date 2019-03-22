package utils;

/**
 * Represents a triangle in 2D plane
 *
 * @see utils.Point
 * @author lroni
 */
public class Triangle {

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    /**
     * Creates a triangle with given vertexes
     *
     * @param vertex1 Vertex 1
     * @param vertex2 Vertex 2
     * @param vertex3 Vertex 3
     */
    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    /**
     * Returns vertex 1
     *
     * @return vertex as {@link utils.Point}
     */
    public Point getVertex1() {
        return vertex1;
    }

    /**
     * Returns vertex 2
     *
     * @return vertex as {@link utils.Point}
     */
    public Point getVertex2() {
        return vertex2;
    }

    /**
     * Returns vertex 3
     *
     * @return vertex as {@link utils.Point}
     */
    public Point getVertex3() {
        return vertex3;
    }

    /**
     * Calculates barycentric weight for given point based on weights of
     * triangle's vertexes
     *
     * i.e. with returned array [0.30, 0.20, 0.50] gives weight for given point:
     * 30% of vertex 1 weight, 20% of vertex 2 weight and 50% of vertex 3 weight
     *
     * @param p Weight of {@link utils.Point} to calculate
     * @return an array containing percentages of vertex weights
     */
    private double[] calculatebarycentricWeights(Point p) {
        int P1x = vertex1.getX();
        int P1y = vertex1.getY();
        int P2x = vertex2.getX();
        int P2y = vertex2.getY();
        int P3x = vertex3.getX();
        int P3y = vertex3.getY();

        int x = p.getX();
        int y = p.getY();

        double weightP1 = (double) ((P2y - P3y) * (x - P3x) + (P3x - P2x) * (y - P3y))
                / ((P2y - P3y) * (P1x - P3x) + (P3x - P2x) * (P1y - P3y));

        double weightP2 = (double) ((P3y - P1y) * (x - P3x) + (P1x - P3x) * (y - P3y))
                / ((P2y - P3y) * (P1x - P3x) + (P3x - P2x) * (P1y - P3y));

        double weightP3 = (double) 1 - weightP1 - weightP2 * 1.0;

        weightP1 = (double) Math.round(weightP1 * 100) / 100;
        weightP2 = (double) Math.round(weightP2 * 100) / 100;
        weightP3 = (double) Math.round(weightP3 * 100) / 100;

        double[] output = {weightP1, weightP2, weightP3};

        return output;
    }

    /**
     * Returns value of given point calculated by barycentric coordinates. The
     * point must be inside of triangle to calculate weight. If the point is
     * outside of triangle method return NaN.
     *
     * @see Triangle#calculatebarycentricWeights(utils.Point)
     *
     * @param p Weight of {@link utils.Point} to calculate
     * @return weight of point or NaN
     */
    public double calcWeightOfPoint(Point p) {
        double[] weights = this.calculatebarycentricWeights(p);

        if (!this.isInsideTriangle(weights)) {
            return Double.NaN;
        }

        double calculatedWeight = vertex1.getWeight() * weights[0]
                + vertex2.getWeight() * weights[1]
                + vertex3.getWeight() * weights[2];

        return calculatedWeight;
    }

    /**
     * Checks if calculated barycentric weights are inside triangle. That is if
     * all values in given array is positive.
     * 
     * @see Triangle#calculatebarycentricWeights(utils.Point) 
     *
     * @param weights an array of calculated weights 
     * @return true if weights are inside triangle, else false
     */
    public boolean isInsideTriangle(double[] weights) {
        if (weights.length < 3) {
            return false;
        }

        for (double value : weights) {
            if (value < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidDelaunay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return vertex1 + " - " + vertex2 + " - " + vertex3;
    }

}
