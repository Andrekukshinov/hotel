package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.service.UserService;

import javax.servlet.ServletException;
import java.util.Map;

public class UpdateUserCommand implements Command {
    private static final String LOGIN = "login";
    private static final String ID = "userId";
    private static final String IS_DISABLED = "isDisabled";
    private static final String ALL_USERS = "/hotel/controller?command=admin_users";
    private static final String ROLE = "role";
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        User toBeUpdated = getUser(context);
        userService.updateUser(toBeUpdated);
        return CommandResult.redirect(ALL_USERS);
    }

    // TODO: 05.12.2020 place the method to anoth class
    private User getUser(RequestContext context) {
        String login = context.getRequestParameter(LOGIN);
        String id = context.getRequestParameter(ID);
        String role = context.getRequestParameter(ROLE);
        String isDisabled = context.getRequestParameter(IS_DISABLED);
        User toBeUpdated = new User();
        toBeUpdated.setUserId(Long.parseLong(id));
        toBeUpdated.setLogin(login);
        toBeUpdated.setIsDisabled(Boolean.parseBoolean(isDisabled));
        toBeUpdated.setRole(Role.valueOf(role));
        return toBeUpdated;
    }
}
