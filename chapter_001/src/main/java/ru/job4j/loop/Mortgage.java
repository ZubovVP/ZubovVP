package ru.job4j.loop;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.12.2020.
 */
public class Mortgage {

    /**
     * Calculate how many year need for finish mortgage.
     *
     * @param amount  - sum of money.
     * @param salary  - salary employer of year.
     * @param percent - percent.
     * @return - year.
     */
    public static int year(int amount, int salary, double percent) {
        int year = 0;
        while (amount > 0) {
            amount += percent / 100 * amount;
            amount -= salary;
            year++;
        }
        return year;
    }
}
