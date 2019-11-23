package ru.job4j.sort;

import ru.job4j.foods.Food;
import ru.job4j.storage.Shop;
import ru.job4j.storage.Trash;
import ru.job4j.storage.Warehouse;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public class ControllQuality {
    private Shop shop;
    private Trash trash;
    private Warehouse warehouse;

    /**
     * Constructor.
     *
     * @param shop - shop.
     * @param trash - trash.
     * @param warehouse - warehouse.
     */
    public ControllQuality(Shop shop, Trash trash, Warehouse warehouse) {
        this.shop = shop;
        this.trash = trash;
        this.warehouse = warehouse;
    }

    /**
     * Sort food.
     * If defrence date (create date and expiry date) < 25%, this food go a warehouse.
     * If defrence date (create date and expiry date) > 25% and < 75, this food go a shop.
     * If defrence date (create date and expiry date) > 75%, that food go a shop and plus discount.
     * If expiry date > now date, this food go a trash.
     * @param food  - food (object extends abstract class Food)
     */
    public void control(Food food) {
        if (food.getExpiryDate().isBefore(LocalDate.now())) {
            this.trash.add(food);
        } else {
            double difference1 = checkDate(food.getCreateDate(), food.getExpiryDate());
            double difference2 = checkDate(food.getCreateDate(), LocalDate.now());
            if (difference2 / difference1 >= 0.75) {
                food.setDisscount(35);
                this.shop.add(food);
            } else if (difference2 / difference1 <= 0.25) {
                this.warehouse.add(food);
            } else {
                this.shop.add(food);
            }
        }
    }

    /**
     * Calculate days between date1 and date2
     *
     * @param date1 - LocalDate1.
     * @param date2 - LocalDate2.
     * @return - quantity days.
     */
    private double checkDate(LocalDate date1, LocalDate date2) {
        Period p = Period.between(date1, date2);
        return (p.getYears() * 365) + (p.getMonths() * 30) + p.getDays();
    }

    public Shop getShop() {
        return shop;
    }

    public Trash getTrash() {
        return trash;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
