package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Jobs;

import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 19.10.2019
 */
public class DBService {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBService INSTANCE = new DBService();
    private static final Logger LOGGER = LogManager.getLogger(DBService.class.getName());
    private static final String CREATE_TABLE_TYPE_OPERATION = "CREATE TABLE type_operation (id SERIAL PRIMARY KEY, name_type VARCHAR(30) NOT NULL);";
    private static final String CREATE_OPERATIONS = "CREATE TABLE operations (id SERIAL PRIMARY KEY, device VARCHAR(30) NOT NULL, user_name VARCHAR(30) NOT NULL, id_type INT references type_operation(id) NOT NULL, amount INT NOT NULL, date DATE NOT NULL);";
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("dd.MM.yyyy HH:mm");


    private static boolean createTable1 = false;
    private static boolean createTable2 = false;


    public static DBService getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    public DBService() {
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
     * Add job in the DB.
     *
     * @param job - job.
     */
    public void add(Jobs.Job job) {
        checkTable();
        int idType = 0;
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st1 = conn.prepareStatement("SELECT id FROM type_operation WHERE name_type = ?");
             PreparedStatement st2 = conn.prepareStatement("INSERT INTO operations(device, user_name, id_type, amount,  date ) VALUES(?, ?, ? , ?, ?)")) {
            st1.setString(1, job.getType());
            try (ResultSet rs = st1.executeQuery()) {
                while (rs.next()) {
                    idType = rs.getInt("id");
                }
            }
            st2.setString(1, job.getDevice());
            st2.setString(2, job.getUser());
            st2.setInt(3, idType);
            st2.setInt(4, job.getAmount());
            st2.setTimestamp(5, convertDate(job.getDate()));
            st2.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to add element in the DataBase. User - name = {}, type = {}, date = {}.", job.getUser(), job.getType(), job.getDate());
        }
    }

    /**
     * Clear all the elements from DB.
     *
     * @return - result.
     */
    public boolean clearAll() {
        boolean result = false;
        checkTable();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("DELETE FROM operations;")) {
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("Failed to clear all the elements from Database.");
        }
        return result;
    }


    /**
     * Find all the elements when userName = parameter.
     *
     * @param userName - userName.
     * @return - List of elements.
     */
    public List<Jobs.Job> findByUser(String userName) {
        checkTable();
        List<Jobs.Job> resultByUser = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT o.id, o.device, o.user_name, t.name_type, o.amount, o.date FROM operations AS o INNER JOIN type_operation AS t ON o.id_type = t.id WHERE user_name = ?;")) {
            st.setString(1, userName);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultByUser.add(new Jobs.Job(rs.getInt("id"), rs.getString("device"), rs.getString("user_name"), rs.getString("name_type"), rs.getInt("amount"), rs.getString("date")));
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to find by userName. userName = {}.", userName);
            }
        } catch (SQLException e) {
            LOGGER.error("Error execute the operation findByUser userName = {}.", userName);
        }
        return resultByUser;
    }

    /**
     * Find all the elements when device = parameter.
     *
     * @param device - device.
     * @return - List of elements.
     */
    public List<Jobs.Job> findByDevice(String device) {
        checkTable();
        List<Jobs.Job> resultByDevice = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT o.id, o.device, o.user_name, t.name_type, o.amount, o.date FROM operations AS o INNER JOIN type_operation AS t ON o.id_type = t.id WHERE device = ?;")) {
            st.setString(1, device);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultByDevice.add(new Jobs.Job(rs.getInt("id"), rs.getString("device"), rs.getString("user_name"), rs.getString("name_type"), rs.getInt("amount"), rs.getString("date")));
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to find by device. device = {}.", device);
            }
        } catch (SQLException e) {
            LOGGER.error("Error execute the operation findByDevice device = {}.", device);
        }
        return resultByDevice;
    }

    /**
     * Find all the elements when type = parameter.
     *
     * @param type - type of operation.
     * @return - List of elements.
     */
    public List<Jobs.Job> findByType(String type) {
        checkTable();
        List<Jobs.Job> resultByType = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT o.id, o.device, o.user_name, t.name_type, o.amount, o.date FROM operations AS o INNER JOIN type_operation AS t ON o.id_type = t.id WHERE name_type = ?;")) {
            st.setString(1, type);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultByType.add(new Jobs.Job(rs.getInt("id"), rs.getString("device"), rs.getString("user_name"), rs.getString("name_type"), rs.getInt("amount"), rs.getString("date")));
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to find by type. type = {}.", type);
            }
        } catch (SQLException e) {
            LOGGER.error("Error execute the operation findByType type = {}.", type);
        }
        return resultByType;
    }

    /**
     * Get all elements from DB.
     *
     * @return - List of elements.
     */
    public List<Jobs.Job> getAllJob() {
        checkTable();
        List<Jobs.Job> resultByType = new ArrayList<>();
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT o.id, o.device, o.user_name, t.name_type, o.amount, o.date FROM operations AS o INNER JOIN type_operation AS t ON o.id_type = t.id;")) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    resultByType.add(new Jobs.Job(rs.getInt("id"), rs.getString("device"), rs.getString("user_name"), rs.getString("name_type"), rs.getInt("amount"), FORMAT_TIME.format(rs.getTimestamp("date"))));
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to find all elements");
            }
        } catch (SQLException e) {
            LOGGER.error("Error execute the operation getAllJob");
        }
        return resultByType;
    }

    /**
     * Close connection.
     */
    public void close() {
        try {
            SOURCE.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to close BasicDataSource");
        }
    }

    private void fillTable() {
        try (Connection conn = SOURCE.getConnection();
             PreparedStatement st1 = conn.prepareStatement("INSERT INTO type_operation(name_type) VALUES('scan');");
             PreparedStatement st2 = conn.prepareStatement("INSERT INTO type_operation(name_type) VALUES('print');");
             PreparedStatement st3 = conn.prepareStatement("INSERT INTO type_operation(name_type) VALUES('fax');");
             PreparedStatement st4 = conn.prepareStatement("INSERT INTO type_operation(name_type) VALUES('copy');")) {
            st1.executeUpdate();
            st2.executeUpdate();
            st3.executeUpdate();
            st4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Check table existence.
     */
    private void checkTable() {
        if (!createTable1) {
            createTable(CREATE_TABLE_TYPE_OPERATION, "type_operation");
            createTable1 = true;
        }

        if (!createTable2) {
            createTable(CREATE_OPERATIONS, "operations");
            createTable2 = true;
        }
    }

    /**
     * Create a table.
     *
     * @param textCreate - SQL command.
     */
    private void createTable(String textCreate, String tableName) {
        try (Connection conn = SOURCE.getConnection()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, tableName, null);
            if (!rs.next()) {
                PreparedStatement st = conn.prepareStatement(textCreate);
                st.executeUpdate();
            }
            if (tableName.equals("type_operation")) {
                fillTable();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private Timestamp convertDate(String date) {
        java.util.Date date1 = null;
        try {
            date1 = FORMAT_TIME.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timeStampDate = new Timestamp(date1.getTime());
        return timeStampDate;
    }

}