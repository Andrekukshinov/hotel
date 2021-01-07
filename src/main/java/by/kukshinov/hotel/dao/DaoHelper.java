package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.connection.ConnectionPool;
import by.kukshinov.hotel.connection.ProxyConnection;
import by.kukshinov.hotel.dao.api.*;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;


public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

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

    private void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            rollback();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
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

    public ApplicationRoomDao createApplicationRoomDao() {
        return new ApplicationRoomDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.close();
    }
}
