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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
    private static final LocalDate CURRENT_DATE = LocalDate.now();

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
        when(roomDao.findAvailableRooms(CURRENT_DATE, CURRENT_DATE, NUMBER, NUMBER)).thenReturn(ROOMS);

        List<Room> actual = service.findRangeAvailableRooms(CURRENT_DATE, CURRENT_DATE, NUMBER, NUMBER);

        Assert.assertEquals(actual, ROOMS);
    }


    @Test
    public void testFindByIdShouldReturnRoom() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        Optional<Room> expected = Optional.of(FIRST);
        when(roomDao.findById(ROOM_ID)).thenReturn(expected);
        //when
        Optional<Room> actual = userService.findById(ROOM_ID);
        //then
        Assert.assertEquals(actual, expected);
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
    public void testUpdateRoomShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        RoomService userService = new RoomServiceImpl(helperFactory);
        doThrow(DaoException.class).when(roomDao).save(any());
        //when
        userService.updateRoom(FIRST);
    }

}
