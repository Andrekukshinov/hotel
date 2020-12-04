package by.kukshinov.hotel.service;

import by.kukshinov.hotel.dao.*;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoHelperFactory helperFactory;

    public UserServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getRangeUsers(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findRangeUsers(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findByCredentials(login, pass);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            UserDao dao = daoHelper.createUserDao();
            dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
