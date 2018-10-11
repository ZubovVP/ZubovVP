package ru.job4j.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    private Connection conn;
    private PreparedStatement st;

    public Tracker(Config config) {
        this.conn = config.getConn();
    }

    public Tracker() {
    }

    public Item add(Item item) {
        try {
            st = conn.prepareStatement("INSERT INTO users(name, description, create_date, id_item) VALUES(?, ?, ?, ?)");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(item.getCreate()));
            st.setString(4, item.getId());
            st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return item;
    }

    protected Item findById(String id) {
        Item resultId = null;
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
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultId;
    }

    protected Item findByName(String name) {
        Item resultName = null;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultName = new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_date").getTime(), rs.getString("id_item"));
            }
            rs.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resultName;
    }

    public void replace(Item item) {

        try {
            st = conn.prepareStatement("UPDATE users SET name = ?, description = ? WHERE id_item = ?");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setString(3, item.getId());
            st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void delete(String id) {

        try {
            st = conn.prepareStatement("DELETE FROM users WHERE id_item = ?");
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_date").getTime(), rs.getString("id_item")));
            }
            rs.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}