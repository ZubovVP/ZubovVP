package ru.job4j.start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ZubovVP on 07.10.2018
 * zubovvp@yadndex.ru
 */
public class Config {
    private Connection con;

    /**
     * Constructor.
     */
    public Config() {
        try {
            String password = "password";
            String userName = "postgres";
            String url = "jdbc:postgresql://localhost:5432/tracker_storage";
            this.con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get connection.
     *
     * @return - Connection.
     */
    public Connection getConn() {
        return con;
    }
}
