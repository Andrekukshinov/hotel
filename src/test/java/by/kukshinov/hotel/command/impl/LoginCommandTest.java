package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
    private static final String ERROR_MASSAGE_VALUE = "not.found";

    private UserService service;

    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = mock(UserService.class);
    }


    @Test
    public void testExecuteShouldReturnRedirectToHomePageAndPutUserDataToContext() throws ServiceException {
        //given
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(LOGIN, EMPTY);
        requestParameters.put(PASS, EMPTY);
        RequestContext context = new RequestContext(requestParameters, new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);
        Optional<User> userOptional = Optional.of(USER);
        when(service.findByCredentials(anyString(), anyString())).thenReturn(userOptional);
        //when
        CommandResult actual = loginCommand.execute(context);
        //then
        CommandResult expected = CommandResult.redirect(HOME_PAGE);
        String login = (String) context.getSessionAttribute(LOGIN_PARAM);
        Long id = (Long) context.getSessionAttribute(USER_ID_PARAM);
        String stringRole = (String) context.getSessionAttribute(ROLE);
        Role role = Role.valueOf(stringRole);

        Assert.assertEquals(id, USER_ID);
        Assert.assertEquals(login, LOGIN);
        Assert.assertEquals(role, USER_ROLE);
        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = {ServiceException.class})//then
    public void testExecuteShouldThrowServiceException() throws ServiceException {
        //given
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);
        when(service.findByCredentials(any(), any())).thenThrow(ServiceException.class);

        //when
        loginCommand.execute(context);
    }

    @Test
    public void testExecuteShouldPutToContextErrorMessage() throws ServiceException {
        //given
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LoginCommand loginCommand = new LoginCommand(service);

        //when
        loginCommand.execute(context);

        //then
        String actualError = (String) context.getRequestAttribute(ERROR_MASSAGE_ATTRIBUTE);
        Assert.assertEquals(actualError, ERROR_MASSAGE_VALUE);
    }

}
