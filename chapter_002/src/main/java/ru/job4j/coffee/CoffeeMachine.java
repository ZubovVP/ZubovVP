package ru.job4j.coffee;

import java.util.Arrays;
/**
 * CoffeeMachine.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
public class CoffeeMachine {
    private final int[] coin = {1, 2, 5, 10};
    /**
     * Метод позволяет позволяет расчитать сдачу при покупке товара с определённой ценой.
     * @param value - номинал внесённой купюры
     * @param price - цена за товар
     * @return Cell[] - массив для выдачи сдачи.
     */
     int[] changes(int value, int price) throws ImposibleGetCoffee {
         int position = 0;
         int debt = value - price;
         if (debt < 0) {
            throw new ImposibleGetCoffee("Please, give more money");
         }
         int[] changes = new int[100];
        for (int x = coin.length - 1; x >= 0; x--) {
            while ((debt / coin[x]) >= 1) {
                debt -= coin[x];
                changes[position++] = coin[x];
            }
        }
        return Arrays.copyOf(changes, position);
    }
}
