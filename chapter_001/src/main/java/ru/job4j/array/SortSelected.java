package ru.job4j.array;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class SortSelected {
    public static int[] sort(int[] data) {
        for (int x = 0; x < data.length; x++) {
            int min = MinDiapason.findMin(data, x, data.length - 1);
            int index = FindLoop.indexOf(data, min, 0, data.length - 1);
            if (x != index) {
                int count = data[index];
                data[index] = data[x];
                data[x] = count;
            }
        }
        return data;
    }

}
