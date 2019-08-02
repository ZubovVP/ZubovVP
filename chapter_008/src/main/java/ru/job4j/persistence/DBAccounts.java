package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Account;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.07.2019.
 */
public class DBAccounts  {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBAccounts INSTANCE = new DBAccounts();
    private static final Logger LOGGER = LogManager.getLogger(DBAccounts.class.getName());

    private static final String CREATE_TABLE = "CREATE TABLE accounts(id SERIAL PRIMARY KEY, name CHARACTER VARYING(100) NOT NULL, phone CHARACTER VARYING(15) NOT NULL UNIQUE);";

    private static boolean createTable = false;

    public static DBAccounts getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private DBAccounts() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(in);
            Class.forName(props.getProperty("driver-class-name"));
            SOURCE.setUrl(props.getProperty("url"));
            SOURCE.setUsername(props.getProperty("username"));
            SOURCE.setPassword(props.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (Exception e) {
            LOGGER.error("Failed to obtain JDBC connection {}.", SOURCE.getUrl());
        }
    }

    /**
     * Add account in the database.
     *
     * @param account - account.
     * @return - Account from database.
     */
    public Account add(Account account) {
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO accounts(name, phone) VALUES(?, ?)")) {
            st.setString(1, account.getName());
            st.setString(2, account.getPhone());
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to add accounts. Name = {}.", account.getName());
        }
        return get(account.getPhone());
    }

    /**
     * Get account.
     *
     * @param phone - phone.
     * @return - account.
     */
    public Account get(String phone) {
        checkTable();
        Account user = null;
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM accounts WHERE phone = ?")) {
            st.setString(1, phone);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    user = new Account(rs.getInt("id"), rs.getString("name"), rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find by phone. phone = {}.", phone);
        }
        return user;
    }

    /**
     * Update account.
     *
     * @param account - account.
     * @return - result.
     */
    public boolean update(Account account) {
        checkTable();
        boolean result = false;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("UPDATE accounts SET name = ?, phone = ? WHERE id = ?")) {
            st.setString(1, account.getName());
            st.setString(2, account.getPhone());
            st.setInt(3, account.getId());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("Failed to update element in the DataBase. User - id = {}, name = {}.", account.getId(), account.getName());
        }
        return result;
    }

    /**
     * Delete account from database.
     *
     * @param id - id of account.
     * @return - result.
     */
    public boolean delete(int id) {
        boolean result = false;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM accounts WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("Error to delete user from the DataBase. Id = {}.", id);
        }
        return result;
    }

    /**
     * Get all accounts from database.
     *
     * @return - Map of accounts.
     */
    public Map<Integer, Account> getAll() {
        checkTable();
        Map<Integer, Account> accounts = new HashMap<>();
        Account user;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM accounts");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                user = new Account(rs.getInt("id"), rs.getString("name"), rs.getString("phone"));
                accounts.put(user.getId(), user);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to get all the elements from Database.");
        }
        return accounts;
    }

    /**
     * Clear all elements from table.
     */
    public void clearAll() {
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM accounts")) {
            DBHalls.getInstance().clearAll();
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to clear all eleme.");
        }
    }

    /**
     * Check table existence.
     */
    public void checkTable() {
        if (!createTable) {
            createTable();
            createTable = true;
        }
    }

    /**
     * Create a table.
     */
    private void createTable() {
        try (Connection conn = SOURCE.getConnection()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "accounts", null);
            if (!rs.next()) {
                PreparedStatement st = conn.prepareStatement(DBAccounts.CREATE_TABLE);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
