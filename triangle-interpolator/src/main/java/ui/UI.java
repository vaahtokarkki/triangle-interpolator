package ui;

import geometry.Point;
import geometry.Triangle;
import static interpolate.Interpolator.interpolateInverseDistance;
import static interpolate.Interpolator.interpolateMatrix;
import static interpolate.Interpolator.triangulate;
import static io.Writer.writeTrianglesToImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import utils.ColorScheme;
import utils.CsvParse;
import utils.MyArrayList;
import utils.MyHashSet;
import utils.MyMath;
import static io.Writer.writeValuesToImage;

public class UI {

    private Scanner sc;
    private CsvParse parser;

    private String inputFileName, outputFileName, dataFolder = "data";
    private boolean drawLabels;

    private String csvSeparator;
    private int xCoord, yCoord, zCoord;

    private int width, height, classes;

    private double p = 2, searchRadius = 1000;
    private ColorScheme color = ColorScheme.SEQUENTIAL;

    public UI() {
        sc = new Scanner(System.in);

        inputFileName = "";
        outputFileName = "";
        drawLabels = true;
        width = 0;
        height = 0;

        dataFolder = UITools.defineDefaultFolder();
    }

    public void start(String userDefinedDataFolder) {
        if (this.dataFolder == null && userDefinedDataFolder == null) {
            System.out.println("Can't find default data folder, please give as command line argument");
            return;
        }

        selectFile(userDefinedDataFolder);
        selectHeadersForFile();
        selectDimensions();
        selectInterpolationParameters();
        selectDrawLables();
        selectOutput();
        System.out.println("*****************************");
        printFileStats();
        runInterpolation();
    }

    private void selectInterpolationParameters() {
        System.out.println("Select classes (1-25) used in output (how may colors) [10]: ");
        classes = UITools.readNumber(sc, 1, 25, 10);

        System.out.println("Use default IDW parameters? p=2, variable serach radius of 1000 pixels [yes]: ");

        if (!UITools.readBoolean(sc, true)) {
            System.out.println("Give p value [2]");
            p = UITools.readDouble(sc, 0, 10, 2);
            System.out.println("Give serach radius in distance [" + searchRadius + "]: ");
            searchRadius = UITools.readDouble(sc, 1, 9999, searchRadius);
        }

        System.out.println("Select color scheme for output image, seqential/diverging [Seqential]: ");
        color = UITools.readColorScheme(sc, "sequential");
    }

    public void selectFile(String argsDataFolder) {
        if (argsDataFolder != null && UITools.isValidFolder(argsDataFolder)) {
            this.dataFolder = argsDataFolder;
            System.out.println("Found given folder '" + argsDataFolder + "'! Select file where to read points: ");
        } else {
            System.out.println("Select file where to read points, default folder is '" + dataFolder + "': ");
        }

        File currentFolder = new File(dataFolder);
        File[] listOfFiles = currentFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String string) {
                return string.toLowerCase().endsWith(".csv");
            }
        });

        UITools.printFiles(listOfFiles);

        int fileId = UITools.readNumber(sc, 1, listOfFiles.length);

        inputFileName = "data/" + listOfFiles[fileId - 1].getName();

        getCsvSeparator();

        parser = new CsvParse(inputFileName);
    }

    private void runInterpolation() {
        CsvParse parser = new CsvParse(inputFileName);
        MyArrayList<Point> list = parser.parsePointsFromFile(csvSeparator, xCoord, yCoord, zCoord);

        list = MyMath.scaleCoordinates(width, height, list);

        System.out.println("Generating Delaunay triangles");
        MyHashSet<Triangle> t = triangulate(list);
        double[][] barycentricInterpolation = interpolateMatrix(width, height, list, t, classes);
        double[][] idwInterpolation = interpolateInverseDistance(width, height, list, searchRadius, p, classes);

        System.out.println("Writing barycentric");
        if (drawLabels) {
            writeValuesToImage(barycentricInterpolation, list, "barycentric_" + outputFileName, classes, color);
        } else {
            writeValuesToImage(barycentricInterpolation, "barycentric_" + outputFileName, classes, color);
        }

        System.out.println("Writing idw");
        if (drawLabels) {
            writeValuesToImage(idwInterpolation, list, "idw_" + outputFileName, classes, color);
        } else {
            writeValuesToImage(idwInterpolation, "idw_" + outputFileName, classes, color);
        }

        System.out.println("writing triangles");
        writeTrianglesToImage(width, height, t, "triangles_" + outputFileName);
    }

    private void selectHeadersForFile() {
        String[] headers = parser.getCsvHeaders(csvSeparator);
        UITools.printColumns(headers);

        System.out.println("Select column for x-coordinate [1-" + headers.length + "]: ");
        xCoord = UITools.readNumber(sc, 1, headers.length) - 1;

        System.out.println("Select column for y-coordinate [1-" + headers.length + "]: ");
        yCoord = UITools.readNumber(sc, 1, headers.length) - 1;

        System.out.println("Select column for value to interpolate [1-" + headers.length + "]: ");
        zCoord = UITools.readNumber(sc, 1, headers.length) - 1;
    }

    private void getCsvSeparator() {
        System.out.println("Give separator used in csv-file? [;]: ");
        this.csvSeparator = UITools.readString(sc, ";", 1);
    }

    private void selectDimensions() {
        System.out.println("Give width of output image in pixels [1000]");
        width = UITools.readNumber(sc, 1, 2000, 1000);
        System.out.println("Give height of output image in pixels [1000]");
        height = UITools.readNumber(sc, 1, 2000, 1000);
    }

    private void printFileStats() {
        System.out.println("Found from file " + inputFileName + " " + (parser.rowsInFile() - 1) + " rows");
    }

    private void selectOutput() {
        String[] filePath = inputFileName.split("/");
        String[] defaultOutputName = filePath[filePath.length - 1].split("\\.");

        System.out.println(
                "Give file name without extension where to write output images [" + defaultOutputName[0] + ".png]");
        outputFileName = UITools.readString(sc, defaultOutputName[0] + ".png");
    }

    private void selectDrawLables() {
        System.out.println("Write point lables on output image y/n [yes]");
        drawLabels = UITools.readBoolean(sc, true);
    }

}
