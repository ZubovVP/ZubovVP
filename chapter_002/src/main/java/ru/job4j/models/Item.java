package ru.job4j.models;

import java.util.Random;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private long create;
    private Random rd = new Random();

    public Item(String name, String description, long create, String id) {
        this.name = name;
        this.description = description;
        this.create = create;
        this.id = id;
    }

    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
        this.id = String.valueOf(System.currentTimeMillis() + rd.nextInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (create != item.create) {
            return false;
        }
        if (id != null ? !id.equals(item.id) : item.id != null) {
            return false;
        }
        if (name != null ? !name.equals(item.name) : item.name != null) {
            return false;
        }
        return description != null ? description.equals(item.description) : item.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (create ^ (create >>> 32));
        return result;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getCreate() {
        return create;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}