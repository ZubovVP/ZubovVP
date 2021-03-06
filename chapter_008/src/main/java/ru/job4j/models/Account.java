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
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    public Account(int id, String name, String phone) {
        this(name, phone);
        this.id = id;
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
