package utils;

/**
 * Utils for arrays
 *
 * @author lroni
 */
public class MyArrays {

    /**
     * Checks if array contains given object.
     *
     * @param <T>
     * @param array array to search from
     * @param search object to search
     * @return
     */
    public static <T> boolean contains(T[] array, T search) {
        for (T v : array) {
            boolean found;
            if (v == null) {
                found = search == null;
            } else {
                found = search == null ? false : v.equals(search);
            }

            if (found) {
                return true;
            }
        }

        return false;
    }

}
