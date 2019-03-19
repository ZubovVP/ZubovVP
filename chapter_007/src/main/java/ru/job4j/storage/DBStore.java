package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Admin;
import ru.job4j.models.User;
import ru.job4j.models.Viewer;

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
    private static final String CREATE_TABLE = "CREATE TABLE users(id SERIAL PRIMARY KEY, name CHARACTER VARYING(50) NOT NULL , login CHARACTER VARYING(50) NOT NULL UNIQUE, email CHARACTER VARYING(50) NOT NULL UNIQUE, createDate TIMESTAMP NOT NULL, password CHARACTER VARYING(32) NOT NUll, role CHARACTER VARYING(20) NOT NUll);";
    private static boolean createTable = false;
    private static final Encryption ENCRYPT = Encryption.getInstance();


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
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, login, email, createDate, password, role) VALUES(?, ?, ?, ?, ?, ?)")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
            st.setString(5, ENCRYPT.encrypt(user.getPassword()));
            st.setString(6, user.getRole());
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
        checkTable();
        if (user.getRole().equals("admin")) {
            try (Connection conn = SOURCE.getConnection();
                 PreparedStatement st = conn.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ?  WHERE id = ?")) {
                st.setString(1, user.getName());
                st.setString(2, user.getLogin());
                st.setString(3, user.getEmail());
                st.setString(4, ENCRYPT.encrypt(user.getPassword()));
                st.setString(5, user.getRole());
                st.setInt(6, user.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("Failed to update element in the DataBase. User - id = {}, login = {}.", user.getId(), user.getLogin());
                throw new IncorrectDateException();
            }
        } else if (user.getRole().equals("viewer")) {
            try (Connection conn = SOURCE.getConnection();
                 PreparedStatement st = conn.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?, password = ?  WHERE id = ?")) {
                st.setString(1, user.getName());
                st.setString(2, user.getLogin());
                st.setString(3, user.getEmail());
                st.setString(4, ENCRYPT.encrypt(user.getPassword()));
                st.setInt(5, user.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("Failed to update element in the DataBase. User - id = {}, login = {}.", user.getId(), user.getLogin());
                throw new IncorrectDateException();
            }
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
        checkTable();
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
        checkTable();
        List<User> result = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("role").equals("admin")) {
                    result.add(new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role")));
                } else if (rs.getString("role").equals("viewer")) {
                    result.add(new Viewer(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role")));
                }
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
        checkTable();
        User resultId = null;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("role").equals("admin")) {
                        resultId = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"));
                    } else if (rs.getString("role").equals("viewer")) {
                        resultId = new Viewer(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find by id. Id = {}.", id);
        }
        return resultId;
    }

    /**
     * Check login and password from the DB.
     *
     * @param login - login.
     * @param password - password.
     * @return - result.
     */
    @Override
    public boolean isCredentional(String login, String password) {
        String ps = null;
        boolean result;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT password FROM users WHERE login = ?")) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ps = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find login. Login = {}.", login);
        }
        result = ENCRYPT.encrypt(password).equals(ps);
        return result;
    }

    /**
     * Close connection.
     */
    @Override
    public void close() {
        try {
            SOURCE.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to close BasicDataSource");
        }
    }


    /**
     * Find user by login of the element.
     *
     * @param login - login.
     * @return - user.
     */
    @Override
    public User findByLogin(String login) {
        User user = null;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE login = ?")) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("role").equals("admin")) {
                        user = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"));
                    } else if (rs.getString("role").equals("viewer")) {
                        user = new Viewer(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find login. Login = {}.", login);
        }
        return user;
    }

    /**
     * Check table existence.
     * If the table is not created, create a table.
     */
    private void checkTable() {
        if (!createTable) {
            try (Connection conn = SOURCE.getConnection()) {
                DatabaseMetaData dbm = conn.getMetaData();
                ResultSet rs = dbm.getTables(null, null, "entry", null);
                if (!rs.next()) {
                    PreparedStatement st = conn.prepareStatement(CREATE_TABLE);
                    st.executeUpdate();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            createTable = true;
        }
    }
}