package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Seat;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.07.2019.
 */
public class DBHalls {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBHalls INSTANCE = new DBHalls();
    private static final Logger LOGGER = LogManager.getLogger(DBHalls.class.getName());
    private DBAccounts dbAccounts = DBAccounts.getInstance();

    private static final String CREATE_TABLE = "CREATE TABLE halls(id SERIAL PRIMARY KEY, row INT NOT NULL, seat INT NOT NULL, status CHARACTER VARYING(15) NOT NULL, id_account INTEGER REFERENCES accounts(id));";

    private static boolean createTable = false;

    public static DBHalls getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private DBHalls() {
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
     * Get all places.
     *
     * @return - List of places.
     */
    public List<Seat> getPlaces() {
        checkTable();
        ArrayList<Seat> seats = new ArrayList<>();
        Seat seat;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM halls");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                seat = new Seat(rs.getInt("id"), rs.getInt("row"), rs.getInt("seat"), rs.getString("status"), rs.getInt("id_account"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to get all the elements from Database.");
        }
        return seats;
    }

    /**
     * Reserve seat in the database.
     *
     * @param seat - seat.
     */
    public void reserve(Seat seat) {
        checkTable();
        String query = null;
        boolean result = false;
        int step = 1;
        if (seat.getStatus().equals("reserve")) {
            query = "UPDATE halls SET status= ? WHERE row = ? AND seat = ?";
        } else if (seat.getStatus().equals("sold")) {
            query = "UPDATE halls SET status= ?, id_account = ? WHERE row = ? AND seat = ?";
        }
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(step++, seat.getStatus());
            if (seat.getStatus().equals("sold")) {
                st.setInt(step++, seat.getIdUser());
            }
            st.setInt(step++, seat.getRow());
            st.setInt(step++, seat.getSeat());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("Failed to reserve seat. Row = {}, Seat = {}.", seat.getRow(), seat.getSeat());
        }
    }

    /**
     * Get seat.
     *
     * @param id - id of seat.
     * @return - seat.
     */
    public Seat getSeat(int id) {
        checkTable();
        Seat seat = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try (Connection conn = SOURCE.getConnection()) {
            st = conn.prepareStatement("SELECT * FROM halls WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                seat = new Seat(rs.getInt("id"), rs.getInt("row"), rs.getInt("seat"), rs.getString("status"), rs.getInt("id_account"));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to get Seat. Id = {}.", id);
        } finally {
            assert st != null;
            try {
                st.close();
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return seat;
    }

    /**
     * Delete status reserve from database.
     */
    public void deleteReserve() {
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("UPDATE halls SET status= ? WHERE status = ?")) {
            st.setString(1, "free");
            st.setString(2, "reserve");
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to delete status reserve.");
        }
    }

    public void clearAll() {
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("UPDATE halls SET status= ?, id_account = null")) {
            st.setString(1, "free");
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to clearAll.");
        }
    }

    /**
     * Check table existence.
     */
    private void checkTable() {
        if (!createTable) {
            createTable();
            createTable = true;
        }
    }

    /**
     * Create a table.
     */
    private void createTable() {
        dbAccounts.checkTable();
        try (Connection conn = SOURCE.getConnection()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "halls", null);
            if (!rs.next()) {
                PreparedStatement st1 = conn.prepareStatement(DBHalls.CREATE_TABLE);
                st1.executeUpdate();
                fillTable();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to create table.");
        }
    }

    private void fillTable() {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO halls(row, seat, status) VALUES(?, ?, ?)")) {
            for (int row = 1; row < 4; row++) {
                for (int seat = 1; seat <= 3; seat++) {
                    st.setInt(1, row);
                    st.setInt(2, seat);
                    st.setString(3, "free");
                    st.executeUpdate();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to fill table.");
        }
    }
}
