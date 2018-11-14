package ru.job4j.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class Tracker implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    private Connection conn;

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


    public Item add(Item item) {
        if (this.conn == null) {
            connect();
        }
        try {
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO users(name, description, create_date, id_item) VALUES(?, ?, ?, ?)");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(item.getCreate()));
            st.setString(4, item.getId());
            st.executeUpdate();
            st.close();
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

    protected Item findById(String id) {
        Item resultId = null;
        if (this.conn == null) {
            connect();
        }
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE id_item = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultId = new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_date").getTime(), rs.getString("id_item"));
                if (resultId.getName() == null || resultId.getDescription() == null) {
                    resultId = null;
                    break;
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultId;
    }

    protected Item findByName(String name) {
        Item resultName = null;
        if (this.conn == null) {
            connect();
        }
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultName = new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_date").getTime(), rs.getString("id_item"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultName;
    }

    public void replace(Item item) {
        PreparedStatement st;
        if (this.conn == null) {
            connect();
        }
        try {
            st = conn.prepareStatement("UPDATE users SET name = ?, description = ? WHERE id_item = ?");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setString(3, item.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void delete(String id) {
        PreparedStatement st;
        if (this.conn == null) {
            connect();
        }
        try {
            st = conn.prepareStatement("DELETE FROM users WHERE id_item = ?");
            st.setString(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        if (this.conn == null) {
            connect();
        }
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_date").getTime(), rs.getString("id_item")));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        this.conn.commit();
        this.conn.setAutoCommit(true);
        if (conn != null) {
            try {
                conn.close();
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