package ru.job4j.start;

import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    protected Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    protected Item findByName(String name) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(name)) {
                result = item;
                break;
            }
        }
        return result;
    }
    public void replace(Item item) {
        for (Item result : this.items) {
            if (result != null && result.getId().equals(item.getId())) {
                this.items.set(this.items.indexOf(result), item);
            }
        }
    }

    public void delete(String id) {
        for (Item result : this.items) {
            if (result != null && result.getId().equals(id)) {
                this.items.remove(result);
                break;
            }
        }
    }

    public  List<Item> getAll() {
        return this.items;
    }

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
