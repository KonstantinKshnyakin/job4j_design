package ru.job4j.it;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = row; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (row < data.length && column < data[row].length) {
            return data[row][column++];
        } else {
            while (row < data.length && column == data[row].length) {
                column = 0;
                row++;
            }
        }
        return data[row][column++];
    }
}
