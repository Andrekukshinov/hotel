package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.service.api.UserService;

import java.util.Optional;

public class LoginCommand implements Command {

    private static final String HOME_PAGE = "/hotel/controller?command=home";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String PASSWORD_PARAM = "password";
    private static final String LOGIN_PARAM = "login";
    private static final String ERROR_MASSAGE_ATTRIBUTE = "errorMassage";
    private static final String ERROR_MASSAGE_VALUE = "User not found";
    private static final String ROLE = "role";
    private static final String USER_ID = "user_id";
    private final UserService userService;

    public LoginCommand(UserService loginService) {
        this.userService = loginService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String login = context.getRequestParameter(LOGIN_PARAM);
        String password = context.getRequestParameter(PASSWORD_PARAM);
        Optional<User> userOptional = userService.findByCredentials(login, password);
        if (userOptional.isPresent()) {
            // TODO: 04.12.2020 check user activity
            setAuthorizationData(context, login, userOptional.get());
            return CommandResult.redirect(HOME_PAGE);
        } else {
            context.setRequestAttribute(ERROR_MASSAGE_ATTRIBUTE, ERROR_MASSAGE_VALUE);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

    private void setAuthorizationData(RequestContext context, String login, User user) {
        Role role = user.getRole();
        long userId = user.getUserId();
        context.setSessionAttribute(LOGIN_PARAM, login);
        context.setSessionAttribute(USER_ID, userId);
        context.setSessionAttribute(ROLE, role.toString());
    }
}
