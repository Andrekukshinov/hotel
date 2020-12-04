package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.request.context.RequestContext;
import by.kukshinov.hotel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UpdateUserCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final String ID = "userId";
    private static final String IS_DISABLED = "isDisabled";
    private static final String ALL_USERS = "/hotel/controller?command=all.users";
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServletException, ServiceException {
        Map<String, String> requestParameters = context.getRequestParameters();
        User toBeUpdated = getUser(requestParameters);
        userService.updateUser(toBeUpdated);
        return CommandResult.redirect(ALL_USERS);
    }

    private User getUser(Map<String, String> requestParameters) {
        String login = requestParameters.get(LOGIN);
        String pass = requestParameters.get(PASSWORD);
        String id = requestParameters.get(ID);
        String isDisabled = requestParameters.get(IS_DISABLED);
        User toBeUpdated = new User();
        toBeUpdated.setUserId(Long.parseLong(id));
        toBeUpdated.setLogin(login);
        toBeUpdated.setPassword(pass);
        toBeUpdated.setIsDisabled(Boolean.parseBoolean(isDisabled));
        return toBeUpdated;
    }
}
