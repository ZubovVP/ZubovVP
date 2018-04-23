package ru.job4j.map;

/**
 * The interface for Map collections.
 *
 * @param <K> - key
 * @param <V> - value
 */
public interface Map<K, V> {

    /**
     * Add element in collection.
     *
     * @param key - key.
     * @param value - value.
     * @return - result.
     */
    boolean insert(K key, V value);

    /**
     * Return element for key.
     *
     * @param key - key of element.
     * @return - element.
     */
    V get(K key);

    /**
     * Delete element from collection.
     *
     * @param key - key of element.
     * @return - resultn
     */
    boolean delete(K key);
}
