package ru.job4j.synchro;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class User {
    private final int id;
    private int amount;

    /**
     * Constructor.
     *
     * @param id - id of user.
     * @param amount - amount of user.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Get id of user.
     *
     * @return - id.
     */
    public  int getId() {
        return this.id;
    }

    /**
     * Get amount of user.
     *
     * @return - amount.
     */
    public  int getAmount() {
        return this.amount;
    }

    /**
     * Change amount of user.
     *
     * @param amount - new amount.
     */
    public  void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        return amount == user.amount;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        return result;
    }
}
