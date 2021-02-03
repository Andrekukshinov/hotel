package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddRoomPageCommandTest {

    private static final String ERROR_VALUE = "errorNumber";
    private static final String ERROR_PARAM = "error";
    private static final String NUMBER = "number";
    private static final String NUMBER_VALUE = "103";
    private static final String ADD_ROOM_PAGE = "WEB-INF/view/addRoom.jsp";


    @Test
    public void testExecuteShouldReturnForwardAddRoomPageAndSetRequestData() throws ServiceException {
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        context.setRequestParameter(ERROR_PARAM, ERROR_VALUE);
        context.setRequestParameter(NUMBER, NUMBER_VALUE);
        AddRoomPageCommand command = new AddRoomPageCommand();
        CommandResult expected = CommandResult.forward(ADD_ROOM_PAGE);

        CommandResult actual = command.execute(context);

        String errorValue = (String) context.getRequestAttribute(ERROR_PARAM);
        String numberValue = (String) context.getRequestAttribute(NUMBER);
        Assert.assertEquals(errorValue, ERROR_VALUE);
        Assert.assertEquals(numberValue, NUMBER_VALUE);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testExecuteShouldReturnForwardAddRoomPage() throws ServiceException {
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        AddRoomPageCommand command = new AddRoomPageCommand();
        CommandResult expected = CommandResult.forward(ADD_ROOM_PAGE);

        CommandResult actual = command.execute(context);

        Assert.assertEquals(actual, expected);
    }

}
