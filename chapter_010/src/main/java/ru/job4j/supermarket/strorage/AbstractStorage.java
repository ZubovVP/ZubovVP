package ru.job4j.supermarket.strorage;

import ru.job4j.supermarket.foods.Food;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public abstract class AbstractStorage implements Store<Food> {
    private List<Food> list;

    public AbstractStorage() {
        this.list = new ArrayList<>();
    }

    public AbstractStorage(List<Food> foods) {
        this.list = foods;
    }


    protected boolean add(Food food) {
        return this.getList().add(food);
    }

    public List<Food> getList() {
        return list;
    }

    /**
     * Calculate days between date1 and date2
     *
     * @param date1 - LocalDate1.
     * @param date2 - LocalDate2.
     * @return - quantity days.
     */
    public double checkDate(LocalDate date1, LocalDate date2) {
        Period p = Period.between(date1, date2);
        return (p.getYears() * 365) + (p.getMonths() * 30) + p.getDays();
    }

    /**
     * Delete food from a storage.
     *
     * @param food - food.
     * @return - result.
     */
    public boolean delete(Food food) {
        return this.list.remove(food);
    }
}
