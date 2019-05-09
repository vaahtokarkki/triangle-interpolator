package io;

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
     * rows in file. Throws FileNotFoundException if invalid file name.
     *
     * @param fileName file to read
     * @return array list containing rows in file or null if caught an
     * exception.
     * @throws java.io.FileNotFoundException
     */
    public static MyArrayList<String> readRows(String fileName) throws FileNotFoundException {

        MyArrayList<String> output = new MyArrayList<>();

        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            output.add(line);
        }

        return output;
    }

}
