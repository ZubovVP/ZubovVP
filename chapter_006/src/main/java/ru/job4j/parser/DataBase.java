package ru.job4j.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZubovVP on 31.10.2018
 * zubovvp@yadndex.ru
 */
public class DataBase implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBase.class);
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    private static final String ADD_ELEMENT = "INSERT INTO offers(headline, url, create_date) VALUES(?, ?, ?)";
    private static final String CREATE_TABLE = "CREATE TABLE offers (id serial primary key, headline VARCHAR (100) NOT NULL, url VARCHAR (150) NOT NULL, create_date Timestamp );";
    private static final String SELECT = "SELECT * FROM offers WHERE url = ?";

    /**
     * Connect to database.
     */
    private void connect() {
        LOGGER.info("--------CONNECTION TO DATABASE--------");
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("offers.properties")) {
            Properties props = new Properties();
            props.load(in);
            Class.forName(props.getProperty("driver-class-name"));
            this.conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
            this.conn.setAutoCommit(false);
            LOGGER.info("--------CONNECTION TO DATABASE - COMPLETE--------");
        } catch (Exception e) {
            LOGGER.info("--------CONNECTION TO DATABASE - ERROR--------");
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Check table existence if not create table.
     */
    public boolean checkTable() {
        boolean newTable = false;
        if (this.conn == null) {
            connect();
        }
        try {
            LOGGER.info("--------CHECK TABLE--------");
            DatabaseMetaData dbm = this.conn.getMetaData();
            rs = dbm.getTables(null, null, "offers", null);
            if (!rs.next()) {
                LOGGER.info("--------CREATE TABLE--------");
                this.st = conn.prepareStatement(CREATE_TABLE);
                st.executeUpdate();
                st.close();
                newTable = true;
            }
            this.rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return newTable;
    }

    /**
     * Add offers in the table.
     *
     * @param offers - List of offers.
     */
    public void add(List<Offer> offers) {
        if (this.conn == null) {
            connect();
        }
        try {
            LOGGER.info("--------ADD NEW OFFERS--------");
            for (Offer offer : offers) {
                this.st = conn.prepareStatement(SELECT);
                this.st.setString(1, offer.getWay());
                this.rs = st.executeQuery();
                if (!rs.next()) {
                    this.st.close();
                    this.st = conn.prepareStatement(ADD_ELEMENT);
                    st.setString(1, offer.getName());
                    st.setString(2, offer.getWay());
                    st.setTimestamp(3, offer.getDate());
                    st.executeUpdate();
                }
                this.rs.close();
                this.st.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Close Connection, PreparedStatement, ResultSet if will not gonna close.
     *
     * @throws Exception - exception.
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
                LOGGER.info("--------CLOSE CONNECTION TO DATABASE--------");
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
