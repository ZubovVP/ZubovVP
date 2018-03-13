package ru.job4j.bank;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Account {
    private int value;
    private String requisites;

    public Account(int value, String  requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public int getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
