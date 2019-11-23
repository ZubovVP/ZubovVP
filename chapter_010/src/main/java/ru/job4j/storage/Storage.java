package ru.job4j.storage;

import ru.job4j.foods.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public abstract class Storage {
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
}
