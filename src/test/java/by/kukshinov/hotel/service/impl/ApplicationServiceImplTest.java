package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ApplicationServiceImplTest {
    private static final int NUMBER = 1;
    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final Application FIRST = new Application(ID, new Byte("3"), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, new BigDecimal("44"), ID, USER_ID);
    private static final Application SECOND = new Application(ID, new Byte("4"), ApartmentType.STANDARD, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, new BigDecimal("44"), ID, USER_ID);
    private static final Application THIRD = new Application(ID, new Byte("6"), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, new BigDecimal("44"), ID, USER_ID);
    private static final Application FOURTH = new Application(ID, new Byte("66"), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, new BigDecimal("44"), ID, USER_ID);
    private static final Application FIFTH = new Application(ID, new Byte("32"), ApartmentType.SKY_WALKER, LocalDate.now(), LocalDate.now(), ApplicationStatus.IN_ORDER, new BigDecimal("44"), null, null);
    private static final Application SIXTH = new Application(ID, new Byte("32"), ApartmentType.SKY_WALKER, LocalDate.now(), LocalDate.now(), ApplicationStatus.IN_ORDER, new BigDecimal("44"), null, null);

    private static final List<Application> APPROVED = Arrays.asList(FIRST, SECOND, THIRD, FOURTH);

    private static final List<Application> IN_ORDER_APPLICATIONS = Arrays.asList(SIXTH, FIFTH);

    private static final List<Application> ALL_APPLICATIONS = Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH);


    private DaoHelperFactory helperFactory;
    private ApplicationDao applicationDao;


    @BeforeMethod
    public void mockDaoAndHelperAndCreationBehaviour() {
        helperFactory = Mockito.mock(DaoHelperFactory.class);
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        applicationDao = Mockito.mock(ApplicationDao.class);

        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
        when(helperFactory.createDaoHelper()).thenReturn(daoHelper);
    }


    @Test
    public void testFindRangeUserBillsShouldReturnListOfApprovedApplications() throws ServiceException, DaoException {
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findUserBills(anyLong(), anyInt(), anyInt())).thenReturn(APPROVED);

        List<Application> actual = service.findRangeUserBills(USER_ID, NUMBER, NUMBER);

        Assert.assertEquals(actual, APPROVED);
    }

    @Test
    public void testFindRangeUserApplicationsShouldReturnListOfUserApplications() throws ServiceException, DaoException {
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findUserApplications(anyLong(), anyInt(), anyInt())).thenReturn(ALL_APPLICATIONS);

        List<Application> actual = service.findRangeUserApplications(USER_ID, NUMBER, NUMBER);

        Assert.assertEquals(actual, ALL_APPLICATIONS);
    }

    @Test
    public void testFindRangeOrderedEntitiesShouldReturnListApplications() throws ServiceException, DaoException {
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findAllOrderedApplications(anyInt(), anyInt())).thenReturn(IN_ORDER_APPLICATIONS);

        List<Application> actual = service.findRangeOrderedEntities(NUMBER, NUMBER);

        Assert.assertEquals(actual, IN_ORDER_APPLICATIONS);
    }
//
//    List<Application> findRangeUserApplications(long userId, int startFrom, int finishWith) throws ServiceException;
//
//    List<Application> findRangeUserBills(long userId, int startFrom, int finishWith) throws ServiceException;
//
//    List<Application> findRangeOrderedEntities(int startFrom, int finishWith) throws ServiceException;


//    void approveApplication(Application application, Long roomId) throws ServiceException;
//
//    Optional<Application> findApprovedUserApplicationById(Long id, Long useId) throws ServiceException;
//
//    Optional<Application> findInOrderApplicationById(Long id) throws ServiceException;
//
//    Optional<Application> findInOrderUserApplicationById(Long appId, Long userId) throws ServiceException;
//
//    int findInOrderApplicationsAmount() throws ServiceException;
//
//    int findUserApplicationsAmount(long id) throws ServiceException;
//
//    int findUserBillsAmount(long id) throws ServiceException;
//
//    int findAllApplicationsAmount() throws ServiceException;
//
//    void rejectApplication(Application application) throws ServiceException;
//
//    void save(Application application) throws ServiceException;
//
//    void userRejectApplication(Application application) throws ServiceException;
//
//    void userCancelApplication(Application application) throws ServiceException;
}
