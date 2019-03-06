package ru.job4j.models;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.12.2018
 */
public abstract class User {
    private int id = 0;
    private String name;
    private String login;
    private String email;
    private Date createDate;
    private String password;
    private String role;

    /**
     * Constector.
     *
     * @param name     - name.
     * @param login    - login.
     * @param email    - email.
     */
    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
    }

    /**
     * Constector.
     *
     * @param name     - name.
     * @param login    - login.
     * @param email    - email.
     * @param password - password.
     */
    public User(String name, String login, String email, String password) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
        this.password = password;
    }

    /**
     * Constructor.
     *
     * @param id       - id.
     * @param name     - name.
     * @param login    - login.
     * @param email    - email.
     */
    public User(int id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
        this.password = "";
    }

    /**
     * Constructor.
     *
     * @param id       - id.
     * @param name     - name.
     * @param login    - login.
     * @param email    - email.
     * @param date     - date.
     */
    public User(int id, String name, String login, String email, Date date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = date;
    }

    /**
     * Constructor.
     *
     * @param id       - id.
     * @param name     - name.
     * @param login    - login.
     * @param email    - email.
     * @param date     - date.
     * @param password - password.
     */
    public User(int id, String name, String login, String email, Date date, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = date;
        this.password = password;
    }

    /**
     * Constructor.
     *
     * @param id       - id.
     * @param name     - name.
     * @param login    - login.
     * @param email    - email.
     * @param password - password.
     */
    public User(int id, String name, String login, String email, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public abstract String getRole();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(email, user.email) && Objects.equals(createDate, user.createDate) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, createDate, password);
    }
}
