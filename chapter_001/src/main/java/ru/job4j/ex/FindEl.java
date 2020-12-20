package ru.job4j.ex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 17.12.2020.
 */
public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i] != null && key.equals(value[i])) {
                rsl = i;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Value doesn't have this key - " + key);
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] value = {"1", "2", "3", "4"};
        try {
            indexOf(value, "4");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
