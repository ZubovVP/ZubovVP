package ru.job4j.models;

import java.util.Date;

/**
 * Admin class.
 */
public class Admin extends User {

    public Admin(String name, String login, String email, String password, String country, String city) {
        super(name, login, email, password, country, city);
    }
    //CHECKSTYLE:OFF
    public Admin(int id, String name, String login, String email, Date date, String password, String country, String city) {
        super(id, name, login, email, date, password, country, city);
    }
    //CHECKSTYLE:ON

    public Admin(int id, String name, String login, String email, String password, String country, String city) {
        super(id, name, login, email, password, country, city);
    }

    /**
     * Get role.
     *
     * @return - role.
     */
    @Override
    public String getRole() {
        return "admin";
    }
}
