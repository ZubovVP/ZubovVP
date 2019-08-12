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
                    String[] data = line.split("=");
                    this.values.put(data[0], data[1]);
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

    public static void main(String[] args) {
        Config con = new Config("C:\\projects\\ZubovVP\\chapter_009\\src\\main\\java\\ru\\job4j\\app.properties");
        //this.getClass().getResource("/img.png")
        con.load();
//        System.out.println(con.toString());
        System.out.println(con.value("hibernate.connection.password"));
    }
}