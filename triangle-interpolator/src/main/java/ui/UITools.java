/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author lroni
 */
public class UITools {

    public static int readNumber(Scanner sc, int min, int max, int defaultValue) {
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

    public static int readNumber(Scanner sc, int min, int max) {
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

    public static void printColumns(String[] headers) {
        String output = "";
        for (int i = 0; i < headers.length; i++) {
            output += "[" + (i + 1) + "] " + headers[i] + " ";
        }
        System.out.println(output);
    }

    public static void printFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println("[" + (i + 1) + "] " + files[i].getName());
        }
    }
}
