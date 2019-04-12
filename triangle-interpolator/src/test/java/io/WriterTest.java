/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import geometry.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.MyArrayList;

/**
 *
 * @author lroni
 */
public class WriterTest {

    double[][] matrix;
    MyArrayList<Point> points;
    String fileName;

    public WriterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        new File("test-run").mkdir();
    }

    @AfterClass
    public static void tearDownClass() {
        File index = new File("test-run");
        String[] entries = index.list();
        for (String s : entries) {
            File currentFile = new File(index.getPath(), s);
            currentFile.delete();
        }
        index.delete();
    }

    @Before
    public void setUp() {
        matrix = new double[100][100];
        fileName = "test-run/test-write.png";
        points = new MyArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 10));
        points.add(new Point(50, 50));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testWriteToGrayscaleImage() throws IOException {
        Writer.writeToGrayscaleImage(matrix, fileName);

        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());
    }

    @Test
    public void testWriteToGrayscaleImageWithPoints() throws IOException {
        Writer.writeToGrayscaleImage(matrix, points, fileName);

        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());
    }

    @Test
    public void testWriteToGrayscaleImageInvalidValues() throws IOException {
        //When NaN values
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = Double.NaN;
            }
        }

        Writer.writeToGrayscaleImage(matrix, fileName);

        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());

        //When values over 255 or less 0
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                double value = -999;
                if (x % 2 == 0 && y % 2 == 0) {
                    value = 999;
                }

                matrix[y][x] = value;
            }
        }

        Writer.writeToGrayscaleImage(matrix, fileName);

        dir = new File("test-run");
        files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());

    }

    @Test
    public void testWriteToGrayscaleImageWithPointsInvalidValues() throws IOException {
        //When NaN values
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = Double.NaN;
            }
        }

        Writer.writeToGrayscaleImage(matrix, points, fileName);

        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());

        //When values over 255 or less 0
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                double value = -999;
                if (x % 2 == 0 && y % 2 == 0) {
                    value = 999;
                }

                matrix[y][x] = value;
            }
        }

        Writer.writeToGrayscaleImage(matrix, points, fileName);

        dir = new File("test-run");
        files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());

    }
}
