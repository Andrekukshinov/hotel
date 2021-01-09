package by.kukshinov.hotel.service;


import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.UserDaoImpl;
import by.kukshinov.hotel.dao.api.Dao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.api.UserService;
import by.kukshinov.hotel.service.impl.UserServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static final String PASS = "password";
    private static final String LOGIN = "login";

    private static final User FIRST = new User(1l, LOGIN, PASS, false, Role.USER);
    private static final User SECOND = new User(2l, LOGIN, PASS, false, Role.USER);
    private static final User THIRD = new User(3l, LOGIN, PASS, false, Role.USER);
    private static final User FOURTH = new User(4l, LOGIN, PASS, false, Role.USER);
    private static final User FIFTH = new User(5l, LOGIN, PASS, false, Role.USER);
    private static final User SIXTH = new User(6l, LOGIN, PASS, false, Role.USER);
    private static final User SEVENTH = new User(7l, LOGIN, PASS, false, Role.USER);
    private static final List<User> USERS = Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH);
    private static final int START_FROM = 1;
    private static final int FINISH_WITH = 7;


    @Test
    public void testGetRangeUsersShouldReturnListOfUsers() throws DaoException, ServiceException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        Dao<User> dao = Mockito.mock(UserDaoImpl.class);
        UserService userService = new UserServiceImpl(helperFactory);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn((UserDao) dao);
        when(((UserDao) dao).findRangeUsers(anyInt(), anyInt())).thenReturn(USERS);

        List<User> actual = userService.getRangeUsers(START_FROM, FINISH_WITH);

        Assert.assertEquals(actual, USERS);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testGetRangeUsersShouldThrowServiceException() throws ServiceException, DaoException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        Dao<User> dao = Mockito.mock(UserDaoImpl.class);
        UserService userService = new UserServiceImpl(helperFactory);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn((UserDao) dao);
        when(((UserDao) dao).findRangeUsers(anyInt(), anyInt())).thenThrow(DaoException.class);

        userService.getRangeUsers(START_FROM, FINISH_WITH);
    }

    @Test
    public void testGetRangeUsersShouldReturnEmptyList() throws ServiceException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        Dao<User> dao = Mockito.mock(UserDaoImpl.class);
        UserService userService = new UserServiceImpl(helperFactory);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn((UserDao) dao);

        List<User> actual = userService.getRangeUsers(START_FROM, FINISH_WITH);

        Assert.assertEquals(actual, Collections.emptyList());
    }


    @Test
    public void testFindByCredentialsShouldReturnUserWithSpecifiedCredentials() throws DaoException, ServiceException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = Mockito.mock(UserDao.class);
        UserService userService = new UserServiceImpl(helperFactory);
        Optional<User> expected = Optional.of(FIRST);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn(dao);
        when(dao.findByCredentials(anyString(), anyString())).thenReturn(expected);

        Optional<User> actual = userService.findByCredentials(LOGIN, PASS);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByCredentialsShouldReturnOptionalEmpty() throws ServiceException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = Mockito.mock(UserDao.class);
        UserService userService = new UserServiceImpl(helperFactory);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn(dao);

        Optional<User> actual = userService.findByCredentials(LOGIN, PASS);

        Assert.assertEquals(actual, Optional.empty());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByCredentialsShouldThrowException() throws DaoException, ServiceException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = Mockito.mock(UserDao.class);
        UserService userService = new UserServiceImpl(helperFactory);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn(dao);
        when(dao.findByCredentials(anyString(), anyString())).thenThrow(DaoException.class);

        userService.findByCredentials(LOGIN, PASS);

    }


    @Test(expectedExceptions = ServiceException.class)
    public void testUpdateUserShouldThrowServiceException() throws DaoException, ServiceException {
        DaoHelperFactory helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        UserDao dao = Mockito.mock(UserDao.class);
        UserService userService = new UserServiceImpl(helperFactory);
        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn(dao);
        doThrow(new DaoException("")).when(dao).save(any());

        userService.updateUser(FIFTH);
    }


}
