package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.RoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ChangeRoomActivityCommandTest {
    private static final String ID = "id";
    private static final Long ROOM_ID = 1L;
    private static final String CAPACITY_STRING = "1";
    private static final BigDecimal PRICE = new BigDecimal("505");
    private static final Room AVAILABLE_ROOM = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), true, PRICE);
    private static final String IS_AVAILABLE = "isAvailable";
    private static final String ONE = "1";
    private static final String FALSE = "false";


    private RoomService roomService;
    private RequestContext context;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        roomService = Mockito.mock(RoomService.class);
    }

    @Test
    public void testExecuteShouldUpdateRoomAndReturnRedirectToAllPages() throws ServiceException {
        context.setRequestParameter(ID, ONE);
        context.setRequestParameter(IS_AVAILABLE, FALSE);
        Room expectedRoom = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), false, PRICE);
        when(roomService.findById(anyLong())).thenReturn(Optional.of(expectedRoom));
        String url = "/hotel/controller?command=admin_rooms";
        CommandResult expectedResult = CommandResult.redirect(url);
        ChangeRoomActivityCommand  command = new ChangeRoomActivityCommand(roomService);

        CommandResult actualResult = command.execute(context);

        Assert.assertEquals(actualResult, expectedResult);
    }
}