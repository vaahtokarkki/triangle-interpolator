package ui;

import java.io.File;
import java.util.Scanner;

/**
 * Tools used for building UI. Mostly reading and validating inputs from user.
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

    public static String readString(Scanner sc, String defaultValue) {
        String output = sc.nextLine();

        if (output.trim().isEmpty()) {
            return defaultValue;
        }

        return output;
    }

    public static boolean readBoolean(Scanner sc, boolean defaultValue) {
        while (true) {
            String output = sc.nextLine();
            output = output.trim().toLowerCase();

            if (output.isEmpty()) {
                return defaultValue;
            } else if (output.equals("n") || output.equals("no")) {
                return false;
            } else if (output.equals("y") || output.equals("yes")) {
                return true;
            }

            System.out.println("Please give y/n");
        }
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
