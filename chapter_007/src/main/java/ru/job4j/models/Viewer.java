package ru.job4j.models;

import java.util.Date;

/**
 * Viewer class.
 */
public class Viewer extends User {

    public Viewer(String name, String login, String email, String password, String country, String city) {
        super(name, login, email, password, country, city);
    }
    //CHECKSTYLE:OFF
    public Viewer(int id, String name, String login, String email, Date date, String password, String country, String city) {
        super(id, name, login, email, date, password, country, city);
    }
    //CHECKSTYLE:ON

    public Viewer(int id, String name, String login, String email, String password, String country, String city) {
        super(id, name, login, email, password, country, city);
    }

    /**
     * Get role.
     *
     * @return - role.
     */
    @Override
    public String getRole() {
        return "viewer";
    }
}
