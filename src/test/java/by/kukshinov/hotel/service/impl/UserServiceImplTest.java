package by.kukshinov.hotel.service.impl;


import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static final String PASS = "password";
    private static final String LOGIN = "login";

    private static final long USER_ID = 1l;
    private static final User FIRST = new User(USER_ID, LOGIN, false, Role.USER);
    private static final User SECOND = new User(2l, LOGIN, false, Role.USER);
    private static final User THIRD = new User(3l, LOGIN, false, Role.USER);
    private static final User FOURTH = new User(4l, LOGIN, false, Role.USER);
    private static final User FIFTH = new User(5l, LOGIN, false, Role.USER);
    private static final User SIXTH = new User(6l, LOGIN, false, Role.USER);
    private static final User SEVENTH = new User(7l, LOGIN, false, Role.USER);
    private static final List<User> USERS = Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH);
    private static final int START_FROM = 1;
    private static final int FINISH_WITH = 7;
    private static final String EMPTY = "";

    private DaoHelperFactory helperFactory;
    private UserDao dao;

    @BeforeMethod
    public void mockDaoAndHelperAndCreationBehaviour() {
        helperFactory = mock(DaoHelperFactory.class);
        DaoHelper helper = mock(DaoHelper.class);
        dao = Mockito.mock(UserDao.class);

        when(helperFactory.createDaoHelper()).thenReturn(helper);
        when(helper.createUserDao()).thenReturn(dao);
    }

    @Test
    public void testGetRangeUsersShouldReturnListOfUsers() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        when(dao.findRangeUsers(anyInt(), anyInt())).thenReturn(USERS);
        //when
        List<User> actual = userService.getRangeUsers(START_FROM, FINISH_WITH);
        //then
        Assert.assertEquals(actual, USERS);
    }

    @Test
    public void testFindByCredentialsShouldReturnUserWithSpecifiedCredentials() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        Optional<User> expected = Optional.of(FIRST);
        when(dao.findByCredentials(anyString(), anyString())).thenReturn(expected);
        //when
        Optional<User> actual = userService.findByCredentials(LOGIN, PASS);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdShouldReturnUser() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        Optional<User> expected = Optional.of(FIRST);
        when(dao.findById(USER_ID)).thenReturn(expected);
        //when
        Optional<User> actual = userService.findById(USER_ID);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testChangeUserStatusShouldUpdateUserActivity() throws DaoException, ServiceException {
        //given
        User actual = new User(USER_ID, LOGIN, false, Role.USER);
        User expected = new User(USER_ID, LOGIN, true, Role.USER);
        UserService userService = new UserServiceImpl(helperFactory);
        doNothing().when(dao).save(actual);
        //when
        userService.switchUserStatus(actual);
        //then
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testGetUsersAmountShouldReturnUsersAmount() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        Integer expected = 7;
        when(dao.getAllUsersAmount()).thenReturn(USERS.size());
        //when
        Integer actual = userService.getUsersAmount();
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindByCredentialsShouldReturnOptionalEmptyWhenInvalidData() throws ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        //when
        Optional<User> actual = userService.findByCredentials(null, null);
        //then
        Assert.assertEquals(actual, Optional.empty());
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testUpdateUserShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        doThrow(new DaoException(EMPTY)).when(dao).save(any());
        //when
        userService.updateUser(FIFTH);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testChangeUserStatusShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        doThrow(new DaoException(EMPTY)).when(dao).save(any());
        //when
        userService.switchUserStatus(FIFTH);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindByCredentialsShouldThrowException() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        when(dao.findByCredentials(anyString(), anyString())).thenThrow(DaoException.class);
        //when
        userService.findByCredentials(LOGIN, PASS);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindByIdShouldShouldThrowException() throws DaoException, ServiceException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        when(dao.findById(USER_ID)).thenThrow(DaoException.class);
        //when
        userService.findById(USER_ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testGetRangeUsersShouldThrowServiceExceptionWhenDaoExceptionIsThrown() throws ServiceException, DaoException {
        //given
        UserService userService = new UserServiceImpl(helperFactory);
        when(dao.findRangeUsers(anyInt(), anyInt())).thenThrow(DaoException.class);
        //when
        userService.getRangeUsers(START_FROM, FINISH_WITH);
    }


}
