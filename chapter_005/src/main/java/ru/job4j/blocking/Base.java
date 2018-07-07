package ru.job4j.blocking;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ZubovVP on 23.06.2018
 * zubovvp@yadndex.ru
 */
public class Base {
    private int id;
    private int version;
    private String name;

    /**
     * Constructor.
     *
     * @param name - name the Base.
     */
    public Base(String name) {
        this.id = ThreadLocalRandom.current().nextInt(1_000_000_000);
        this.version = 0;
        this.name = name;
    }

    /**
     * Get id of the base.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set id of the base.
     *
     * @param id - id.
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Get version of the Base.
     *
     * @return - version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Get name of the Base.
     *
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the Base.
     *
     * @param name - name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Increment version of the Base.
     *
     * @return - Base.
     */
    public synchronized Base incrementVersion() {
        this.version += 1;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Base base = (Base) o;

        if (id != base.id) {
            return false;
        }
        return name != null ? name.equals(base.name) : base.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
