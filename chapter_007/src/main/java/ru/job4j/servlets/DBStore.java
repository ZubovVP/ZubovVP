package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DBStore implements Store<User>, AutoCloseable {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    private static final Logger LOGGER = LoggerFactory.getLogger(DBStore.class);
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;


    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private DBStore() {
    }

    /**
     * Connect to database.
     */
    private boolean connect() {
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
            this.conn = SOURCE.getConnection();
            this.conn.setAutoCommit(false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return this.conn != null;
    }

    /**
     * Add element in the database.
     *
     * @param user - user.
     * @return - result.
     */
    @Override
    public boolean add(User user) {
        if (this.conn == null) {
            connect();
        }
        try {
            this.st = this.conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES(?, ?, ?, ?)");
            this.st.setString(1, user.getName());
            this.st.setString(2, user.getLogin());
            this.st.setString(3, user.getEmail());
            this.st.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
            this.st.executeUpdate();
            this.st.close();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error(e.getMessage(), e);
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
        if (this.conn == null) {
            connect();
        }
        try {
            this.st = this.conn.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?  WHERE id = ?");
            this.st.setString(1, user.getName());
            this.st.setString(2, user.getLogin());
            this.st.setString(3, user.getEmail());
            this.st.setInt(4, user.getId());
            this.st.executeUpdate();
            this.st.close();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error(e.getMessage(), e);
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
        if (this.conn == null) {
            connect();
        }
        try {
            this.st = this.conn.prepareStatement("DELETE FROM users WHERE id = ?");
            this.st.setInt(1, id);
            this.st.executeUpdate();
            this.st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error(e.getMessage(), e);
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
        if (this.conn == null) {
            connect();
        }
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM users");
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                result.add(new User(this.rs.getInt("id"), this.rs.getString("name"), this.rs.getString("login"), this.rs.getString("email"), new Date(this.rs.getTimestamp("createdate").getTime())));
            }
            this.rs.close();
            this.st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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
        if (this.conn == null) {
            connect();
        }
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            this.st.setInt(1, id);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                resultId = new User(this.rs.getInt("id"), this.rs.getString("name"), this.rs.getString("login"), this.rs.getString("email"), new Date(this.rs.getTimestamp("createdate").getTime()));
            }
            this.rs.close();
            this.st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultId;
    }

    /**
     * Close connection.
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        this.conn.commit();
        this.conn.setAutoCommit(true);
        if (this.rs != null) {
            this.rs.close();
        }
        if (this.st != null) {
            this.st.close();
        }
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                try {
                    this.conn.rollback();
                } catch (SQLException e1) {
                    LOGGER.error(e.getMessage(), e);
                }
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
