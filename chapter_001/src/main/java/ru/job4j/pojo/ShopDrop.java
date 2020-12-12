package ru.job4j.pojo;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.12.2020.
 */
public class ShopDrop {
    public static Product[] leftShift(Product[] products, int index) {
        for (int x = index; x < products.length; x++) {
            if (x == products.length - 1) {
                products[x] = null;
                continue;
            }
            products[x] = products[x + 1];
            products[x + 1] = null;
        }
        return products;
    }
}
