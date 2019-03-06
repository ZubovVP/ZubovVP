package ru.job4j.models;

import java.util.Date;

/**
 * Viewer class.
 */
public class Viewer extends User {

    public Viewer(String name, String login, String email, String password) {
        super(name, login, email, password);
    }

    public Viewer(int id, String name, String login, String email, Date date, String password) {
        super(id, name, login, email, date, password);
    }

    public Viewer(int id, String name, String login, String email, String password) {
        super(id, name, login, email, password);
    }

    /**
     * Get role.
     * @return - role.
     */
    @Override
    public String getRole() {
        return "viewer";
    }
}
