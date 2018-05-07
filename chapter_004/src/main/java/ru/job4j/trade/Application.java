package ru.job4j.trade;

import java.util.Comparator;
import java.util.Random;

/**
 * Application.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Application {
    private long id;
    private String book;
    private String type;
    private String action;
    private double price;
    private int volume;
    private static final Random RN = new Random();


    /**
     * Constructor.
     *
     * @param book - name of the application.
     * @param type - type of the application.
     * @param action - action of the application.
     * @param price - price of the application.
     * @param volume - volume of the application.
     */
    public Application(String book, String type, String action, int price, int volume) {
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
        this.id = (System.currentTimeMillis() + RN.nextInt());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public static Comparator<Double> doubleDecrease = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return -Integer.compare(o1.intValue(), o2.intValue());
        }
    };
}