package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.connection.ConnectionPool;
import by.kukshinov.hotel.connection.ProxyConnection;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;


public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CLOSING_TRANSACTION_ERROR = "Closing transaction error";

    private final ProxyConnection connection;

    DaoHelper() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            endTransaction();
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            rollback();
        } finally {
            endTransaction();
        }
    }

    public void endTransaction() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(CLOSING_TRANSACTION_ERROR, e);
        }
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

    @Override
    public void close() {
        connection.close();
    }
}
