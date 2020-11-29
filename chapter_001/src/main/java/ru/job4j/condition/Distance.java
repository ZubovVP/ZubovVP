package ru.job4j.condition;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.11.2020.
 */
public class Distance {

    /**
     * Calculate distance between points.
     *
     * @param x1 - x1.
     * @param y1 - y1.
     * @param x2 - x2.
     * @param y2 - y2.
     * @return - result
     */
    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        double result = new Distance().distance(0, 0, 2, 0);
        System.out.println("result (0, 0) to (2, 0) " + result);
    }
}
