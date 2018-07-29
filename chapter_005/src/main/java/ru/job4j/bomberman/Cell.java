package ru.job4j.bomberman;

/**
 * Created by ZubovVP on 14.07.2018
 * zubovvp@yadndex.ru
 */
public class Cell {
    private int x;
    private int y;

    /**
     * Constuctor.
     *
     * @param x - coordinate x.
     * @param y - coordinate y.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x.
     *
     * @return - x.
     */
    public int getX() {
        return x;
    }

    /**
     * Get y.
     *
     * @return - y.
     */
    public int getY() {
        return y;
    }

    /**
     * Change x.
     *
     * @param x - x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Change y.
     *
     * @param y - y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
