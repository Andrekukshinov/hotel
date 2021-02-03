package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;

public class AddRoomPageCommand implements Command {
    private static final String ADD_ROOM_PAGE = "WEB-INF/view/addRoom.jsp";
    private static final String ERROR_PARAM = "error";
    private static final String NUMBER = "number";

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String errorParam = context.getRequestParameter(ERROR_PARAM);
        String number = context.getRequestParameter(NUMBER);
        if(errorParam != null) {
            context.setRequestAttribute(ERROR_PARAM, errorParam);
            context.setRequestAttribute(NUMBER, number);
        }
        return CommandResult.forward(ADD_ROOM_PAGE);
    }
}
