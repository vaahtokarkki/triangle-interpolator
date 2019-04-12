package geometry;

import utils.MyArrayList;
import utils.MyHashSet;

/**
 * Represents a triangle in 2D plane
 *
 * @see geometry.Point
 */
public class Triangle {

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
    private MyHashSet<Point> setOfVertexes;

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
        this.setOfVertexes = new MyHashSet<>();
        setOfVertexes.add(vertex1);
        setOfVertexes.add(vertex2);
        setOfVertexes.add(vertex3);
    }

    /**
     * Returns vertex 1
     *
     * @return vertex as {@link geometry.Point}
     */
    public Point getVertex1() {
        return vertex1;
    }

    /**
     * Returns vertex 2
     *
     * @return vertex as {@link geometry.Point}
     */
    public Point getVertex2() {
        return vertex2;
    }

    /**
     * Returns vertex 3
     *
     * @return vertex as {@link geometry.Point}
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
     * @param p Weight of {@link geometry.Point} to calculate
     * @return an array containing percentages of vertex weights, null if
     * invalid point is given
     */
    private double[] calculatebarycentricWeights(Point p) {
        if (p == null) {
            return null;
        }

        double P1x = vertex1.getX();
        double P1y = vertex1.getY();
        double P2x = vertex2.getX();
        double P2y = vertex2.getY();
        double P3x = vertex3.getX();
        double P3y = vertex3.getY();

        double x = p.getX();
        double y = p.getY();

        double weightP1 = ((P2y - P3y) * (x - P3x) + (P3x - P2x) * (y - P3y))
                / ((P2y - P3y) * (P1x - P3x) + (P3x - P2x) * (P1y - P3y));

        double weightP2 = ((P3y - P1y) * (x - P3x) + (P1x - P3x) * (y - P3y))
                / ((P2y - P3y) * (P1x - P3x) + (P3x - P2x) * (P1y - P3y));

        double weightP3 = 1 - weightP1 - weightP2 * 1.0;

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
     * @param p Weight of {@link geometry.Point} to calculate
     * @return weight of point or NaN
     */
    public double calcWeightOfPoint(Point p) {
        double[] weights = this.calculatebarycentricWeights(p);

        if (!this.areWeightsInsideTriangle(weights)) {
            return Double.NaN;
        }

        double calculatedWeight = vertex1.getWeight() * weights[0]
                + vertex2.getWeight() * weights[1]
                + vertex3.getWeight() * weights[2];

        return calculatedWeight;
    }

    /**
     * Checks if given point is inside triangle.
     *
     * @see Triangle#calculatebarycentricWeights(utils.Point)
     * @see Triangle#areWeightsInsideTriangle(double[])
     *
     * @param p
     * @return true if Point is inside triangle, else false
     */
    public boolean isPointInsideTriangle(Point p) {
        double[] weights = this.calculatebarycentricWeights(p);
        return areWeightsInsideTriangle(weights);
    }

    /**
     * Returns true if point is on some of the lines between triangles vertexes.
     * That is, if one of the calculated brycentrinc weights is 0.
     *
     * @param p Point to check
     * @return true if point is on triangles side
     */
    public boolean isPointOnTriangleSide(Point p) {
        double[] weights = this.calculatebarycentricWeights(p);

        if (!this.areWeightsInsideTriangle(weights)) {
            return false;
        }

        for (double value : weights) {
            if (value < 0.005) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if this triangle is a valid Delaunay triangle. That is if inside
     * triangles circumcenter circle is not any other points that the triangles
     * vertexes.
     *
     * @param listOfPoints list of points used in triangulation.
     *
     * @return true if triangle is a valid Delaunay triangle
     */
    public boolean isValidDelaunay(MyArrayList<Point> listOfPoints) {
        Circle circumcenter = this.getCircumcenterCircle();

        if (circumcenter == null) {
            return false;
        }

        MyArrayList<Point> pointsInsideCircle = circumcenter.findPointsInside(listOfPoints, setOfVertexes);

        return pointsInsideCircle.size() == 0;
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
    private boolean areWeightsInsideTriangle(double[] weights) {
        if (weights == null) {
            return false;
        }

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

    /**
     * Returns triangle's circumcenter, that is the point where the distance to
     * all the vertices is same.
     *
     * Steps how to calculate the cirumcenter:
     * <ul>
     * <li>Create line AB from vertex1 to vertex2 and line BC vertex2 to
     * vertex3</li>
     * <li>Find midpoints for those lines </li>
     * <li>Find normals for lines AB and BC by their midpoints</li>
     * <li>Find intersect point from previous step's lines</li>
     * </ul>
     *
     * @return
     */
    public Point findCircumcenter() {
        /*
        1 find first lines from vertex1 to vertex2 and vertex2 to vertex3
        2 find for step 1 lines midpoint
        3 find for step 1 lines normal by step 2 midpoints (line that is a normal
        4 to original lines and goes trough midpoint of original line)
        5 calculate perpendicular lines by points calculated in step 3
        6 find point where step 4 lines intersect
         */

        Line AB = new Line(vertex1, vertex2);
        Line BC = new Line(vertex2, vertex3);

        Point ABmidpoint = AB.getMidPoint();
        Point BCmidpoint = BC.getMidPoint();

        double[] parametersOfNormalForAB = AB.findPerpendicularLineByPoint(ABmidpoint);
        double[] parametersOfNormalForBC = BC.findPerpendicularLineByPoint(BCmidpoint);

        Line normalOfAB = new Line(parametersOfNormalForAB);
        Line normalOfBC = new Line(parametersOfNormalForBC);

        Point intersect = normalOfAB.findIntersect(normalOfBC);
        return intersect;
    }

    /**
     * Returns circle representing triangle's circumcenter.
     *
     * @return Circle of circumcenter or null if all vrtexes are on same line.
     */
    public Circle getCircumcenterCircle() {
        Point circumcenter = this.findCircumcenter();

        //If all vertexes are parallel 
        if (circumcenter == null) {
            return null;
        }

        double radius = circumcenter.calculateDistance(vertex1);

        return new Circle(circumcenter, radius);
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

        Triangle t = (Triangle) o;

        return t.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return vertex1.hashCode() + vertex2.hashCode() + vertex3.hashCode();
    }

    @Override
    public String toString() {
        return vertex1 + " - " + vertex2 + " - " + vertex3;
    }

}
