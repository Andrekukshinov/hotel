package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.connection.ConnectionPool;
import by.kukshinov.hotel.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


public class UserDaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    private Connection connection;

    public UserDaoHelper() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }


    public UserDao createDao() {
        return new UserDaoImpl(connection);
    }

    public void close() throws DaoException {
        try {
            connection.close();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
