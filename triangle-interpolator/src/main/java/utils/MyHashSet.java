package utils;

/**
 * Resizable HashSet implementation using {@link utils.MyArrayList}
 *
 * @author lroni
 */
public class MyHashSet<T> {

    private MyArrayList<T>[] array;
    private int size;

    /**
     * Creates a new HashSet, with initial size of 8.
     */
    public MyHashSet() {
        array = new MyArrayList[8];
        size = 0;
    }

    /**
     * Returns how many elements this set contains.
     *
     * @return amount of elements in this set
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if given objects is found in hash set
     *
     * @param obj object to search for
     * @return true if found, else false
     */
    public boolean contains(T obj) {
        MyArrayList<T> list = this.getList(obj);
        if (list.size() == 0) {
            return false;
        }

        return list.indexOf(obj) >= 0;
    }

    /**
     * Adds a new object to hash set. If same object (comparison done with
     * equals()) found, replaces the old object with new. After adding check if
     * the amount of elements in hash set is over 75% of the size of array. If
     * the load factor is over 75%, the array is increased.
     *
     *
     * @param obj Object to add
     */
    public void add(T obj) {
        MyArrayList<T> objectsInIndex = this.getList(obj);
        int index = objectsInIndex.indexOf(obj);

        if (index < 0) {
            objectsInIndex.add(obj);
            this.size++;
        } else {
            objectsInIndex.set(index, obj);
        }

        if (1.0 * this.size / this.array.length > 0.75) {
            increaseSize();
        }
    }

    /**
     * Removes given object from hash set. Returns true if the given object was
     * found and removed, otherwise returns false and no changes to hash set was
     * made.
     *
     * @param obj Object to remove
     * @return true if object was removed, false if no changes to hash set was
     * made
     */
    public boolean remove(T obj) {
        MyArrayList<T> list = this.getList(obj);
        if (list.size() == 0) {
            return false;
        }

        int indexOfObj = list.indexOf(obj);
        if (indexOfObj < 0) {
            return false;
        }
        
        size--;
        return list.remove(obj);
    }

    /**
     * Retrieve array list for given object's hash code. If none found creates
     * new empty list to index and returns it.
     *
     * @param obj Obejct to serach for
     * @return
     */
    private MyArrayList<T> getList(T obj) {
        int hashCode = Math.abs(obj.hashCode() % array.length);

        if (array[hashCode] == null) {
            array[hashCode] = new MyArrayList<>();
        }

        return array[hashCode];
    }

    /**
     * Doubles the size of array and copies old array's content to it.
     */
    private void increaseSize() {
        MyArrayList<T>[] newArray = new MyArrayList[this.array.length * 2];

        for (int i = 0; i < this.array.length; i++) {
            this.copyListToArray(newArray, i);
        }

        this.array = newArray;
    }

    /**
     * Copies list's content from given index to new array. Calculate new
     * indexes for items as the length of array has changed.
     *
     * @param newArray new array where to copy
     * @param index index where to copy from
     */
    private void copyListToArray(MyArrayList<T>[] newArray, int index) {
        if (this.array[index] == null) {
            return;
        }

        for (int i = 0; i < this.array[index].size(); i++) {
            T obj = this.array[index].get(i);

            int hasCode = Math.abs(obj.hashCode() % newArray.length);
            if (newArray[hasCode] == null) {
                newArray[hasCode] = new MyArrayList<>();
            }

            newArray[index].add(obj);
        }
    }

    @Override
    public String toString() {
        String output = "[";

        for (int i = 0; i < array.length; i++) {
            MyArrayList<T> list = this.array[i];
            if (list == null) {
                continue;
            }

            if (list.size() == 0) {
                continue;
            }

            for (int z = 0; z < list.size(); z++) {
                output += list.get(z) + ", ";
            }
        }

        output += "]";
        return output;
    }

}
