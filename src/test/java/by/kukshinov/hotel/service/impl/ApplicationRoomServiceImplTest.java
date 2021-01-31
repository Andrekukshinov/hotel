package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ApplicationRoomServiceImplTest {
    private static final long ROOM_ID = 1L;
    private static final Room ROOM = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("505"));

    private static final long ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Application APPROVED_APPLICATION = new Application(ID, new Byte("4"), ApartmentType.BUSINESS, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, new BigDecimal("505"), ROOM_ID, USER_ID);
    private static final Application DENIED_APPLICATION = new Application(ID, new Byte("4"), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.DENIED, null, null, 2L);


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
    public void testFindApplicationRoomShouldReturnApprovedApplicationWithRoom() throws DaoException, ServiceException {
        ApplicationRoomService applicationUsernameService = new ApplicationRoomServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(APPROVED_APPLICATION));
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));

        ApplicationRoom expected = new ApplicationRoom(APPROVED_APPLICATION, ROOM);

        ApplicationRoom actual = applicationUsernameService.findByApplicationId(ID);

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testFindUserBillByApplicationIdShouldReturnApprovedApplicationWithRoom() throws DaoException, ServiceException {
        ApplicationRoomService applicationUsernameService = new ApplicationRoomServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(APPROVED_APPLICATION));
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));

        ApplicationRoom expected = new ApplicationRoom(APPROVED_APPLICATION, ROOM);

        ApplicationRoom actual = applicationUsernameService.findUserBillByApplicationId(ID, USER_ID);

        Assert.assertEquals(actual, expected);

    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindApplicationRoomShouldThrowServiceExceptionWhenApplicationIsNotFound() throws DaoException, ServiceException {
        ApplicationRoomService applicationUsernameService = new ApplicationRoomServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));

        applicationUsernameService.findByApplicationId(ID);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindApplicationRoomShouldThrowServiceExceptionWhenRoomIdIsNull() throws DaoException, ServiceException {
        ApplicationRoomService applicationUsernameService = new ApplicationRoomServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(DENIED_APPLICATION));
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));

        applicationUsernameService.findByApplicationId(ID);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindApplicationRoomShouldThrowServiceExceptionWhenRoomIsNotFound() throws DaoException, ServiceException {
        ApplicationRoomService applicationUsernameService = new ApplicationRoomServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.empty());
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.empty());

        applicationUsernameService.findByApplicationId(ID);
    }


    @Test(expectedExceptions = ServiceException.class)
    public void testFindUserBillByApplicationIdShouldThrowServiceExceptionWhenUserIdIsWrong() throws DaoException, ServiceException {
        ApplicationRoomService applicationUsernameService = new ApplicationRoomServiceImpl(helperFactory);
        when(applicationDao.findById(anyLong())).thenReturn(Optional.of(APPROVED_APPLICATION));
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));

        applicationUsernameService.findUserBillByApplicationId(ID, ID);

    }
}
