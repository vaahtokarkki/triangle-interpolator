/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageWriter;

import geometry.Triangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import javax.imageio.ImageIO;

/**
 *
 * @author lroni
 */
public class Writer {

    public static void writeToImage(double[][] values) {
        try {
            BufferedImage image = new BufferedImage(values[0].length + 1, values.length, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < values.length; y++) {
                for (int x = 0; x < values[0].length; x++) {
                    double value = values[y][x];
                    int rgb;
                    if (Double.isNaN(value)) {
                        rgb = 0;
                    } else {
                        rgb = (int) value;
                    }

                    Color newColor = new Color(rgb, rgb, rgb);
//                    System.out.print(rgb+" ");
                    image.setRGB(x, y, newColor.getRGB());
                }
//                System.out.println("");
            }

            File output = new File("GrayScale.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeTrianglesToImage(int width, int height, HashSet<Triangle> triangles) {
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            for (Triangle t : triangles) {
                g2d.drawLine((int) t.getVertex1().getX(), (int) t.getVertex1().getY(), (int) t.getVertex2().getX(), (int) t.getVertex2().getY());
                g2d.drawLine((int) t.getVertex2().getX(), (int) t.getVertex2().getY(), (int) t.getVertex3().getX(), (int) t.getVertex3().getY());
                g2d.drawLine((int) t.getVertex1().getX(), (int) t.getVertex1().getY(), (int) t.getVertex3().getX(), (int) t.getVertex3().getY());
            }

            File output = new File("Triangles.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
