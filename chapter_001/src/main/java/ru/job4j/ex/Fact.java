package ru.job4j.ex;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 17.12.2020.
 */
public class Fact {
    public int calc(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("wrong parameter - " + n);
        }
        int rsl = 1;
        for (int index = 1; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }

    public static void main(String[] args) {
        Fact fact = new Fact();
        fact.calc(-3);
    }

}
