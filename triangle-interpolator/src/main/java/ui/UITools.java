package ui;

import java.io.File;
import java.util.Scanner;

import utils.ColorScheme;

/**
 * Tools used for building UI. Mostly reading and validating inputs from user.
 */
public class UITools {

    public static int readNumber(Scanner sc, int min, int max, int defaultValue) {
        int output;
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) {
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

    public static double readDouble(Scanner sc, double min, double max, double defaultValue) {
        double output;
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) {
                return defaultValue;
            }

            try {
                output = Double.parseDouble(line);
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

    public static String readString(Scanner sc, String defaultValue, int length) {
        String output = readString(sc, defaultValue);

        if (output.equals(defaultValue)) {
            return output;
        }

        while (output.length() != length) {
            System.out.println("Input length must be exactly " + length + " characters long!");
            output = readString(sc, defaultValue);
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

    public static ColorScheme readColorScheme(Scanner sc, String defaultValue) {
        while (true) {
            String input = sc.nextLine();
            input = input.trim().toUpperCase();
            if (input.isEmpty()) {
                return ColorScheme.valueOf(defaultValue.toUpperCase());
            }

            try {
                ColorScheme output = ColorScheme.valueOf(input);
                return output;
            } catch (Exception e) {
                System.out.println("Please give valid color scheme name");
            }
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

    public static boolean isValidFolder(String dataFolder) {
        File f = new File(dataFolder);
        return f.exists() && f.isDirectory();
    }

    static String defineDefaultFolder() {
        if (isValidFolder("./data")) {
            return "./data";
        } else if (isValidFolder("./triangle-interpolator/data")) {
            return "./triangle-interpolator/data";
        }
        return null;
    }
}
