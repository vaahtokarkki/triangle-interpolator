package io;

import geometry.Triangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import javax.imageio.ImageIO;
import utils.MyMath;

/**
 *
 * @author lroni
 */
public class Writer {

    /**
     * Writes values from matrix to a grayscale jpg image. Dimensions of image
     * is same as dimensions of matrix. Values of matrix should be in range of
     * 0-255.
     * 
     * Note: Writes currently images with values mapped to colour ¯\_(ツ)_/¯
     *
     * @param values matrix to interpolate
     * @param filename filename of created image, for example "grayscale.jpg"
     */
    public static void writeToGrayscaleImage(double[][] values, String filename) {
        try {
            BufferedImage image = new BufferedImage(values[0].length + 1, values.length, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < values.length; y++) {
                for (int x = 0; x < values[0].length; x++) {
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

            File output = new File(filename);
            ImageIO.write(image, "jpg", output);
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
     * @param filename filename of created image, for example "triangles.jpg"
     */
    public static void writeTrianglesToImage(int width, int height, HashSet<Triangle> triangles, String filename) {
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            for (Triangle t : triangles) {
                g2d.drawLine((int) t.getVertex1().getX(), (int) t.getVertex1().getY(), (int) t.getVertex2().getX(), (int) t.getVertex2().getY());
                g2d.drawLine((int) t.getVertex2().getX(), (int) t.getVertex2().getY(), (int) t.getVertex3().getX(), (int) t.getVertex3().getY());
                g2d.drawLine((int) t.getVertex1().getX(), (int) t.getVertex1().getY(), (int) t.getVertex3().getX(), (int) t.getVertex3().getY());
            }

            File output = new File(filename);
            ImageIO.write(image, "jpg", output);
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
}
