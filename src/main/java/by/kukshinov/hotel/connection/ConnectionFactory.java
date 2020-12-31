package by.kukshinov.hotel.connection;

import by.kukshinov.hotel.exceptions.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionFactory {
    private static final String CONNECTION_PROPERTIES = "connection.properties";
    private static final String URL = "url";
    private final Properties prop;

    public ConnectionFactory() throws DaoException {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONNECTION_PROPERTIES)) {
            prop = new Properties();
            prop.load(in);
        } catch (IOException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public Connection createConnection() throws DaoException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = prop.getProperty(URL);
            return DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
