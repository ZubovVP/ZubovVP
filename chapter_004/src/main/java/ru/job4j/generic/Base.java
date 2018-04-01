package ru.job4j.generic;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Method can to get id model.
     *
     * @return - id.
     */
    public String getId() {
        return id;
    }
}