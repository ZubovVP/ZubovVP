package ru.job4j.parser;

import java.sql.Timestamp;

/**
 * Offer
 *
 * Created by ZubovVP on 26.10.2018
 * zubovvp@yadndex.ru
 */
public class Offer {
    private String name;
    private String way;
    private Timestamp date;

    public Offer(String name, String way, Timestamp date) {
        this.name = name;
        this.way = way;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Offer offer = (Offer) o;

        if (name != null ? !name.equals(offer.name) : offer.name != null) {
            return false;
        }
        if (way != null ? !way.equals(offer.way) : offer.way != null) {
            return false;
        }
        return date != null ? date.equals(offer.date) : offer.date == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (way != null ? way.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
