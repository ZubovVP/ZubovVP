package ru.job4j.supermarket.strorage;

import ru.job4j.supermarket.Store;
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
public abstract class Storage implements Store {
    private List<Food> list;

    public Storage() {
        this.list = new ArrayList<>();
    }

    public void add(Food food) {
        this.getList().add(food);
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
}
