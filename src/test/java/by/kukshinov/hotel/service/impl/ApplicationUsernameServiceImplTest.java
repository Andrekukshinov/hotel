package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationUsernameDto;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.ApplicationUsernameService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ApplicationUsernameServiceImplTest {
    private static final String PASS = "password";
    private static final String LOGIN = "login";

    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final BigDecimal PRICE = new BigDecimal("505");
    private static final String CAPACITY_STRING = "1";

    private static final User FIRST_USER = new User(USER_ID, LOGIN, PASS, false, Role.USER);

    private static final Application FIRST_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application SECOND_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.STANDARD, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final List<Application> APPLICATIONS = Arrays.asList(FIRST_APPLICATION, SECOND_APPLICATION);
    private static final int NUMBER = 1;

    private DaoHelperFactory helperFactory;
    private ApplicationDao applicationDao;
    private UserDao userDao;
    private DaoHelper daoHelper;


    @BeforeMethod
    public void mockDaoAndHelperAndCreationBehaviour() {
        helperFactory = Mockito.mock(DaoHelperFactory.class);
        daoHelper = Mockito.mock(DaoHelper.class);
        applicationDao = Mockito.mock(ApplicationDao.class);
        userDao = Mockito.mock(UserDao.class);

        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(helperFactory.createDaoHelper()).thenReturn(daoHelper);
    }

    @Test
    public void testFindRangeShouldReturnListOfAppUsernameDtoObjects() throws DaoException, ServiceException {
        ApplicationUsernameService applicationUsernameService = new ApplicationUsernameServiceImpl(helperFactory);
        when(applicationDao.findRangeApplications(anyInt(), anyInt())).thenReturn(APPLICATIONS);
        when(userDao.findById(anyLong())).thenReturn(Optional.of(FIRST_USER));
        String login = FIRST_USER.getLogin();
        List<ApplicationUsernameDto> expected = new ArrayList<>();
        expected.add(new ApplicationUsernameDto(FIRST_APPLICATION, login));
        expected.add(new ApplicationUsernameDto(SECOND_APPLICATION, login));

        List<ApplicationUsernameDto> actual = applicationUsernameService.findRange(NUMBER, NUMBER);

        Assert.assertEquals(actual, expected);

    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindRangeShouldThrowServiceException() throws DaoException, ServiceException {
        ApplicationUsernameService applicationUsernameService = new ApplicationUsernameServiceImpl(helperFactory);
        when(applicationDao.findRangeApplications(anyInt(), anyInt())).thenReturn(APPLICATIONS);
        when(userDao.findById(anyLong())).thenReturn(Optional.empty());

        applicationUsernameService.findRange(NUMBER, NUMBER);
    }
}
