package ru.job4j.supermarket.strorage;

import ru.job4j.supermarket.foods.Food;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public class Trash extends AbstractStorage {
    public Trash() {
    }

    public Trash(List<Food> foods) {
        super(foods);
    }

    /**
     * Check food.
     * If expiry date < now, that true.
     * If expiry date > now, that false.
     *
     * @param food - food.
     * @return - result.
     */
    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (food.getExpiryDate().isBefore(LocalDate.now())) {
            this.add(food);
            result = true;
        }
        return result;
    }
}
