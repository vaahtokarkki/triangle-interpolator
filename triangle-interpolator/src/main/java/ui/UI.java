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
    private String fileName;
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

        int fileId = readNumber();
        while (fileId >= listOfFiles.length) {
            System.out.println("File number must be between 1-" + listOfFiles.length);
            fileId = readNumber();
        }

        fileName = listOfFiles[fileId - 1].getName();
        System.out.println(fileName);
    }

    private void printFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println("[" + (i + 1) + "] " + files[i].getName());
        }
    }

    private int readNumber() {
        int output;
        while (true) {
            try {
                output = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please give a number");
            }
        }
        return output;
    }

    private void runInterpolation() {
        CsvParse parser = new CsvParse(fileName);
        MyArrayList<geometry.Point> list = parser.parsePointsFromFile(";", "x", "y", "weight");
        list = MyMath.moveCoordinatesToOrigin(list);
        list = MyMath.scaleCoordinates(1000, 1000, list);

        System.out.println("Generating Delaunay triangles");
        MyHashSet<Triangle> t = triangulate(list);
        double[][] barycentricInterpolation = interpolateMatrix(1000, 1000, list, 25);
        double[][] idwInterpolation = interpolateInverseDistance(1000, 1000, list, 300, 2, 15);

        System.out.println("Writing barycentric");
        writeToGrayscaleImage(barycentricInterpolation, "barycentric_test.jpg");
        System.out.println("Writing idw");

        writeToGrayscaleImage(idwInterpolation, "idw_test.jpg");
        writeTrianglesToImage(1000, 1000, t, "triangles.jpg");
        System.out.println("writing triangles");
    }

}
