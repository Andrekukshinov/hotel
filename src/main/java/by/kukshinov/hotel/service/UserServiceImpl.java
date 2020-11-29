package by.kukshinov.hotel.service;

import by.kukshinov.hotel.dao.*;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoHelperFactory helperFactory;

    public UserServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

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
        try (UserDaoHelper daoHelper = ((UserDaoHelper)helperFactory.createHelper("user"))){
            UserDao userDao = daoHelper.createDao();
            return userDao.findByCredentials(login, pass);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
