package ui;

import geometry.Triangle;
import static interpolate.Interpolator.interpolateInverseDistance;
import static interpolate.Interpolator.interpolateMatrix;
import static interpolate.Interpolator.triangulate;
import static io.Writer.writeToGrayscaleImage;
import static io.Writer.writeTrianglesToImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;
import utils.CsvParse;
import utils.MyArrayList;
import utils.MyHashSet;
import utils.MyMath;

public class UI {

    private Scanner sc;
    CsvParse parser;

    private String fileName;
    private String csvSeparator;

    private int XCoord, YCoord, ZCoord;

    private int width;
    private int height;

    public UI() {
        sc = new Scanner(System.in);

        fileName = "";
        width = 0;
        height = 0;
    }

    public void start() {
        selectFile();
        selectHeadersForFile();
        selectDimensions();
        System.out.println("*****************************");
        printFileStats();
        runInterpolation();
    }

    public void selectFile() {
        System.out.println("Select file where to read points");

        File currentFolder = new File(".");
        File[] listOfFiles = currentFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String string) {
                return string.toLowerCase().endsWith(".csv");
            }
        });

        printFiles(listOfFiles);

        int fileId = readNumber(1, listOfFiles.length);

        fileName = listOfFiles[fileId - 1].getName();

        getCsvSeparator();

        parser = new CsvParse(fileName);
    }

    private void printFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println("[" + (i + 1) + "] " + files[i].getName());
        }
    }

    private int readNumber(int min, int max, int defaultValue) {
        int output;
        while (true) {

            String line = sc.nextLine();
            line = line.trim();
            if (line.isEmpty()) {
                return defaultValue;
            }

            try {
                output = Integer.parseInt(line);
                if (output < min || output > max) {
                    System.out.println("Number must be in range " + min + "-" + max);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please give a number");
            }
        }
        return output;
    }

    private int readNumber(int min, int max) {
        int output;
        while (true) {
            try {
                output = Integer.parseInt(sc.nextLine());
                if (output < min || output > max) {
                    System.out.println("Number must be in range " + min + "-" + max);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please give a number");
            }
        }
        return output;
    }

    private void runInterpolation() {
        CsvParse parser = new CsvParse(fileName);
        MyArrayList<geometry.Point> list = parser.parsePointsFromFile(csvSeparator, XCoord, YCoord, ZCoord);
        list = MyMath.moveCoordinatesToOrigin(list);
        list = MyMath.scaleCoordinates(1000, 1000, list);

        System.out.println("Generating Delaunay triangles");
        MyHashSet<Triangle> t = triangulate(list);
        double[][] barycentricInterpolation = interpolateMatrix(width, height, list, 25);
        double[][] idwInterpolation = interpolateInverseDistance(width, height, list, 300, 2, 15);

        System.out.println("Writing barycentric");
        writeToGrayscaleImage(barycentricInterpolation, "barycentric_test.jpg");
        System.out.println("Writing idw");

        writeToGrayscaleImage(idwInterpolation, "idw_test.jpg");
        writeTrianglesToImage(width, height, t, "triangles.jpg");
        System.out.println("writing triangles");
    }

    private void selectHeadersForFile() {
        String[] headers = parser.getCsvHeaders(csvSeparator);
        printColumns(headers);

        System.out.println("Select column for x-coordinate (1-" + headers.length + ")");
        XCoord = readNumber(1, headers.length) - 1;

        System.out.println("Select column for y-coordinate (1-" + headers.length + ")");
        YCoord = readNumber(1, headers.length) - 1;

        System.out.println("Select column for value to interpolate (1-" + headers.length + ")");
        ZCoord = readNumber(1, headers.length) - 1;
    }

    private void getCsvSeparator() {
        System.out.println("Separator used in csv-file? [;]");
        String separator = sc.nextLine();

        separator = separator.trim();

        if (separator.isEmpty()) {
            this.csvSeparator = ";";
            return;
        }

        while (separator.length() != 1) {
            System.out.println("Separator must be exactly one character long!");
            separator = sc.nextLine();
            separator = separator.trim();
        }
        this.csvSeparator = separator;
    }

    private void printColumns(String[] headers) {
        String output = "";
        for (int i = 0; i < headers.length; i++) {
            output += "[" + (i + 1) + "] " + headers[i] + " ";
        }
        System.out.println(output);
    }

    private void selectDimensions() {
        System.out.println("Give width of output image in pixels [1000]");
        width = readNumber(1, 2000, 1000);
        System.out.println("Give height of output image in pixels [1000]");
        height = readNumber(1, 2000, 1000);
    }

    private void printFileStats() {
        System.out.println("Found from file "+fileName+" "+(parser.rowsInFile()-1)+" rows");
    }

}
