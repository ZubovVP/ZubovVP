package ru.job4j.generator;

import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.12.2019.
 */
public interface Checkable {
    String checkLine(Map<String, String> data, String line) throws CheckExeption;
}
