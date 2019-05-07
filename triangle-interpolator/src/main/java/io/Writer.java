package io;

import geometry.Point;
import geometry.Triangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import utils.ColorScheme;
import utils.MyArrayList;
import utils.MyColors;
import utils.MyHashSet;
import utils.MyMath;

/**
 *
 * @author lroni
 */
public class Writer {

    /**
     * Writes values from matrix to a grayscale png image. Dimensions of image
     * is same as dimensions of matrix. Values of matrix should be in range of
     * given classes (0 - amount of classes). Note, the values in matrix must be
     * classified for getting correct colour from given colour scheme.
     *
     *
     * @param values matrix to write
     * @param filename filename of created image, for example "grayscale.png"
     */
    public static void writeValuesToImage(double[][] values, String filename, int classes, ColorScheme color) {
        try {
            BufferedImage image = new BufferedImage(values[0].length, values.length, BufferedImage.TYPE_INT_RGB);

            renderDataToImage(image, values, classes, color);

            File output = new File(filename);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Writes values from matrix to a png image. Adds points and weights of them
     * as overlay to written matrix. Dimensions of image is same as dimensions
     * of matrix. Values of matrix should be in range of given classes (0 to
     * amount of classes). Note, the values in matrix must be classified for
     * getting correct colour from given colour scheme.
     *
     * @param values matrix to write
     * @param points list of points to add
     * @param filename filename of created image, for example "interpolated.png"
     */
    public static void writeValuesToImage(double[][] values, MyArrayList<Point> points, String filename, int classes,
            ColorScheme color) {
        int width = values[0].length;
        int height = values.length;

        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(rh);

            renderDataToImage(image, values, classes, color);
            drawPointsToGraphics(g2d, points, width);

            File output = new File(filename);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Draws given set of {@link geometry.Triangle} to a image.
     *
     * @param width width of created image
     * @param height height of created image
     * @param triangles set of triangles
     * @param fileName filename of created image, for example "triangles.png"
     */
    public static void writeTrianglesToImage(int width, int height, MyHashSet<Triangle> triangles, String fileName) {
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            for (Triangle t : triangles) {
                int vertex1X = MyMath.round(t.getVertex1().getX());
                int vertex1Y = MyMath.round(t.getVertex1().getY());

                int vertex2X = MyMath.round(t.getVertex2().getX());
                int vertex2Y = MyMath.round(t.getVertex2().getY());

                int vertex3X = MyMath.round(t.getVertex3().getX());
                int vertex3Y = MyMath.round(t.getVertex3().getY());

                g2d.drawLine(vertex1X, vertex1Y, vertex2X, vertex2Y);
                g2d.drawLine(vertex2X, vertex2Y, vertex3X, vertex3Y);
                g2d.drawLine(vertex1X, vertex1Y, vertex3X, vertex3Y);
            }

            File output = new File(fileName);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Renders data from given matrix to given BufferedImage object.
     *
     * @param image BufferedImage object
     * @param values value matrix
     * @param classes amount of classes used in output image
     * @param color used color scheme
     */
    private static void renderDataToImage(BufferedImage image, double[][] values, int classes, ColorScheme color) {
        for (int y = 0; y < values.length; y++) {
            for (int x = 0; x < values[0].length; x++) {
                double value = values[y][x];
                if (value > classes || value < 0) {
                    value = Double.NaN;
                }

                Color newColor = getColorForColorScheme(color, value, classes);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    /**
     * Get color for given value from given color scheme.
     *
     * @param color used color scheme @see{ColorScheme}
     * @param value value to get color for
     * @param classes amount of classes used, that is amount of different colors
     * wanted in output image
     * @return Color for value, or drfault color if no proper color scheme given
     */
    private static Color getColorForColorScheme(ColorScheme color, double value, int classes) {
        switch (color) {
            case SEQUENTIAL:
                return MyColors.getSequentialColorForClass(value, classes);
            case DIVERGING:
                return MyColors.getDivergingColorForClass(value, classes);
            default:
                return MyColors.DEFAULT;
        }
    }

    /**
     * Draw points from list to Graphics2D object.
     *
     * @param g2d Graphics2D object
     * @param points List of points
     * @param width width of image, used to check that points labels are inside
     * image
     */
    private static void drawPointsToGraphics(Graphics2D g2d, MyArrayList<Point> points, int width) {
        g2d.setPaint(new Color(0, 0, 0));
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            int pointX = MyMath.round(p.getX());
            int pointY = MyMath.round(p.getY());

            g2d.fillOval(pointX - 6, pointY - 6, 6, 6);

            pointX = pointX + 10 > width - 20 ? pointX - 50 : pointX;
            pointY = pointY - 10 < 10 ? pointY + 30 : pointY;

            g2d.drawString(p.getWeight() + "", pointX + 10, pointY - 10);
        }
    }
}
