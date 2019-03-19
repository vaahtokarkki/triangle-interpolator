/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageWriter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author lroni
 */
public class Writer {
    
    public static void writeToImage(double[][] values) {
        try {
            BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < values.length; y++) {
                for (int x = 0; x < values[0].length; x++) {
                    int a = (int) Math.round(values[y][x]);
                    if(a < 0) {
                        a=255;
                    }
                    Color newColor = new Color(a, a, a);
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
            File output = new File("GrayScale.jpg");
            ImageIO.write(image, "jpg", output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
}
