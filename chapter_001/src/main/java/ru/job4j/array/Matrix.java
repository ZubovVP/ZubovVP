package ru.job4j.array;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class Matrix {
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int x = 0; x < table.length; x++) {
            for (int y = 0; y < table[x].length; y++) {
                table[x][y] = (x + 1) * (y + 1);
            }
        }
        return table;
    }
}
