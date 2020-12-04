package ru.job4j.array;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class Square {
    public static int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int x = 0; x < rst.length; x++) {
            rst[x] = (int) Math.pow(x, 2);
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] array = calculate(4);
        for (int index = 0; index < array.length; index++) {
            System.out.println(array[index]);
        }
    }
}
