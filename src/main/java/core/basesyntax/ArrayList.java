package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        T removed = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == null && element == null)
                    || (array[i] != null && array[i].equals(element))) {
                T removed = array[i];

                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }

                array[--size] = null;
                return removed;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (array.length < minCapacity) {
            int newCapacity = array.length + array.length / 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }

            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
