package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.connection.ConnectionPool;
import by.kukshinov.hotel.connection.ProxyConnection;
import by.kukshinov.hotel.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    private ProxyConnection connection;

    public DaoHelper() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }


    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public void close() throws DaoException {
        connection.close();
    }
}
