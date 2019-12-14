package ru.job4j.supermarket.foods;

import java.time.LocalDate;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public class Bread extends Food {
    public Bread(String name, LocalDate expiryDate, LocalDate createDate, double price, int disscount) {
        super(name, expiryDate, createDate, price, disscount);
    }
}
