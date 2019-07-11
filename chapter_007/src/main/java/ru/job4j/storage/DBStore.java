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
    private static final String CREATE_TABLE1 = "CREATE TABLE users(id SERIAL PRIMARY KEY, name CHARACTER VARYING(50) NOT NULL , login CHARACTER VARYING(50) NOT NULL UNIQUE, email CHARACTER VARYING(50) NOT NULL UNIQUE, createDate TIMESTAMP NOT NULL, password CHARACTER VARYING(32) NOT NUll, role CHARACTER VARYING(20) NOT NUll);";
    private static final String CREATE_TABLE2 = "CREATE TABLE places (id SERIAL PRIMARY KEY, country CHARACTER VARYING(50) NOT NULL, city CHARACTER VARYING(50) NOT NULL, id_user INTEGER REFERENCES users(id) UNIQUE);";
    private static boolean createTable1 = false;
    private static boolean createTable2 = false;
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
             PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, login, email, createDate, password, role) VALUES(?, ?, ?, ?, ?, ?)");
             PreparedStatement st2 = conn.prepareStatement("INSERT INTO places(country, city, id_user) VALUES(?, ?,?)")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
            st.setString(5, ENCRYPT.encrypt(user.getPassword()));
            st.setString(6, user.getRole());
            st.executeUpdate();
            st2.setString(1, user.getCountry());
            st2.setString(2, user.getCity());
            st2.setInt(3, findIdByLogin(user.getLogin()));
            st2.executeUpdate();
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
                 PreparedStatement st = conn.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ?  WHERE id = ?");
                 PreparedStatement st2 = conn.prepareStatement("UPDATE places SET country = ?, city = ? WHERE id_user = ?")) {
                st.setString(1, user.getName());
                st.setString(2, user.getLogin());
                st.setString(3, user.getEmail());
                st.setString(4, ENCRYPT.encrypt(user.getPassword()));
                st.setString(5, user.getRole());
                st.setInt(6, user.getId());
                st.executeUpdate();
                st2.setString(1, user.getCountry());
                st2.setString(2, user.getCity());
                st2.setInt(3, user.getId());
                st2.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("Failed to update element in the DataBase. User - id = {}, login = {}.", user.getId(), user.getLogin());
                throw new IncorrectDateException();
            }
        } else if (user.getRole().equals("viewer")) {
            try (Connection conn = SOURCE.getConnection();
                 PreparedStatement st = conn.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?, password = ?  WHERE id = ?");
                 PreparedStatement st2 = conn.prepareStatement("UPDATE places SET country = ?, city = ? WHERE id_user = ?")) {
                st.setString(1, user.getName());
                st.setString(2, user.getLogin());
                st.setString(3, user.getEmail());
                st.setString(4, ENCRYPT.encrypt(user.getPassword()));
                st.setInt(5, user.getId());
                st.executeUpdate();
                st2.setString(1, user.getCountry());
                st2.setString(2, user.getCity());
                st2.setInt(3, user.getId());
                st2.executeUpdate();
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
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM places WHERE id_user = ?");
             PreparedStatement st2 = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            st2.setInt(1, id);
            st2.executeUpdate();
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
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users INNER JOIN places ON (users.id = places.id_user)");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("role").equals("admin")) {
                    result.add(new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"), rs.getString("country"), rs.getString("city")));
                } else if (rs.getString("role").equals("viewer")) {
                    result.add(new Viewer(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"), rs.getString("country"), rs.getString("city")));
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
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users INNER JOIN places ON (users.id = places.id_user) WHERE id_user = ?")) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("role").equals("admin")) {
                        resultId = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"), rs.getString("country"), rs.getString("city"));
                    } else if (rs.getString("role").equals("viewer")) {
                        resultId = new Viewer(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"), rs.getString("country"), rs.getString("city"));
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
     * @param login    - login.
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
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users INNER JOIN places ON (users.id = places.id_user) WHERE login = ?")) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("role").equals("admin")) {
                        user = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"), rs.getString("country"), rs.getString("city"));
                    } else if (rs.getString("role").equals("viewer")) {
                        user = new Viewer(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("email"), new Date(rs.getTimestamp("createdate").getTime()), rs.getString("role"), rs.getString("country"), rs.getString("city"));
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
     */
    private void checkTable() {
        if (!createTable1) {
            createTable(CREATE_TABLE1);
            createTable1 = true;
        }

        if (!createTable2) {
            createTable(CREATE_TABLE2);
            createTable2 = true;
        }
    }

    /**
     * Create a table.
     *
     * @param textCreate - SQL command.
     */
    private void createTable(String textCreate) {
        try (Connection conn = SOURCE.getConnection()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "entry", null);
            if (!rs.next()) {
                PreparedStatement st = conn.prepareStatement(textCreate);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private int findIdByLogin(String login) {
        int id = 0;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT id FROM users WHERE login = ?")) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find login. Login = {}.", login);
        }
        return id;
    }

    public static void main(String[] args) {
        new DBStore().add(new Admin("Duke1", "Duke1", "duke-ruller1@", "123", "aaa", "aaa"));
    }
}