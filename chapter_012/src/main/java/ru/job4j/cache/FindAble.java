package ru.job4j.cache;

import java.io.IOException;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 12.03.2020.
 */
public interface FindAble<K, V> {
    V find(K key) throws IOException;
}
