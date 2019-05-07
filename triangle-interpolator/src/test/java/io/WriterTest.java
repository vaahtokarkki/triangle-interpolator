/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import geometry.Point;
import geometry.Triangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.ColorScheme;
import utils.MyArrayList;
import utils.MyHashSet;

/**
 *
 * @author lroni
 */
public class WriterTest {

    double[][] matrix;
    MyArrayList<Point> points;
    MyHashSet<Triangle> setOfTriangles;
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
        setOfTriangles = new MyHashSet<>();

        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(50, 50);
        Point p4 = new Point(99, 99);

        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);

        Triangle t1 = new Triangle(p1, p2, p3);
        Triangle t2 = new Triangle(p2, p3, p4);
        setOfTriangles.add(t1);
        setOfTriangles.add(t2);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testWriteToGrayscaleImage() throws IOException {
        Writer.writeValuesToImage(matrix, fileName, 10, ColorScheme.DIVERGING);

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
        Writer.writeValuesToImage(matrix, points, fileName, 10, ColorScheme.DIVERGING);

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
        // When NaN values
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = Double.NaN;
            }
        }

        Writer.writeValuesToImage(matrix, fileName, 10, ColorScheme.DIVERGING);

        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());

        // When values over 255 or less 0
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                double value = -999;
                if (x % 2 == 0 && y % 2 == 0) {
                    value = 999;
                }

                matrix[y][x] = value;
            }
        }

        Writer.writeValuesToImage(matrix, fileName, 10, ColorScheme.DIVERGING);

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
        // When NaN values
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                matrix[y][x] = Double.NaN;
            }
        }

        Writer.writeValuesToImage(matrix, points, fileName, 10, ColorScheme.DIVERGING);

        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());

        // When values over 255 or less 0
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                double value = -999;
                if (x % 2 == 0 && y % 2 == 0) {
                    value = 999;
                }

                matrix[y][x] = value;
            }
        }

        Writer.writeValuesToImage(matrix, points, fileName, 10, ColorScheme.DIVERGING);

        dir = new File("test-run");
        files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        testImage = ImageIO.read(new File(fileName));

        assertEquals(matrix[0].length, testImage.getWidth());
        assertEquals(matrix.length, testImage.getHeight());
    }

    @Test
    public void testWriteTrianglesToImage() throws IOException {
        int width = 100;
        int height = 100;

        Writer.writeTrianglesToImage(width, height, setOfTriangles, fileName);
        File dir = new File("test-run");
        File[] files = dir.listFiles();

        assertEquals(1, files.length);
        assertEquals(fileName, files[0].toString());

        BufferedImage testImage = ImageIO.read(new File(fileName));

        assertEquals(width, testImage.getWidth());
        assertEquals(height, testImage.getHeight());
    }
    
}
