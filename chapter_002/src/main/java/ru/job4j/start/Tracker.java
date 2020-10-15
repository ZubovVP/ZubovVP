package ru.job4j.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import ru.job4j.models.*;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
@Component
public class Tracker implements Store<Item>, AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    private static final String CREATE_TABLE = "CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR(25) NOT NULL, description TEXT NOT NULL, create_date TIMESTAMP, id_item VARCHAR(15));";
    private static boolean createTable = false;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;


    /**
     * Connect to database.
     */
    public boolean connect() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("tracker.properties")) {
            Properties props = new Properties();
            props.load(in);
            Class.forName(props.getProperty("driver-class-name"));
            this.conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
            this.conn.setAutoCommit(false);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.conn != null;
    }

    @Override
    public Item add(Item item) {
        if (this.conn == null) {
            connect();
        }
        checkTable();
        try {
            this.st = this.conn.prepareStatement("INSERT INTO users(id, name, description, create_date) VALUES(?, ?, ?, ?)");
            this.st.setInt(1, item.getId());
            this.st.setString(2, item.getName());
            this.st.setString(3, item.getDescription());
            this.st.setLong(4, item.getCreateOfDate());
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
        return item;
    }

    @Override
    public Item findById(int id) {
        Item resultId = null;
        if (this.conn == null) {
            connect();
        }
        checkTable();
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            this.st.setInt(1, id);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                resultId = new Item(this.rs.getString("name"), this.rs.getString("description"), this.rs.getLong("create_date"), this.rs.getInt("id"));
                if (resultId.getName() == null || resultId.getDescription() == null) {
                    resultId = null;
                    break;
                }
            }
            this.rs.close();
            this.st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultId;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> resultName = new ArrayList<>();
        if (this.conn == null) {
            connect();
        }
        checkTable();
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM users WHERE name = ?");
            this.st.setString(1, key);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                resultName.add(new Item(this.rs.getString("name"), this.rs.getString("description"), this.rs.getLong("create_date"), this.rs.getInt("id")));
            }
            this.rs.close();
            this.st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultName;
    }

    @Override
    public boolean replace(Item item) {
        if (this.conn == null) {
            connect();
        }
        checkTable();
        try {
            this.st = this.conn.prepareStatement("UPDATE users SET name = ?, description = ? WHERE id = ?");
            this.st.setString(1, item.getName());
            this.st.setString(2, item.getDescription());
            this.st.setInt(3, item.getId());
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

    @Override
    public boolean delete(int id) {
        if (this.conn == null) {
            connect();
        }
        checkTable();
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

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        if (this.conn == null) {
            connect();
        }
        checkTable();
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM users");
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                result.add(new Item(this.rs.getString("name"), this.rs.getString("description"), this.rs.getLong("create_date"), this.rs.getInt("id")));
            }
            this.rs.close();
            this.st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Check table existence.
     * If the table is not created, create a table.
     */
    private void checkTable() {
        if (!createTable) {
            try {
                DatabaseMetaData dbm = conn.getMetaData();
                ResultSet rs = dbm.getTables(null, null, "users", null);
                if (!rs.next()) {
                    PreparedStatement st = conn.prepareStatement(CREATE_TABLE);
                    st.executeUpdate();
                    createTable = true;
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.commit();
        this.conn.setAutoCommit(true);
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