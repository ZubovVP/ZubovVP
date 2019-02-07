package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 01.01.2019
 */
public class DBStore implements Store<User> {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    private static final Logger LOGGER = LogManager.getLogger(DBStore.class.getName());


    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private DBStore() {
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
     * Add element in the database.
     *
     * @param user - user.
     * @return - result.
     */
    @Override
    public boolean add(User user) {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES(?, ?, ?, ?)")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to add element in the DataBase. User - name = {}, login = {}, email = {}.", user.getName(), user.getLogin(), user.getEmail());
            throw new IncorrectDateException();
        }
        return true;
    }

    /**
     * Update element in the database.
     *
     * @param user - user.
     * @return -result.
     */
    @Override
    public boolean update(User user) {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?  WHERE id = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update element in the DataBase. User - id = {}, login = {}.", user.getId(), user.getLogin());
            throw new IncorrectDateException();
        }
        return true;
    }

    /**
     * Delete element in the database.
     *
     * @param id - id of the user.
     * @return - result.
     */
    @Override
    public boolean delete(int id) {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error to delete user from the DataBase. Id = {}.", id);
            return false;
        }
        return true;
    }

    /**
     * Return all the users in the database.
     *
     * @return - all users.
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                result.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime())));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to get all the elements from Database.");
        }
        return result;
    }

    /**
     * Find by id of the element.
     *
     * @param id - id of the element.
     * @return - user.
     */
    @Override
    public User findById(int id) {
        User resultId = null;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultId = new User(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find by id. Id = {}.", id);
        }
        return resultId;
    }

//    @Override
//    public boolean contains(User element) {
//        boolean result = false;
//        try (Connection conn = SOURCE.getConnection();
//             PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
//            st.setInt(1, element.getId());
//            try (ResultSet rs = st.executeQuery()) {
//               result = rs.getInt("id") > 0;
//            }
//        } catch (SQLException e) {
//            LOGGER.error("User( id = {} ) does not exist.", element.getId());
//        }
//        return result;
//    }

    @Override
    public void close() {
        try {
            SOURCE.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to close BasicDataSource");
        }
    }
}