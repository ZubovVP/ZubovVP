package ru.job4j.foods;

import java.time.LocalDate;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public class Water extends Food {
    public Water(String name, LocalDate expiryDate, LocalDate createDate, double price, int disscount) {
        super(name, expiryDate, createDate, price, disscount);
    }
}
