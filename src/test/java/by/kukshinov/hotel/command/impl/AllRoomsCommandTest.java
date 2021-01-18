package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.service.impl.RoomServiceImpl;
import by.kukshinov.hotel.validators.PageValidatorImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AllRoomsCommandTest {
    private static final String ROOMS = "rooms";
    private static final String PAGE = "page";
    private static final String FIRST = "1";
    private static final String ZERO = "0";
    private static final Integer FIRST_PAGE = 1;
    private static final String ALL_ROOMS = "WEB-INF/view/allRooms.jsp";
    private static final int number = 1;
    private static final Room ROOM = new Room(1L, number, ApartmentType.BUSINESS, (byte) 2, true, new BigDecimal(122));


    private RoomService service;
    private RequestContext context;
    private PageValidatorImpl pageValidator;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = Mockito.mock(RoomServiceImpl.class);
        pageValidator = Mockito.mock(PageValidatorImpl.class);

        Map<String, String> param = new HashMap<>();
        param.put(PAGE, FIRST);
        context = new RequestContext(param, new HashMap<>(), null);

        when(pageValidator.getValidPage(anyString(), anyInt(), anyInt())).thenReturn(FIRST_PAGE);
    }


    @Test
    public void testExecuteShouldReturnForwardToAllRooms() throws ServiceException {
        //given
        when(service.findRangeEntities(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        CommandResult expected = CommandResult.forward(ALL_ROOMS);
        Command command = new AllRoomsCommand(service, pageValidator);
        //when
        CommandResult actual = command.execute(context);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = {ServiceException.class})//then
    public void testExecuteShouldThrowServiceException() throws ServiceException {
        //given
        when(service.findRangeEntities(anyInt(), anyInt())).thenThrow(ServiceException.class);
        Command command = new AllRoomsCommand(service, pageValidator);
        //when
        command.execute(context);
    }

    @Test
    public void testExecuteShouldReturnPutToContextRooms() throws ServiceException {
        //given
        List<Room> expected = Collections.singletonList(ROOM);
        when(service.findRangeEntities(anyInt(), anyInt())).thenReturn(expected);
        Command command = new AllRoomsCommand(service, pageValidator);
        //when
        command.execute(context);
        //then
        List<Room> actualRooms = (List<Room>) context.getRequestAttribute(ROOMS);
        Assert.assertEquals(actualRooms, expected);
    }
}
