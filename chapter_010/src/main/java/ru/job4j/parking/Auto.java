package ru.job4j.parking;

import java.util.Objects;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.11.2019.
 */
public abstract class Auto {
    private final String name;
    private final String type;

    /**
     * Constructor.
     *
     * @param name - name auto.
     * @param type - type auto.
     */
    public Auto(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return Objects.equals(name, auto.name) && Objects.equals(type, auto.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
