package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 12.03.2020.
 */
public class SoftHashMap<K, V> extends AbstractMap<K, V> {
    private Map<K, SoftReference<V>> map = new HashMap<>();

    @Override
    public V get(Object key) {
        SoftReference<V> result = this.map.get(key);
        return result == null ? null : result.get();
    }

    @Override
    public V put(K key, V value) {
        return (V) this.map.put(key, new SoftReference<>(value));
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.map.size();
    }

}
