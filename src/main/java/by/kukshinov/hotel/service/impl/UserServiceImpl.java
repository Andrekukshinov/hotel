package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.api.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoHelperFactory helperFactory;

    public UserServiceImpl(DaoHelperFactory helperFactory) {

        this.helperFactory = helperFactory;
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findByCredentials(login, pass);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            UserDao dao = daoHelper.createUserDao();
            dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void switchUserStatus(User user) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            UserDao dao = daoHelper.createUserDao();
            boolean isDisabled = user.getIsDisabled();
            user.setIsDisabled(!isDisabled);
            dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getUsersAmount() throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.getAllUsersAmount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getRangeUsers(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.findRangeUsers(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
