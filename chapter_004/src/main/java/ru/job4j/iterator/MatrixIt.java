package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 11.01.2021.
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = this.row; i < data.length; i++) {
            for (int j = this.column; j < data[i].length; j++) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        int t;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[row].length == 0) {
            this.row++;
        }
        if (column <= data[row].length - 1) {
            t = data[row++][column];
            column = 0;
        } else {
            t = data[row][column++];
        }
        return t;
    }
}