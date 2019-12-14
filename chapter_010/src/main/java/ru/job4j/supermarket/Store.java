package ru.job4j.supermarket;

import ru.job4j.supermarket.foods.Food;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.11.2019.
 */
public interface Store {
    boolean accept(Food food);
}
