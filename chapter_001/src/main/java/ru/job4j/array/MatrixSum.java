package ru.job4j.array;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class MatrixSum {
    public static int sum(int[][] array) {
        int rsl = 0;
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                rsl += array[x][y];
            }
        }
        return rsl;
    }
}
