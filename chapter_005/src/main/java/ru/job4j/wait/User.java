package ru.job4j.wait;

/**
 * Created by ZubovVP on 31.07.2018
 * zubovvp@yadndex.ru
 */
public class User {
    private String userName;
    private String email;

    /**
     * Constructor.
     *
     * @param userName - username.
     * @param email - email.
     */
    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    /**
     * getUserName.
     *
     * @return - userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setUserName.
     *
     * @param userName - new userName.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getEmail.
     *
     * @return - email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail.
     *
     * @param email - new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
