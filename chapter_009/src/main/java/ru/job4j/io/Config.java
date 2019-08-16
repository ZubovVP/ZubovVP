package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 11.08.2019.
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();


    /**
     * Constructor.
     *
     * @param path - path.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Load file.
     */
    public void load() {
        try (
                FileReader fr = new FileReader(new File(this.path));
                BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals("") && !line.contains("##")) {
                    String[] lineSep = line.split("=");
                    this.values.put(lineSep[0], lineSep[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get value.
     *
     * @param key - key.
     * @return - value.
     */
    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}