package utils;

import geometry.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A csv-file parser to parse {@link geometry.Point} from csv-file.
 *
 * @author lroni
 */
public class CsvParse {

    private String fileName;
    private MyArrayList<String> linesFromFile;

    /**
     * Creates new csv-parser with given filename to read data from.
     *
     * @param fileName file to read
     */
    public CsvParse(String fileName) {
        this.fileName = fileName;
        linesFromFile = new MyArrayList<>();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        linesFromFile = new MyArrayList<>();
    }

    /**
     * Returns how many files are in given file, including csv-header row.
     *
     * @return amount of rows in file
     */
    public int rowsInFile() {
        if (linesFromFile.size() == 0) {
            readFile();
        }

        return linesFromFile.size();
    }

    private void readFile() {
        if (fileName.trim().isEmpty()) {
            return;
        }

        try {
            linesFromFile = io.Reader.readRows(fileName);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
    }

    /**
     * Reads rows from given csv (must set with constructor or
     * {@link CsvParse#setFileName(java.lang.String)}) file and parses
     * {@link utils.MyArrayList} of {@link geometry.Point} objects from values.
     * Csv file must have column names on first row, other columns are ignored.
     *
     * @param csvSeparator Separator used in csv file (usually ";" )
     * @param XCoord column name for X-coordinate
     * @param YCoord column name for Y-coordinate
     * @param ZValue column name for value to interpolate, the weight or
     * Z-coordinate of point.
     * @return {@link utils.MyArrayList} of parsed {@link geometry.Point} or
     * null if invalid file
     */
    public MyArrayList<Point> parsePointsFromFile(String csvSeparator, int XCoord, int YCoord, int ZValue) {
        if (linesFromFile.size() == 0) {
            readFile();
        }

        //If file contains only header row
        if (linesFromFile.size() <= 1) {
            return null;
        }

        MyArrayList<Point> output = new MyArrayList<>();
        MyArrayList<String[]> valuesFromFile = parseValuesFromCSVLines(csvSeparator);

        for (int i = 1; i < valuesFromFile.size(); i++) {
            String[] row = valuesFromFile.get(i);
            double X, Y, Z;
            try {
                X = Double.parseDouble(row[XCoord]);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            try {
                Y = Double.parseDouble(row[YCoord]);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            try {
                Z = Double.parseDouble(row[ZValue]);
            } catch (Exception e) {
                e.printStackTrace();
                Z = Double.NaN;
            }

            output.add(new Point(X, Y, Z));
        }

        return output;
    }

    /**
     * Returns an array of headers in given csv file. That is the first line of
     * file, splited by given separator.
     *
     * @param csvSeparator csv separator to use in split
     * @return an array of headers, null if invalid file
     */
    public String[] getCsvHeaders(String csvSeparator) {
        if (linesFromFile.size() == 0) {
            readFile();
        }

        if (linesFromFile == null || linesFromFile.size() == 0) {
            return null;
        }

        return linesFromFile.get(0).split(csvSeparator);
    }

    /**
     * Creates a list of arrays with values from single string representing a
     * row in csv file. That is, the strings are splitted with separator and one
     * array contains all values of one row.
     *
     * TODO: don't ignore empty values! "hello;;world" -> [hello,null,world]
     *
     * @param csvSeparator separator to use in parsing
     * @return array list containing parsed values in arrays
     */
    private MyArrayList<String[]> parseValuesFromCSVLines(String csvSeparator) {
        MyArrayList<String[]> output = new MyArrayList<>();

        if (linesFromFile == null) {
            return null;
        }

        if (linesFromFile.size() == 0) {
            return output;
        }

        for (int i = 0; i < linesFromFile.size(); i++) {
            String line = linesFromFile.get(i);
            output.add(line.split(csvSeparator));
        }

        return output;
    }

    /**
     * Finds index of columns for X- Y- and Z-coordinates from header (first)
     * row of csv file. Other columns are ignored. Output is an array containing
     * in 1st element index of X-coordinate, in 2nd element index of
     * Y-coordinate and 3rd element containing index of Z-coordinate
     *
     * Note: Not used anymore!
     *
     * @param headerRow array of values in header row
     * @param XCoord name of X-coordinate column
     * @param YCoord name of Y-coordinate column
     * @param ZValue name of Z-coordinate or weight column
     * @return
     */
    private int[] mapColumnsForValues(String[] headerRow, String XCoord, String YCoord, String ZValue) {
        if (!utils.MyArrays.contains(headerRow, XCoord)
                || !utils.MyArrays.contains(headerRow, YCoord)
                || !utils.MyArrays.contains(headerRow, ZValue)) {
            return null;
        }

        int[] output = new int[3];

        for (int i = 0; i < headerRow.length; i++) {
            if (headerRow[i].equals(XCoord)) {
                output[0] = i;
            }

            if (headerRow[i].equals(YCoord)) {
                output[1] = i;
            }

            if (headerRow[i].equals(ZValue)) {
                output[2] = i;
            }
        }

        return output;

    }
}
