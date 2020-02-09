package ru.job4j.generator;

import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.12.2019.
 */
public class Validator {

    public boolean checkKey(Map<String, String> data, String key) {
        return data.containsKey(key);
    }
}
