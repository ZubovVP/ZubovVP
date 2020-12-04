package ru.job4j.array;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class SkipNegative {
    public static int[][] skip(int[][] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                if (array[x][y] < 0) {
                    array[x][y] = 0;
                }
            }
        }
        return array;
    }
}
