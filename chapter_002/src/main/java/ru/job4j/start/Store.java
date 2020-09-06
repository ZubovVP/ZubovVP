package ru.job4j.start;

import ru.job4j.models.Item;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 06.09.2020.
 */
public interface Store {
    Item add(Item item);

    boolean replace(Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
