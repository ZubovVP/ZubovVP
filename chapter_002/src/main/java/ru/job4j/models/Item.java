package ru.job4j.models;

import javax.persistence.*;
import java.util.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private long created;

    public Item() {

    }

    public Item(String name, String description, long createOfDate, int id) {
        this(name, description, createOfDate);
        this.id = id;
    }

    public Item(String name, String description, long createOfDate) {
        this.name = name;
        this.description = description;
        this.created = createOfDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(created, item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreateOfDate() {
        return created;
    }

    public void setCreateOfDate(long createOfDate) {
        this.created = createOfDate;
    }
}