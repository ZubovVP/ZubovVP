package ru.job4j.game;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 09.01.2020.
 */
public class Field {
    private Character[][] field;
    private int size;

    /**
     * Constructor.
     *
     * @param size - size of field.
     */
    public Field(int size) {
        this.size = size;
        if (this.size > 2) {
            this.field = new Character[this.size][this.size];
        } else {
            this.field = new Character[3][3];
        }
    }

    /**
     * Constructor.
     */
    public Field() {
        this(3);
    }

    /**
     * Insert symbol on the field.
     *
     * @param x      - x coordinate.
     * @param y      - y coordinate.
     * @param symbol - symbol.
     * @return - result.
     */
    public boolean add(int x, int y, char symbol) {
        boolean result = check(x, y);
        if (result) {
            this.field[x][y] = symbol;
        }
        return result;
    }

    /**
     * Check this plase in the field. Null = false, isn't null = false.
     *
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @return - result.
     */
    private boolean check(int x, int y) {
        return this.field[x][y] == null;
    }

    /**
     * Get symbol from field.
     *
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @return - symbol.
     */
    public char getSymbol(int x, int y) {
        return this.field[x][y] == null ? ' ' : this.field[x][y];
    }

    /**
     * Get size.
     *
     * @return - size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Clear all symbols on the field.
     */
    public void clearAll() {
        this.field = new Character[this.size][this.size];
    }

    /**
     * Get field.
     *
     * @return array.
     */
    public Character[][] getField() {
        return field;
    }
}
