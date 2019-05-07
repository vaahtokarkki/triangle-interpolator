package utils;

import java.awt.Color;

/**
 * Colors for interpolated map. Color schemes generated with
 * <a href="https://github.com/gka/chroma.js/">chroma.js</a> and cartography
 * colors from
 * <a href="https://github.com/axismaps/colorbrewer/">ColorBrewer</a>
 *
 */
public class MyColors {

    /**
     * Sequental color scheme, total of 25 colors.
     */
    public static final Color[] SEQUENTIAL = {new Color(255, 247, 236), new Color(255, 242, 224),
        new Color(254, 237, 212), new Color(254, 232, 200), new Color(254, 225, 186), new Color(253, 219, 172),
        new Color(253, 212, 158), new Color(253, 204, 149), new Color(253, 195, 141), new Color(253, 187, 132),
        new Color(253, 172, 118), new Color(252, 156, 103), new Color(252, 141, 89), new Color(248, 128, 83),
        new Color(243, 114, 78), new Color(239, 101, 72), new Color(231, 83, 58), new Color(223, 66, 45),
        new Color(215, 48, 31), new Color(203, 32, 21), new Color(191, 16, 10), new Color(179, 0, 0),
        new Color(162, 0, 0), new Color(144, 0, 0), new Color(127, 0, 0)};

    /**
     * Diverging color scheme, total of 25 colors.
     */
    public static final Color[] DIVERGING = {new Color(94, 79, 162),
        new Color(76, 103, 173),
        new Color(57, 127, 185),
        new Color(63, 151, 183),
        new Color(85, 175, 173),
        new Color(108, 196, 165),
        new Color(137, 208, 165),
        new Color(165, 219, 164),
        new Color(191, 229, 160),
        new Color(215, 239, 155),
        new Color(234, 247, 158),
        new Color(245, 251, 175),
        new Color(255, 255, 191),
        new Color(255, 242, 169),
        new Color(254, 229, 148),
        new Color(254, 212, 129),
        new Color(253, 191, 111),
        new Color(252, 169, 95),
        new Color(249, 142, 82),
        new Color(245, 114, 70),
        new Color(234, 93, 71),
        new Color(221, 74, 76),
        new Color(204, 52, 77),
        new Color(181, 26, 71),
        new Color(158, 1, 66)};

    public static final Color DEFAULT = new Color(0, 0, 0);

    /**
     * Get a color from sequential color scheme, mapped to given class when
     * specified amount of classes.
     *
     * @param currentClass class to get color for
     * @param classes total amount of classes used
     * @return
     */
    public static Color getSequentialColorForClass(double currentClass, int classes) {
        if (Double.isNaN(currentClass) || classes <= 0) {
            return DEFAULT;
        }

        if (classes > SEQUENTIAL.length) {
            classes = SEQUENTIAL.length;
        }

        double width = SEQUENTIAL.length / classes;
        int index = getIndex(currentClass, classes, SEQUENTIAL.length);

        if (index < width) {
            return SEQUENTIAL[0];
        } else if (index >= (SEQUENTIAL.length - width)) {
            return SEQUENTIAL[SEQUENTIAL.length - 1];
        }

        return SEQUENTIAL[index];
    }

    /**
     * Get a color from diverging color scheme, mapped to given class when
     * specified amount of classes. Zero value (or middle), approx white or
     * light orange on index 12.
     *
     * @param currentClass class to get color for
     * @param classes total amount of classes used
     * @return
     */
    public static Color getDivergingColorForClass(double currentClass, int classes) {
        if (Double.isNaN(currentClass) || classes <= 0) {
            return DEFAULT;
        }

        if (classes > DIVERGING.length) {
            classes = DIVERGING.length;
        }

        double width = DIVERGING.length / classes;
        int index = getIndex(currentClass, classes, DIVERGING.length);

        if (index < width) {
            return DIVERGING[0];
        } else if (index >= (DIVERGING.length - width)) {
            return DIVERGING[DIVERGING.length - 1];
        }

        return DIVERGING[index];
    }

    public static int getIndex(double currentClass, int classes, int length) {
        double width = length * 1.0 / classes;

        int startIndex = (int) (currentClass * width);

        int avg = 0;
        for (int i = startIndex; i < startIndex + width; i++) {
            avg += i;
        }
        avg = (int) (avg / width);

        return avg;
    }

}
