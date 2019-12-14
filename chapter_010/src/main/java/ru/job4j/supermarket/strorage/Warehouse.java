package ru.job4j.supermarket.strorage;

import ru.job4j.supermarket.foods.Food;

import java.time.LocalDate;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public class Warehouse extends Storage {

    /**
     * Check food.
     * If difference between now and expiry date more 25%, that add in storage.
     *
     * @param food - food.
     * @return - result.
     */
    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (!food.getExpiryDate().isBefore(LocalDate.now())) {
            double difference1 = checkDate(food.getCreateDate(), food.getExpiryDate());
            double difference2 = checkDate(food.getCreateDate(), LocalDate.now());
            if (difference2 / difference1 <= 0.25) {
                this.add(food);
                result = true;
            }
        }
        return result;
    }
}
