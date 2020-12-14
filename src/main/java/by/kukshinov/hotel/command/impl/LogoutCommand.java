package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;

public class LogoutCommand implements Command {
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String LOGIN = "login";
    private static final String USER_ID = "user_id";
    private static final String ROLE = "role";

    @Override
    public CommandResult execute(RequestContext context) {
        context.removeSessionAttribute(LOGIN);
        context.removeSessionAttribute(USER_ID);
        context.removeSessionAttribute(ROLE);
        return CommandResult.forward(LOGIN_PAGE);
    }
}
