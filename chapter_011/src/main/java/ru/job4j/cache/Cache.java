package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 12.03.2020.
 */
public class Cache<V, K> implements FindAble<K, V> {
    private AbstractMap cache;
    private String directory;

    /**
     * Constructor.
     *
     * @param directory - way to a directory.
     */
    public Cache(String directory) {
        this.directory = directory;
        this.cache = new SoftHashMap();
    }

    /**
     * Constructor.
     *
     * @param directory - - way to a directory.
     * @param map       - cache.
     */
    public Cache(String directory, AbstractMap map) {
        this.directory = directory;
        this.cache = map;
    }

    /**
     * Find text for help key.
     *
     * @param key - key.
     * @return - text.
     * @throws IOException
     */
    @Override
    public V find(K key) throws IOException {
        V result = (V) this.cache.get(key);
        return (result == null ? readText(key) : result);
    }

    /**
     * Read text from file.
     *
     * @param key - key.
     * @return - text.
     * @throws IOException
     */
    private V readText(K key) throws IOException {
        StringBuilder result = new StringBuilder();
        try (FileReader file = new FileReader(String.format("%s\\%s", this.directory, key));
             BufferedReader reader = new BufferedReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
        } catch (IOException e) {
            throw new IOException(String.format("Error find or read file, Name file = %s.", key));
        }
        addFileInCache(key, (V) result.toString());
        return (V) this.cache.get(key);
    }

    /**
     * Add key and text in the cache.
     *
     * @param key  - key.
     * @param text - text.
     */
    private void addFileInCache(K key, V text) {
        this.cache.put(key, text);
    }

    /**
     * @return size cache.
     */
    public int getSize() {
        return this.cache.size();
    }
}
