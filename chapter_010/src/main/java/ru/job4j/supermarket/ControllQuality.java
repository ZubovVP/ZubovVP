package ru.job4j.supermarket;

import ru.job4j.supermarket.foods.Food;
import ru.job4j.supermarket.strorage.*;

import java.util.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.12.2019.
 */
public class ControllQuality implements Actions<Food> {
    private List<AbstractStorage> storages;

    public ControllQuality(List<AbstractStorage> storages) {
        this.storages = storages;
    }

    /**
     * Check food.
     *
     * @param food - food.
     * @return - result.
     */
    @Override
    public boolean accept(Food food) {
        boolean result = false;
        for (AbstractStorage storage : this.storages) {
            if (storage.accept(food)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Resort foods from storages.
     */
    @Override
    public void resort() {
        Queue<Food> queue = new ArrayDeque<>();
        for (AbstractStorage storage : this.storages) {
            if (!storage.isTrash()) {
                queue.addAll(storage.getList());
                storage.getList().clear();
            }
        }
        while (!queue.isEmpty()) {
            accept(queue.poll());
        }
    }
}
