package utils;

/**
 * Available color schemes.
 */
public enum ColorScheme {

    /**
     * Used when values are sorted, colors from light red to dark red
     */
    SEQUENTIAL,
    /**
     * Used when values are sorted and data set divides into two parts. For
     * example temperatures, where value 0 is dividing. Colors from dark blue to
     * dark red.
     */
    DIVERGING
}
