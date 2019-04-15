package io;

import geometry.Point;
import geometry.Triangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import utils.MyArrayList;
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
     * 0-255.
     *
     * Note: Writes currently images with values mapped to colour ¯\_(ツ)_/¯
     *
     * TODO: get rid of value range 0-255, accept any values and find out their
     * min and max for mapping to colour
     *
     *
     * @param values matrix to write
     * @param filename filename of created image, for example "grayscale.png"
     */
    public static void writeToGrayscaleImage(double[][] values, String filename) {
        try {
            BufferedImage image = new BufferedImage(values[0].length, values.length, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < values.length; y++) {
                for (int x = 0; x < values[0].length; x++) {
                    double value = values[y][x];
                    Color newColor;
                    if (Double.isNaN(value) || value > 255 || value < 0) {
                        newColor = new Color(0, 0, 0);
                    } else {
                        int roundedValue = MyMath.round(value);
                        newColor = getRGBForValue(roundedValue, 0, 255);
                    }
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            File output = new File(filename);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Writes values from matrix to a grayscale png image. Adds points and
     * weights of them as overlay to written matrix. Dimensions of image is same
     * as dimensions of matrix. Values of matrix should be in range of 0-255.
     *
     * Note: Writes currently images with values mapped to colour ¯\_(ツ)_/¯
     *
     * TODO: get rid of value range 0-255, accept any values and find out their
     * min and max for mapping to colour
     *
     * @param values matrix to write
     * @param points list of points to add
     * @param filename filename of created image, for example "grayscale.png"
     */
    public static void writeToGrayscaleImage(double[][] values, MyArrayList<Point> points, String filename) {
        int width = values[0].length;
        int height = values.length;

        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHints(rh);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    double value = values[y][x];
                    Color newColor;
                    int rgb;
                    if (Double.isNaN(value) || value > 255 || value < 0) {
                        rgb = 0;
                        newColor = new Color(rgb, rgb, rgb);
                    } else {
                        rgb = MyMath.round(value);
                        newColor = getRGBForValue(rgb, 0, 255);
                    }
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            drawPointsToGraphics(g2d, points, width);

            File output = new File(filename);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Draws given set of {@link  geometry.Triangle} to a image.
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
                g2d.drawLine((int) t.getVertex1().getX(), (int) t.getVertex1().getY(), (int) t.getVertex2().getX(), (int) t.getVertex2().getY());
                g2d.drawLine((int) t.getVertex2().getX(), (int) t.getVertex2().getY(), (int) t.getVertex3().getX(), (int) t.getVertex3().getY());
                g2d.drawLine((int) t.getVertex1().getX(), (int) t.getVertex1().getY(), (int) t.getVertex3().getX(), (int) t.getVertex3().getY());
            }

            File output = new File(fileName);
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Maps a value in given range to a rgb value. Used to colorize classified
     * gray scale values
     *
     * @param value value to map a rgb value
     * @param min minimum value used
     * @param max maximum value used
     * @return
     */
    public static Color getRGBForValue(int value, double min, double max) {
        double ratio = 2 * (value - min) / (max - min);
        int b = (int) Math.max(0, 255 * (1 - ratio));
        int r = (int) Math.max(0, 255 * (ratio - 1));
        int g = 255 - b - r;

        return new Color(r, g, b);
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

            g2d.fillOval(pointX - 6, pointY - 6, 12, 12);

            pointX = pointX + 10 > width - 20 ? pointX - 50 : pointX;
            pointY = pointY - 10 < 10 ? pointY + 30 : pointY;

            g2d.drawString(p.getWeight() + "", pointX + 10, pointY - 10);
        }
    }
}
