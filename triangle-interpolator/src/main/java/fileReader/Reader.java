package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utils.MyArrayList;

/**
 *
 * @author lroni
 */
public class Reader {

    /**
     * Reads a csv file from given filename and returns array list containing
     * rows in file.
     *
     * @param fileName file to read
     * @return array list containing rows in file or null if caught an
     * exception.
     */
    public static MyArrayList<String> readRows(String fileName) {

        MyArrayList<String> output = new MyArrayList<>();

        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                output.add(line);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

        return output;

    }

}
