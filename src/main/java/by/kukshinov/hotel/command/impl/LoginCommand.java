package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.request.context.RequestContext;
import by.kukshinov.hotel.service.UserService;

import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String HOME_PAGE = "/hotel/controller?command=home";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String PASSWORD_PARAM = "password";
    private static final String LOGIN_PARAM = "login";
    private static final String ERROR_MASSAGE_ATTRIBUTE = "errorMassage";
    private static final String ERROR_MASSAGE_VALUE = "User not found";
    private static final String ROLE = "role";
    private final UserService userService;

    public LoginCommand(UserService loginService) {
        this.userService = loginService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Map<String, String> requestParameters = context.getRequestParameters();
        Map<String, Object> requestAttributes = context.getRequestAttributes();
        Map<String, Object> sessionAttributes = context.getSessionAttributes();
        String login = requestParameters.get(LOGIN_PARAM);
        String password = requestParameters.get(PASSWORD_PARAM);
        Optional<User> userOptional = userService.findByCredentials(login, password);
        if (userOptional.isPresent()) {
            // TODO: 04.12.2020 check user activity
            setAuthorizationData(sessionAttributes, login, userOptional);
            return CommandResult.redirect(HOME_PAGE);
        } else {
            requestAttributes.put(ERROR_MASSAGE_ATTRIBUTE, ERROR_MASSAGE_VALUE);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

    private void setAuthorizationData(Map<String, Object> sessionAttributes, String login, Optional<User> userOptional) {
        User user = userOptional.get();
        Role role = user.getRole();
        sessionAttributes.put(LOGIN_PARAM, login);
        sessionAttributes.put(ROLE, role);
    }
}
