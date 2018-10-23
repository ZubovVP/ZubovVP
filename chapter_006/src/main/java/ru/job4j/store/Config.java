package ru.job4j.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ZubovVP on 13.10.2018
 * zubovvp@yadndex.ru
 */
public class Config {
    private Connection con;

    /**
     * Get connection.
     *
     * @return - Connection.
     */
    public Connection getConn() {
        try {
            String password = "password";
            String userName = "postgres";
            String url = "jdbc:postgresql://localhost:5432/store_db";
            this.con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
