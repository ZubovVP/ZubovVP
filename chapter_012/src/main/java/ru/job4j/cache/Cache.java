package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 09.03.2020.
 */
public class Cache<K> {
    private final HashMap<K, SoftReference<String>> cache = new HashMap<>();
    private String directory;

    /**
     * Constructor.
     *
     * @param directory - way of directory.
     */
    public Cache(String directory) {
        this.directory = directory;
    }

    /**
     * Find text for help key.
     *
     * @param key - key.
     * @return - text.
     * @throws IOException
     */
    public String getText(K key) throws IOException {
        SoftReference result = this.cache.get(key);
        return result == null ? readText(key) : (String) result.get();
    }

    /**
     * Read text from file.
     *
     * @param key - key.
     * @return - text.
     * @throws IOException
     */
    private String readText(K key) throws IOException {
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
        addFileInCache(key, result.toString());
        return this.cache.get(key).get();
    }

    /**
     * Add key and text in the cache.
     *
     * @param key - key.
     * @param text - text.
     */
    private void addFileInCache(K key, String text) {
        this.cache.put(key, new SoftReference<>(text));
    }

    /**
     * @return size cache.
     */
    public int getSize() {
        return this.cache.size();
    }
}
