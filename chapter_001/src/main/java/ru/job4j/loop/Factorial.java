package ru.job4j.loop;

/**
 * Factorial.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$`
 * @since 0.1
 */
public class Factorial {
    /**
     * Программа предназначенна для вычисления факториала.
     *
     * @param n - число из которого вычисляется факториал
     * @return calc - результат факториала
     */
    public int calc(int n) {
        int factorial = 1;
        while (n > 0) {
            factorial *= n--;
        }
        return factorial;
    }
}
