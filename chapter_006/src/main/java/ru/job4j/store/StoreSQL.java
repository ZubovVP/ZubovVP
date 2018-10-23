package ru.job4j.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    static final long TIME_START = System.currentTimeMillis();


    /**
     * Constructor.
     *
     * @param config - settings for connection.
     */
    public StoreSQL(Config config) {
        this.conn = config.getConn();
    }

    /**
     * Create recods in the db.
     *
     * @param n - quantity of generated records.
     */
    public void generate(int n) {
        this.st = null;
        this.rs = null;
        checkTable();
        for (int x = 1; x <= n; x++) {
            try {
                this.conn.setAutoCommit(false);
                this.st = conn.prepareStatement(ADD_ELEMENT);
                this.st.setInt(1, x);
                this.st.executeUpdate();
                this.conn.setAutoCommit(true);
            } catch (Exception e) {
                try {
                    this.conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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
        List<StoreXML.Field> result = new ArrayList<>();
        st = null;
        rs = null;
        try {
            st = conn.prepareStatement(SELECT_TABLE);
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(new StoreXML.Field(rs.getInt("field")));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Check table existence.
     * If the table is not created, create a table.
     */
    private void checkTable() {
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
            e.printStackTrace();
        }
    }

    /**
     * Check the table for records.
     * If there are records in the table, all records are deleted.
     */
    private void checkRecords() {
        this.st = null;
        this.rs = null;
        try {
            this.st = conn.prepareStatement(SELECT_ELEMENT);
            this.rs = this.st.executeQuery();
            if (this.rs.next()) {
                this.st = conn.prepareStatement(String.format("%s %s", "DROP TABLE entry;", "CREATE TABLE entry (field INT NOT NULL);"));
                st.executeUpdate();
            }
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
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
