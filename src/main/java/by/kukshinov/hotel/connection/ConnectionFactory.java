package by.kukshinov.hotel.connection;

import by.kukshinov.hotel.exceptions.DaoException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final Properties prop;

    public ConnectionFactory() throws DaoException{
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            prop = new Properties();
            ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
            prop.load(classLoader.getResourceAsStream("connection.properties"));
            prop.put("user", "root");
            prop.put("password", "root");
            prop.put("autoReconnect", "true");
            prop.put("characterEncoding", "UTF-8");
            prop.put("useUnicode", "true");
        } catch (SQLException | IOException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    // TODO: 04.12.2020 rework with properties
    public Connection createConnection() throws DaoException {
        try {
            String url = "jdbc:mysql://localhost:3306/hotel_management?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

            return DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
