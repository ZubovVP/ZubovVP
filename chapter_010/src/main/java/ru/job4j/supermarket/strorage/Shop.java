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
public class Shop extends Storage {

    /**
     * Check food.
     * If result true, that food add the storage.
     * If result false, that food doesn't add the storage.
     * If difference between create and expiry date more 75%, that add discount.
     *
     * @param food - food.
     * @return - result.
     */
    @Override
    public boolean accept(Food food) {
        boolean result = true;
        if (!food.getExpiryDate().isBefore(LocalDate.now())) {
            double difference1 = checkDate(food.getCreateDate(), food.getExpiryDate());
            double difference2 = checkDate(food.getCreateDate(), LocalDate.now());
            if (difference2 / difference1 >= 0.75) {
                food.setDiscount(35);
                this.add(food);
            } else if (difference2 / difference1 > 0.25) {
                this.add(food);
            }
        } else {
            result = false;
        }
        return result;
    }
}
