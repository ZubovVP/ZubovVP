package ru.job4j.convert;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 06.02.2018
 */
 class User {

    private String name;
    private int id;
    private String city;

     User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

     int getId() {
        return id;
    }

     String getName() {
        return name;
    }
}
