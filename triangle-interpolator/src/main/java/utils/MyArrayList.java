package utils;

/**
 * 
 * @author lroni
 * @param <T> 
 */
public class MyArrayList<T> {

    private T[] array;
    private int index;

    public MyArrayList() {
        array = (T[]) new Object[8];
        index = 0;
    }

    public int size() {
        return this.index;
    }

    public void add(T obj) {
        if (index == array.length) {
            this.increaseSize();
        }

        index++;
        array[index] = obj;
    }

    public T get(int i) {
        if (i > index || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        return array[i];
    }

    public boolean remove(T obj) {
        int objIndex = this.indexOf(obj);

        if (objIndex < 0) {
            return false;
        }
        
        //...
        
        throw new UnsupportedOperationException();
    }

    public int indexOf(T obj) {
        for (int i = 0; i < array.length; i++) {
            boolean found = obj == null ? get(i) == null : obj.equals(get(i));
            if (found) {
                return i;
            }
        }
        return -1;
    }

    private void increaseSize() {
        T[] newArray = (T[]) new Object[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

}
