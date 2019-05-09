package utils;

/**
 * Resizable array implementation.
 *
 * @author lroni
 * @param <T>
 */
public class MyArrayList<T> {

    private T[] array;
    private int index;

    /**
     * Creates a new ArrayList. Default size of array is 8
     */
    public MyArrayList() {
        array = (T[]) new Object[8];
        index = 0;
    }

    /**
     * Returns the amount of items in this list.
     *
     * @return amount of items in list
     */
    public int size() {
        return this.index;
    }

    /**
     * Adds new item to the list.
     *
     * @param obj the item to add
     */
    public void add(T obj) {
        if (index == array.length) {
            this.increaseSize();
        }

        array[index] = obj;
        index++;
    }

    /**
     * Returns the item in given index of list. Throws IndexOutOfBoundsException
     * if given index is greater than size of the list or the index is bellow
     * zero.
     *
     * @param i index where from to return the item
     * @return the item in given index
     */
    public T get(int i) {
        if (i >= size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        return array[i];
    }

    /**
     * Removes first occurrence of given object. If the given object is null,
     * the items in list are compared to null. Otherwise comparison is done by
     * equals method.
     *
     * @param obj object to reomve
     * @return true if object is found and removed, or false is none was found
     */
    public boolean remove(T obj) {
        int objIndex = this.indexOf(obj);

        if (objIndex < 0) {
            return false;
        }

        this.shiftToLeft(objIndex);

        index--;
        array[index] = null;

        return true;
    }

    /**
     * Returns the index of given object's first occurrence.If the given object
     * is null, the items in list are compared to null. Otherwise comparison is
     * done by equals method.
     *
     * @param obj object to search for
     * @return index of first occurrence of given object, or -1 if none found
     */
    public int indexOf(T obj) {
        for (int i = 0; i < size(); i++) {
            boolean found = obj == null ? get(i) == null : obj.equals(get(i));
            if (found) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Replaces value in given index with new object and returns old object in
     * that index.
     *
     * @param index index where to set new object
     * @param obj new object
     * @return old object in given index, null if it was empty
     */
    public T set(int index, T obj) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        T output = array[index];
        array[index] = obj;

        return output;
    }

    private void increaseSize() {
        T[] newArray = (T[]) new Object[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    private void shiftToLeft(int startIndex) {
        if (startIndex < 0) {
            return;
        }

        for (int i = startIndex; i < index - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    @Override
    public String toString() {
        String output = "[";
        for (int i = 0; i < index; i++) {
            output += array[i];
            if (i < index - 1) {
                output += ", ";
            }
        }
        output += "]";

        return output;
    }

}
