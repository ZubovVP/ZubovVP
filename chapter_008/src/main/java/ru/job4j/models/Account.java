package ru.job4j.models;

import java.util.Objects;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.07.2019.
 */
public class Account {
    private final int id;
    private final String name;
    private final String phone;

    public Account(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Account(String name, String phone) {
        this.id = 0;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    //CHECKSTYLE:OFF
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account acount = (Account) o;
        return id == acount.id &&
                Objects.equals(name, acount.name) &&
                Objects.equals(phone, acount.phone);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }
}
