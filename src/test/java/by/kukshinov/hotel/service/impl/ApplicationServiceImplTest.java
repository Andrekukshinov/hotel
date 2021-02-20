package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ApplicationServiceImplTest {
    private static final int NUMBER = 1;
    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final String CAPACITY_STRING = "1";
    private static final BigDecimal PRICE = new BigDecimal("505");
    private static final String WRONG_ROOM = "wrong room!";


    private static final Room AVAILABLE_ROOM = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), true, PRICE);

    private static final Application FIRST = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application SECOND = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.STANDARD, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application THIRD = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application FOURTH = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application FIFTH = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.SKY_WALKER, LocalDate.now(), LocalDate.now(), ApplicationStatus.IN_ORDER, null, null, USER_ID);
    private static final Application SIXTH = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.SKY_WALKER, LocalDate.now(), LocalDate.now(), ApplicationStatus.IN_ORDER, null, null, USER_ID);

    private static final List<Application> ALL_APPLICATIONS = Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH);
    private static final List<Application> APPROVED = Arrays.asList(FIRST, SECOND, THIRD, FOURTH);
    private static final List<Application> IN_ORDER_APPLICATIONS = Arrays.asList(SIXTH, FIFTH);
    private static final long ANOTHER_USER_ID = 2L;


    private DaoHelperFactory helperFactory;
    private ApplicationDao applicationDao;
    private RoomDao roomDao;
    private DaoHelper daoHelper;


    @BeforeMethod
    public void mockDaoAndHelperAndCreationBehaviour() {
        helperFactory = Mockito.mock(DaoHelperFactory.class);
        daoHelper = Mockito.mock(DaoHelper.class);
        applicationDao = Mockito.mock(ApplicationDao.class);
        roomDao = Mockito.mock(RoomDao.class);

        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
        when(daoHelper.createRoomDao()).thenReturn(roomDao);
        when(helperFactory.createDaoHelper()).thenReturn(daoHelper);
    }


    @Test
    public void testFindRangeUserBillsShouldReturnListOfApprovedApplications() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findUserBills(anyLong(), anyInt(), anyInt())).thenReturn(APPROVED);
        //when
        List<Application> actual = service.findRangeUserBills(USER_ID, NUMBER, NUMBER);
        //then
        Assert.assertEquals(actual, APPROVED);
    }

    @Test
    public void testFindRangeUserApplicationsShouldReturnListOfUserApplications() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findUserApplications(anyLong(), anyInt(), anyInt())).thenReturn(ALL_APPLICATIONS);
        //when
        List<Application> actual = service.findRangeUserApplications(USER_ID, NUMBER, NUMBER);
        //then
        Assert.assertEquals(actual, ALL_APPLICATIONS);
    }

    @Test
    public void testFindRangeOrderedEntitiesShouldReturnListApplications() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findAllOrderedApplications(anyInt(), anyInt())).thenReturn(IN_ORDER_APPLICATIONS);
        //when
        List<Application> actual = service.findRangeOrderedEntities(NUMBER, NUMBER);
        //then
        Assert.assertEquals(actual, IN_ORDER_APPLICATIONS);
    }


    @Test
    public void testFindInOrderApplicationsAmountShouldReturnInOrderApplicationsAmount() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        Integer expected = IN_ORDER_APPLICATIONS.size();
        when(applicationDao.getInOrderApplicationsAmount()).thenReturn(expected);
        //when
        Integer actual = service.findInOrderApplicationsAmount();
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserApplicationsAmountShouldReturnUserApplicationsAmount() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        Integer expected = ALL_APPLICATIONS.size();
        when(applicationDao.findUserApplicationsAmount(USER_ID)).thenReturn(expected);
        //when
        Integer actual = service.findUserApplicationsAmount(USER_ID);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindUserBillsAmountShouldReturnUserApprovedApplicationsAmount() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        Integer expected = APPROVED.size();
        when(applicationDao.findUserBillsAmount(USER_ID)).thenReturn(expected);
        //when
        Integer actual = service.findUserBillsAmount(USER_ID);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindAllApplicationsAmountShouldReturnAllApplicationsAmount() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        Integer expected = ALL_APPLICATIONS.size();
        when(applicationDao.getAllApplicationsAmount()).thenReturn(expected);
        //when
        Integer actual = service.findAllApplicationsAmount();
        //then
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testFindFutureArrivalInOrderApplicationByIdShouldReturnInOrderApplication() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        Application expected = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.SKY_WALKER, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(expected));
        //when
        Application actual = service.findFutureArrivalInOrderApplicationById(ID);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAdminDenyOrderedApplicationShouldDenyOrderedApplication() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        Application expected = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.DENIED, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));

        //when
        service.adminDenyOrderedApplication(ID);
        //then
        Assert.assertEquals(start, expected);
    }

    @Test
    public void testApproveApplicationShouldAddApprovedDataToAppWhenApplicationAndRoomIsFound() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        Application expected = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);

        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(AVAILABLE_ROOM));

        //when
        service.approveApplication(ID, ROOM_ID);
        //then
        Assert.assertEquals(start, expected);
    }

    @Test
    public void testUserRejectApprovedApplicationShouldDenyApprovedApplication() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
        Application expected = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.USER_REJECTED, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        //when
        service.userRejectApprovedApplication(ID, USER_ID);
        //then
        Assert.assertEquals(start, expected);
    }


    @Test
    public void testUserCancelOrderedApplicationShouldCancelOrderedApplication() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        Application expected = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.CANCELLED, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        //when
        service.userCancelOrderedApplication(ID, USER_ID);
        //then
        Assert.assertEquals(start, expected);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testSaveShouldThrowServiceException() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doThrow(DaoException.class).when(applicationDao).save(any());
        //when
        service.save(FIRST);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindFutureArrivalInOrderApplicationByIdShouldThrowServiceExceptionWhenStatusIsNotInOrder() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(FIRST));
        //when
        service.findFutureArrivalInOrderApplicationById(ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testAdminDenyOrderedApplicationShouldThrowServiceExceptionWhenStatusIsNotInOrder() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now(), ApplicationStatus.CANCELLED, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));

        //when
        service.adminDenyOrderedApplication(ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testAdminDenyOrderedApplicationShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());

        //when
        service.adminDenyOrderedApplication(ID);
    }



    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindFutureArrivalInOrderApplicationByIdShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());
        //when
        service.findFutureArrivalInOrderApplicationById(ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindFutureArrivalInOrderApplicationByIdShouldThrowServiceExceptionWhenArrivalDateHasPassed() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(FIFTH));
        //when
        service.findFutureArrivalInOrderApplicationById(ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testApproveApplicationShouldThrowServiceExceptionWhenArrivalDateHasPassed() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        doNothing().when(applicationDao).save(any());
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(AVAILABLE_ROOM));

        //when
        service.approveApplication(ID, ROOM_ID);

    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = WRONG_ROOM)//then
    public void testApproveApplicationShouldThrowServiceExceptionWhenRoomIsNotFound() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        doNothing().when(applicationDao).save(any());
        when(roomDao.findById(anyLong())).thenReturn(Optional.empty());

        //when
        service.approveApplication(ID, ROOM_ID);

    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = WRONG_ROOM)//then
    public void testApproveApplicationShouldThrowServiceExceptionWhenRoomIsNotAvailable() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, null, null, USER_ID);
        Room wrongRoom = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), false, PRICE);

        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        doNothing().when(applicationDao).save(any());
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(wrongRoom));

        //when
        service.approveApplication(ID, ROOM_ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testApproveApplicationShouldThrowServiceExceptionWhenApplicationIsNotInOrder() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, null, null, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        doNothing().when(applicationDao).save(any());
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(AVAILABLE_ROOM));

        //when
        service.approveApplication(ID, ROOM_ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testApproveApplicationShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(AVAILABLE_ROOM));

        //when
        service.approveApplication(ID, ROOM_ID);

    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testUserRejectApprovedApplicationShouldThrowServiceExceptionWhenArrivalDateIsBeforeToday() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        //when
        service.userRejectApprovedApplication(ID, USER_ID);

    }


    @Test(expectedExceptions = ServiceException.class)//then
    public void testUserCancelOrderedApplicationShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());
        //when
        service.userCancelOrderedApplication(ID, USER_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testUserCancelOrderedApplicationShouldThrowServiceExceptionWhenApplicationIsNotInOrder() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now(), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        //when
        service.userCancelOrderedApplication(ID, USER_ID);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testUserRejectApprovedApplicationShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException, DaoException {
        //given
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());
        //when
        service.userRejectApprovedApplication(ID, USER_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testUserRejectApprovedApplicationShouldThrowServiceExceptionWhenStateIsNotApproved() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(2), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, PRICE, ROOM_ID, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        //when
        service.userRejectApprovedApplication(ID, USER_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testUserRejectApprovedApplicationShouldThrowServiceExceptionWhenApplicationIsNotUserApplication() throws ServiceException, DaoException {
        //given
        Application start = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.LUX, LocalDate.now().plusDays(2), LocalDate.now().plusDays(1), ApplicationStatus.IN_ORDER, PRICE, ROOM_ID, USER_ID);
        ApplicationService service = new ApplicationServiceImpl(helperFactory);
        doNothing().when(applicationDao).save(any());
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(start));
        //when
        service.userRejectApprovedApplication(ID, ANOTHER_USER_ID);
    }
}
