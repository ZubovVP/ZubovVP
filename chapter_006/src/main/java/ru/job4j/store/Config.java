package ru.job4j.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by ZubovVP on 13.10.2018
 * zubovvp@yadndex.ru
 */
public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    private final Properties values = new Properties();
    private Connection con;

    /**
     * Get connection.
     *
     * @return - Connection.
     */
    public Connection getConn() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("store.properties")) {
            this.values.load(in);
            Class.forName(this.values.getProperty("driver-class-name"));
            this.con = DriverManager.getConnection(get("url"), get("username"), get("password"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return con;
    }

    /**
     * Get value.
     *
     * @param key - key.
     * @return - value.
     */
    private String get(String key) {
        return this.values.getProperty(key);
    }
}
