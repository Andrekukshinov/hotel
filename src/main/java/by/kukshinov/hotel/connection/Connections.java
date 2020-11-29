package by.kukshinov.hotel.connection;

import by.kukshinov.hotel.dao.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
//    private static Connection connection;

    public static Connection getConnection() throws DaoException {
        String url = "jdbc:mysql://localhost:3306/hotel_management?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(url, prop);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
