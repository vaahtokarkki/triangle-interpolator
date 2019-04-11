/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import geometry.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
    int XCoord;
    int YCoord;
    int ZCoord;
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
        XCoord = 0;
        YCoord = 1;
        ZCoord = 2;
        pathToTestResources = "src/test/resources/";
        emptyList = new MyArrayList<>();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testParsePointsFromFileSmallTest() {
        String file1 = pathToTestResources + "simple_points.csv";

        parser = new CsvParse(file1);
        MyArrayList<Point> results = parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord);
        MyArrayList<Point> expected = new MyArrayList<>();
        expected.add(new Point(0, 0, 1));
        expected.add(new Point(1, 1, 2));
        expected.add(new Point(3, 3, 3));
        expected.add(new Point(5, 5, 4));

        assertEquals(4, results.size());

        System.out.println(results);
        for (int i = 0; i < results.size(); i++) {
            Point p = results.get(i);
            assertTrue(expected.indexOf(p) >= 0);
        }
    }

    @Test
    public void testParsePointsFromFileLargeTest() {
        String file2 = pathToTestResources + "100_points.csv";

        parser = new CsvParse(file2);
        MyArrayList<Point> results = parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord);

        assertEquals(100, results.size());
        assertEquals(-1, results.indexOf(null));
    }

    @Test
    public void testParsePointsFromFileInvalidValues() {
        String file1 = pathToTestResources + "invalid_values.csv";
        String file2 = pathToTestResources + "invalid_columns.csv";
        String file5 = pathToTestResources + "empty_csv.csv";
        String file6 = pathToTestResources + "empty_file.csv";

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

        parser.setFileName(file5);
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName(file6);
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName(null);
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName("");
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());

        parser.setFileName("FileNotFound");
        assertEquals(0, parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord).size());
    }

    @Test
    public void testGetCsvHeaders() {
        parser = new CsvParse(null);
        String file1 = pathToTestResources + "simple_points.csv";
        parser.setFileName(file1);

        String[] result = parser.getCsvHeaders(csvSeparator);
        String[] expected = {"x", "y", "weight"};
        assertArrayEquals(expected, result);

        parser.setFileName(null);
        assertArrayEquals(null, parser.getCsvHeaders(csvSeparator));

        String file2 = pathToTestResources + "empty_file.csv";
        parser.setFileName(file2);
        System.out.println(Arrays.toString(parser.getCsvHeaders(csvSeparator)));
        assertArrayEquals(null, parser.getCsvHeaders(csvSeparator));
    }

    @Test
    public void testRowsInFile() {
        String file1 = pathToTestResources + "simple_points.csv";
        String file2 = pathToTestResources + "100_points.csv";
        parser = new CsvParse(file1);

        assertEquals(5, parser.rowsInFile());
        parser.setFileName(file2);
        assertEquals(101, parser.rowsInFile());

        parser.setFileName("FileNotFound");
        assertEquals(0, parser.rowsInFile());

        parser.setFileName(null);
        assertEquals(0, parser.rowsInFile());
    }

}
