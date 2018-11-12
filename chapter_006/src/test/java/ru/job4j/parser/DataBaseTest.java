package ru.job4j.parser;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.*;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ZubovVP on 10.11.2018
 * zubovvp@yadndex.ru
 */
public class DataBaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseTest.class);
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    private static final String DROP_TABLE = "DROP TABLE offers;";
    private static final String SELECT = "SELECT * FROM offers WHERE url = ?";


    /**
     * Connect to database.
     */
    private void connect() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("offers.properties")) {
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
     * Close Connection, PreparedStatement, ResultSet if will not gonna close.
     *
     * @throws SQLException - exception.
     */
    private void finish() throws SQLException {
        if (this.rs != null) {
            this.rs.close();
        }
        if (this.st != null) {
            this.st.close();
        }
        if (this.conn != null) {
            try {
                this.conn.rollback();
                this.conn.setAutoCommit(true);
                this.conn.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }


    @Test
    public void checkTableIfTableNotCreatedToCreateTable() {
        DatabaseMetaData dbm;
        try {
            connect();
            dbm = this.conn.getMetaData();
            rs = dbm.getTables(null, null, "offers", null);
            if (rs.next()) {
                this.st = conn.prepareStatement(DROP_TABLE);
                st.executeUpdate();
            }
            rs = dbm.getTables(null, null, "offers", null);
            assertFalse(rs.next());
            rs.close();
            finish();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        try (DataBase db = new DataBase()) {
            db.checkTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connect();
            dbm = this.conn.getMetaData();
            rs = dbm.getTables(null, null, "offers", null);
            assertTrue(rs.next());
            rs.close();
            finish();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void addElementInTheTableAndCheckElementsInTheTable() {
        List<Offer> testList = new ArrayList<>(Arrays.asList(new Offer("TestName1", "url1", new Timestamp(Calendar.getInstance().getTimeInMillis())),
                new Offer("TestName2", "url2", new Timestamp(Calendar.getInstance().getTimeInMillis()))));

        try (DataBase db = new DataBase()) {
            db.checkTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Offer offer : testList) {
            connect();
            try {
                this.st = conn.prepareStatement(SELECT);
                this.st.setString(1, offer.getWay());
                this.rs = this.st.executeQuery();
                assertFalse(rs.next());
                this.rs.close();
                this.st.close();
                finish();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try (DataBase db = new DataBase()) {
            db.add(testList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Offer offer : testList) {
            connect();
            try {
                this.st = conn.prepareStatement(SELECT);
                this.st.setString(1, offer.getWay());
                this.rs = this.st.executeQuery();
                assertTrue(rs.next());
                PreparedStatement sta = conn.prepareStatement("DELETE FROM offers WHERE url = ?");
                sta.setString(1, offer.getWay());
                sta.executeUpdate();
                conn.commit();
                sta.close();
                this.rs.close();
                this.st.close();
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}