package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.connection.ConnectionPool;
import by.kukshinov.hotel.connection.ProxyConnection;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.Dao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ProxyConnection connection;

    DaoHelper() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }


    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public ApplicationDao createApplicationDao() {
        return new ApplicationDaoImpl(connection);
    }

    public RoomDao createRoomDao() {
        return new RoomDaoImpl(connection);
    }

    public void close() throws DaoException {
        connection.close();
    }
}
