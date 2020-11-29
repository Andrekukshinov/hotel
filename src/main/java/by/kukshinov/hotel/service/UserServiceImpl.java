package by.kukshinov.hotel.service;

import by.kukshinov.hotel.connection.Connections;
import by.kukshinov.hotel.dao.DaoException;
import by.kukshinov.hotel.dao.UserDao;
import by.kukshinov.hotel.dao.UserDaoImpl;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.exceptions.UserNotFoundException;
import by.kukshinov.hotel.model.User;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserServiceImpl implements UserService {


    @Override
    public List<User> getAllUsers() {

        return Arrays.asList(
                new User(1, "admin", "password", false),
                new User(2, "Billy", "password", false),
                new User(3, "Harrington", "password", false),
                new User(4, "Van", "password", true),
                new User(5, "Darkholme", "password", false),
                new User(6, "Slave", "password", true),
                new User(7, "Smith", "password", true)
        );
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws ServiceException {
        try (Connection connection = Connections.getConnection()){
            UserDao userDao = new UserDaoImpl(connection);
            return userDao.findByCredentials(login, pass);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
