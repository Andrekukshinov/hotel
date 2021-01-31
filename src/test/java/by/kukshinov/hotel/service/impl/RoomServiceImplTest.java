package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.RoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoomServiceImplTest {
    private static final long ROOM_ID = 1L;
    private static final Room FIRST = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("505"));
    private static final Room SECOND = new Room(ROOM_ID, 307, ApartmentType.BUSINESS, new Byte("3"), true, new BigDecimal("505"));
    private static final Room THIRD = new Room(ROOM_ID, 306, ApartmentType.BUSINESS, new Byte("1"), true, new BigDecimal("505"));
    private static final Room FOURTH = new Room(ROOM_ID, 305, ApartmentType.BUSINESS, new Byte("2"), false, new BigDecimal("505"));
    private static final Room FIFTH = new Room(ROOM_ID, 304, ApartmentType.BUSINESS, new Byte("5"), true, new BigDecimal("505"));


    private static final List<Room> ROOMS = Arrays.asList(FIRST, SECOND, THIRD, FOURTH, FIFTH);
    private static final int SIZE = ROOMS.size();
    private static final int NUMBER = 1;
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);

    private DaoHelperFactory helperFactory;
    private RoomDao roomDao;

    @BeforeMethod
    private void mockDaoAndHelperAndCreationBehaviour() {
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        helperFactory = Mockito.mock(DaoHelperFactory.class);
        roomDao = Mockito.mock(RoomDao.class);

        when(helperFactory.createDaoHelper()).thenReturn(daoHelper);
        when(daoHelper.createRoomDao()).thenReturn(roomDao);
    }

    @Test
    public void testGetAvailableRoomAmountShouldReturnListSize() throws DaoException, ServiceException {
        RoomService service = new RoomServiceImpl(helperFactory);
        when(roomDao.getAvailableRoomAmountForPeriod(any(), any())).thenReturn(SIZE);

        int actual = service.getAvailableRoomAmount(LocalDate.now(), LocalDate.now());

        Assert.assertEquals(actual, SIZE);
    }

    @Test
    public void testGetRoomAmountShouldReturnListSize() throws DaoException, ServiceException {
        RoomService service = new RoomServiceImpl(helperFactory);
        when(roomDao.getAllRoomAmount()).thenReturn(SIZE);

        int actual = service.getRoomAmount();

        Assert.assertEquals(actual, SIZE);
    }

    @Test
    public void testFindRangeEntitiesShouldReturnListOfRooms() throws DaoException, ServiceException {
        RoomService service = new RoomServiceImpl(helperFactory);
        when(roomDao.findRangeRooms(NUMBER, NUMBER)).thenReturn(ROOMS);

        List<Room> actual = service.findRangeEntities(NUMBER, NUMBER);

        Assert.assertEquals(actual, ROOMS);
    }

    @Test
    public void testFindRangeAvailableRoomsShouldReturnListOfRooms() throws DaoException, ServiceException {
        RoomService service = new RoomServiceImpl(helperFactory);
        when(roomDao.findAvailableRooms(TOMORROW, TOMORROW, NUMBER, NUMBER)).thenReturn(ROOMS);

        List<Room> actual = service.findRangeAvailableRooms(TOMORROW, TOMORROW, NUMBER, NUMBER);

        Assert.assertEquals(actual, ROOMS);
    }

    @Test
    public void testFindByIdShouldReturnRoomWhenRoomIsFound() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        Optional<Room> expected = Optional.of(FIRST);
        when(roomDao.findById(ROOM_ID)).thenReturn(expected);
        //when
        Room actual = userService.findById(ROOM_ID);
        //then
        Assert.assertEquals(actual, FIRST);
    }

    @Test
    public void testSwitchRoomActivityShouldUpdateRoomActivity() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        Room start = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("505"));
        Room expected = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte("4"), false, new BigDecimal("505"));
        doNothing().when(roomDao).save(any());
        //when
        userService.switchRoomActivity(start);
        //then
        Assert.assertEquals(start, expected);
    }

    @Test
    public void testFindAvailableByIdShouldReturnRoomWhenValidRoomIsFound() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        Optional<Room> expected = Optional.of(FIRST);
        when(roomDao.findById(ROOM_ID)).thenReturn(expected);
        //when
        Room actual = userService.findAvailableById(ROOM_ID);
        //then
        Assert.assertEquals(actual, FIRST);
    }

    @Test
    public void testFindDisabledByIdShouldReturnRoomWhenValidRoomIsFound() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        Optional<Room> expected = Optional.of(FOURTH);
        when(roomDao.findById(ROOM_ID)).thenReturn(expected);
        //when
        Room actual = userService.findDisabledById(ROOM_ID);
        //then
        Assert.assertEquals(actual, FOURTH);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindByIdShouldThrowServiceExceptionWhenRoomIsNotFound() throws ServiceException, DaoException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.empty());
        //when
        userService.findById(ROOM_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testSaveShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        doThrow(DaoException.class).when(roomDao).save(any());
        //when
        userService.saveRoom(FIRST);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindAvailableByIdShouldThrowServiceExceptionWhenRoomIsInvalid() throws ServiceException, DaoException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(FOURTH));
        //when
        userService.findAvailableById(ROOM_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindDisabledByIdShouldThrowServiceExceptionWhenRoomIsInvalid() throws ServiceException, DaoException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(FIFTH));
        //when
        userService.findDisabledById(ROOM_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindAvailableByIdShouldThrowServiceExceptionWhenRoomIsNotFound() throws ServiceException, DaoException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.empty());
        //when
        userService.findAvailableById(ROOM_ID);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testFindDisabledByIdShouldThrowServiceExceptionWhenRoomIsNotFound() throws ServiceException, DaoException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.empty());
        //when
        userService.findDisabledById(ROOM_ID);
    }

    @Test
    public void testFindRangeAvailableRoomsShouldThrowException() throws DaoException, ServiceException {
        RoomService service = new RoomServiceImpl(helperFactory);
        when(roomDao.findAvailableRooms(TOMORROW, TOMORROW, NUMBER, NUMBER)).thenReturn(ROOMS);

        List<Room> actual = service.findRangeAvailableRooms(TOMORROW, TOMORROW, NUMBER, NUMBER);

        Assert.assertEquals(actual, ROOMS);
    }

}
