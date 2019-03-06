package ru.job4j.models;

import java.util.Date;

/**
 * Admin class.
 */
public class Admin extends User {

    public Admin(String name, String login, String email, String password) {
        super(name, login, email, password);
    }

    public Admin(int id, String name, String login, String email, Date date, String password) {
        super(id, name, login, email, date, password);
    }

    public Admin(int id, String name, String login, String email, String password) {
        super(id, name, login, email, password);
    }

    /**
     * Get role.
     * @return - role.
     */
    @Override
    public String getRole() {
        return "admin";
    }
}
