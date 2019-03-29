package ru.job4j.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZubovVP on 13.10.2018
 * zubovvp@yadndex.ru
 */
public class StoreSQL implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class);
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    private static final String ADD_ELEMENT = "INSERT INTO entry(field) VALUES(?)";
    private static final String SELECT_TABLE = "SELECT * FROM entry";
    private static final String CREATE_TABLE = "CREATE TABLE entry (field INT NOT NULL);";
    private static final String SELECT_ELEMENT = "SELECT * from entry";
    private static final String DELETE_TABLE = "DELETE FROM entry";
    static final long TIME_START = System.currentTimeMillis();


    /**
     * Connect to database.
     */
    public void connect() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("store.properties")) {
            Properties props = new Properties();
            props.load(in);
            Class.forName(props.getProperty("driver-class-name"));
            this.conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
            this.conn.setAutoCommit(false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Create recods in the db.
     *
     * @param n - quantity of generated records.
     */
    public void generate(int n) {
        if (this.conn == null) {
            connect();
        }
        checkTable();
        for (int x = 1; x <= n; x++) {
            try {
                this.st = conn.prepareStatement(ADD_ELEMENT);
                this.st.setInt(1, x);
                this.st.executeUpdate();
            } catch (SQLException e) {
                try {
                    this.conn.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Get all entries in the table.
     *
     * @return - List<Field>.
     */
    public List<StoreXML.Field> getAllEntries() {
        if (this.conn == null) {
            connect();
        }
        List<StoreXML.Field> result = new ArrayList<>();
        try {
            st = conn.prepareStatement(SELECT_TABLE);
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(new StoreXML.Field(rs.getInt("field")));
            }
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
        if (this.conn == null) {
            connect();
        }
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            rs = dbm.getTables(null, null, "entry", null);
            if (rs.next()) {
                checkRecords();
            } else {
                this.st = conn.prepareStatement(CREATE_TABLE);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Check the table for records.
     * If there are records in the table, all records are deleted.
     */
    private void checkRecords() {
        if (this.conn == null) {
            connect();
        }
        try {
            this.st = conn.prepareStatement(SELECT_ELEMENT);
            this.rs = this.st.executeQuery();
            if (this.rs.next()) {
                this.st = conn.prepareStatement(DELETE_TABLE);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Close PreparedStatement and Connection.
     *
     * @throws Exception - Exception.
     */
    @Override
    public void close() throws Exception {
        this.conn.commit();
        this.conn.setAutoCommit(true);
        if (this.rs != null) {
            this.rs.close();
        }
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
