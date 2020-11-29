package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.connection.Connections;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;

public class UserDaoHelper implements DaoHelper<User, UserDao> {
    private Connection connection;

    public UserDaoHelper() {
        try {
            connection = Connections.getConnection();
        } catch (DaoException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public UserDao createDao() {
        return new UserDaoImpl(connection);
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
