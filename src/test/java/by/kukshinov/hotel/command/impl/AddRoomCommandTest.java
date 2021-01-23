package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.service.impl.RoomServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddRoomCommandTest {
    private static final String URL = "/hotel/controller?command=admin_rooms&page=1";
    private static final String ROOM_CAPACITY = "capacity";
    private static final String APARTMENT_TYPE = "apartment-type";
    private static final String ROOM_NUMBER = "number";
    private static final String PRICE = "price";
    private static final String NUMBER_VALUE = "1";
    private static final String NEGATIVE_VALUE = "-1";
    private static final String LUX = "LUX";
    private static final String UNKNOWN = "no";
    private static final String PRICE_VALUE = "255";
    private static final String WRONG_PRICE = "Wrong price!";
    private static final String WRONG_CAPACITY = "Wrong capacity!";


    private RoomService service;
    private RequestContext context;

    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = Mockito.mock(RoomServiceImpl.class);
    }


    @Test
    public void testExecuteReturnRedirectToAllRooms() throws ServiceException {
        //given
        Map<String, String> param = new HashMap<>();
        param.put(ROOM_NUMBER, NUMBER_VALUE);
        param.put(ROOM_CAPACITY, NUMBER_VALUE);
        param.put(APARTMENT_TYPE, LUX);
        param.put(PRICE, PRICE_VALUE);
        context = new RequestContext(param, new HashMap<>(), null);
        CommandResult expected = CommandResult.redirect(URL);
        doNothing().when(service).saveRoom(any());
        AddRoomCommand command = new AddRoomCommand(service);
        //when
        CommandResult actual = command.execute(context);
        //then
        Assert.assertEquals(actual, expected);
        verify(service, times(1)).saveRoom(any());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testExecuteShouldThrowIllegalArgumentExceptionWhenGetsWrongApartmentType() throws ServiceException {
        //given
        Map<String, String> param = new HashMap<>();
        param.put(ROOM_NUMBER, NEGATIVE_VALUE);
        param.put(ROOM_CAPACITY, NEGATIVE_VALUE);
        param.put(APARTMENT_TYPE, UNKNOWN);
        param.put(PRICE, NEGATIVE_VALUE);
        context = new RequestContext(param, new HashMap<>(), null);
        //when
        AddRoomCommand command = new AddRoomCommand(service);
        //then
        command.execute(context);
    }

    @Test( expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = WRONG_PRICE)
    public void testExecuteShouldThrowServiceExceptionWhenPriceIsNegative() throws ServiceException {
        //given
        Map<String, String> param = new HashMap<>();
        param.put(ROOM_NUMBER, NUMBER_VALUE);
        param.put(ROOM_CAPACITY, NUMBER_VALUE);
        param.put(APARTMENT_TYPE, LUX);
        param.put(PRICE, NEGATIVE_VALUE);
        context = new RequestContext(param, new HashMap<>(), null);
        //when
        AddRoomCommand command = new AddRoomCommand(service);
        //then
        command.execute(context);
    }

    @Test( expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = WRONG_CAPACITY)
    public void testExecuteShouldThrowServiceExceptionWhenCapacityIsNegative() throws ServiceException {
        //given
        Map<String, String> param = new HashMap<>();
        param.put(ROOM_NUMBER, NUMBER_VALUE);
        param.put(ROOM_CAPACITY, NEGATIVE_VALUE);
        param.put(APARTMENT_TYPE, LUX);
        param.put(PRICE, NUMBER_VALUE);
        context = new RequestContext(param, new HashMap<>(), null);
        //when
        AddRoomCommand command = new AddRoomCommand(service);
        //then
        command.execute(context);
    }

    @Test( expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = "Wrong number!")
    public void testExecuteShouldThrowServiceExceptionWhenRoomNumberIsNegative() throws ServiceException {
        //given
        Map<String, String> param = new HashMap<>();
        param.put(ROOM_NUMBER, NEGATIVE_VALUE);
        param.put(ROOM_CAPACITY, NUMBER_VALUE);
        param.put(APARTMENT_TYPE, LUX);
        param.put(PRICE, NUMBER_VALUE);
        context = new RequestContext(param, new HashMap<>(), null);
        //when
        AddRoomCommand command = new AddRoomCommand(service);
        //then
        command.execute(context);
    }
}
