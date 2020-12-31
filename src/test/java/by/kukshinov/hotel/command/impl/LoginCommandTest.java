package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private static final String PASS = "password";
    private static final String LOGIN = "login";
    private static final Long USER_ID = 1L;
    private static final Role USER_ROLE = Role.USER;
    private static final User USER = new User(USER_ID, LOGIN, PASS, false, USER_ROLE);
    private static final String LOGIN_PARAM = "login";
    private static final String ROLE = "role";
    private static final String USER_ID_PARAM = "user_id";
    private static final String HOME_PAGE = "/hotel/controller?command=home";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String EMPTY = "";
    private static final String ERROR_MASSAGE_ATTRIBUTE = "errorMassage";
    private static final String ERROR_MASSAGE_VALUE = "User not found";


    @Test
    public void testExecuteShouldReturnRedirectToHomePage() throws ServiceException {
        UserService service = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(LOGIN, EMPTY);
        requestParameters.put(PASS, EMPTY);
        RequestContext context = new RequestContext(requestParameters, new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);
        Optional<User> userOptional = Optional.of(USER);
        when(service.findByCredentials(anyString(), anyString())).thenReturn(userOptional);

        CommandResult actual = loginCommand.execute(context);

        CommandResult expected = CommandResult.redirect(HOME_PAGE);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testExecuteShouldReturnForwardToLoginPage() throws ServiceException {
        UserService service = Mockito.mock(UserService.class);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);
        CommandResult expected = CommandResult.forward(LOGIN_PAGE);

        CommandResult actual = loginCommand.execute(context);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testExecuteShouldPutUserDetailsToRequestContext() throws ServiceException {
        UserService service = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(LOGIN_PARAM, LOGIN);
        requestParameters.put(PASS, EMPTY);
        RequestContext context = new RequestContext(requestParameters, new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);
        Optional<User> userOptional = Optional.of(USER);
        when(service.findByCredentials(anyString(), anyString())).thenReturn(userOptional);

        loginCommand.execute(context);

        String login = (String) context.getSessionAttribute(LOGIN_PARAM);
        Long id = (Long) context.getSessionAttribute(USER_ID_PARAM);
        String stringRole = (String) context.getSessionAttribute(ROLE);
        Role role = Role.valueOf(stringRole);

        // TODO: 11.12.2020 ask
        Assert.assertEquals(id, USER_ID);
        Assert.assertEquals(login, LOGIN);
        Assert.assertEquals(role, USER_ROLE);

    }

    @Test(expectedExceptions = {ServiceException.class})
    public void testExecuteShouldThrowServiceException() throws ServiceException {
        UserService service = Mockito.mock(UserService.class);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);
        when(service.findByCredentials(any(), any())).thenThrow(ServiceException.class);

        loginCommand.execute(context);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessage() throws ServiceException {
        UserService service = Mockito.mock(UserService.class);
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);

        loginCommand.execute(context);

        String actualError = (String) context.getRequestAttribute(ERROR_MASSAGE_ATTRIBUTE);
        Assert.assertEquals(actualError, ERROR_MASSAGE_VALUE);
    }


}
