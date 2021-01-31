package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateRoomPageCommandTest {
    private static final String ROOM_JSP = "WEB-INF/view/updRoom.jsp";
    private static final String ROOM_NAME = "room";
    private static final String ONE = "1";
    private static final String ID = "id";
    private static final Room ROOM = new Room();

    private RoomService service;
    private RequestContext context;

    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = Mockito.mock(RoomService.class);
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());

        context.setRequestParameter(ID, ONE);
    }

    @Test
    public void testExecuteShouldReturnForwardToUpdateRoomPage() throws ServiceException {
        when(service.findDisabledById(any())).thenReturn(ROOM);
        CommandResult expected = CommandResult.forward(ROOM_JSP);
        UpdateRoomPageCommand command = new UpdateRoomPageCommand(service);

        CommandResult actual = command.execute(context);

        Room actualRoom = (Room) context.getRequestAttribute(ROOM_NAME);
        Assert.assertEquals(actualRoom, ROOM);
        Assert.assertEquals(actual, expected);
    }

}
