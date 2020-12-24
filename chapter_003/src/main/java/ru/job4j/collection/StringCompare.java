package ru.job4j.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.12.2020.
 */
public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        int delta = o1.length() >= o2.length() ? o2.length() : o1.length();
        for (int x = 0; x < delta; x++) {
            result = Character.compare(o1.charAt(x), o2.charAt(x));
            if (result != 0) {
                break;
            }
        }
        if (result == 0 && o1.length() != o2.length()) {
            result = o1.length() > o2.length() ? o1.charAt(o1.length() - 1) : -o1.charAt(o1.length() - 1);
        }
        return result;
    }
}
