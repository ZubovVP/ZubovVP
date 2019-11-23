package ru.job4j.foods;

import java.time.LocalDate;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.11.2019.
 */
public abstract class Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private int disscount;

    /**
     * Food
     *
     * @param name - name.
     * @param expiryDate - expiryDate.
     * @param createDate - CreateDate.
     * @param price - price.
     * @param discount - discount.
     */
    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.disscount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public int getDisscount() {
        return disscount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDisscount(int disscount) {
        this.disscount = disscount;
    }
}
