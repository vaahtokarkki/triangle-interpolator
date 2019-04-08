/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import geometry.Point;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lroni
 */
public class CsvParseTest {

    CsvParse parser;
    String csvSeparator;
    String XCoord;
    String YCoord;
    String ZCoord;
    MyArrayList<Point> emptyList;
    
    String pathToTestResources;

    public CsvParseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        csvSeparator = ";";
        XCoord = "x";
        YCoord = "y";
        ZCoord = "weight";
        pathToTestResources = "src/test/resources/";
        emptyList = new MyArrayList<>();
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testParsePointsFromFileSmallTest() {
        String file1 = pathToTestResources+"simple_points.csv";

        parser = new CsvParse(file1);
        MyArrayList<Point> results = parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord);
        MyArrayList<Point> expected = new MyArrayList<>();
        expected.add(new Point(0, 0, 1));
        expected.add(new Point(1, 1, 2));
        expected.add(new Point(3, 3, 3));
        expected.add(new Point(5, 5, 4));
        
        assertEquals(4, results.size());
        for (int i = 0; i < results.size(); i++) {
            Point p = results.get(i);
            assertTrue(expected.indexOf(p) >= 0);
        }
    }

    @Test
    public void testParsePointsFromFileLargeTest() {
        String file2 = pathToTestResources+"100_points.csv";

        parser = new CsvParse(file2);
        MyArrayList<Point> results = parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord);

        assertEquals(100, results.size());
        assertEquals(-1, results.indexOf(null));
    }

    @Test
    public void testParsePointsFromFileInvalidValues() {
        String file1 = pathToTestResources+"invalid_values.csv";
        String file2 = pathToTestResources+"no_header.csv";
        String file3 = pathToTestResources+"invalid_header1.csv";
        String file4 = pathToTestResources+"invalid_header2.csv";
        String file5 = pathToTestResources+"empty_csv.csv";
        String file6 = pathToTestResources+"empty_file.csv";

        parser = new CsvParse(file1);
        MyArrayList<Point> results = parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord);
        MyArrayList<Point> expected = new MyArrayList<>();
        expected.add(new Point(1.0, 1.0, Double.NaN));
        expected.add(new Point(2.0, 2.0, 3.0));

        assertEquals(2, results.size());
        for (int i = 0; i < results.size(); i++) {
            Point p = results.get(i);
            assertTrue(expected.indexOf(p) >= 0);
        }

        parser.setFileName(file2);
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName(file3);
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName(file4);
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName(file5);
        assertEquals(null, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord));

        parser.setFileName(file6);
        assertEquals(null, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord));
    }

    @Test
    public void testParsePointsThrowsException() {
        String file = "FileNotFound";

        parser = new CsvParse(file);
        assertEquals(null, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord));
    }

}