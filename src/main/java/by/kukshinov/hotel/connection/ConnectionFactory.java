//package by.kukshinov.hotel.connection;
//
//import by.kukshinov.hotel.exceptions.DaoException;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class ConnectionFactory {
//    private final Properties prop;
//
//    public ConnectionFactory() throws DaoException{
//        try {
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            prop = new Properties();
//            ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
//            prop.load(classLoader.getResourceAsStream("connection.properties"));
//        } catch (SQLException | IOException e) {
//            throw new DaoException(e.getMessage(), e);
//        }
//    }
//
//    // TODO: 04.12.2020 rework with properties
//    public Connection createConnection() throws DaoException {
//        try {
//            String url = "jdbc:mysql://localhost:3306/hotel_management?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
//
//            return DriverManager.getConnection(url, prop);
//        } catch (SQLException e) {
//            throw new DaoException(e.getMessage(), e);
//        }
//    }
//
//}
package by.kukshinov.hotel.connection;

import by.kukshinov.hotel.exceptions.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String CONNECTION_PROPERTIES = "connection.properties";
    private static final String URL = "url";
    private final Properties prop;

    public ConnectionFactory() throws DaoException{
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONNECTION_PROPERTIES)){
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            prop = new Properties();
            prop.load(in);
        } catch (SQLException | IOException e) {
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
