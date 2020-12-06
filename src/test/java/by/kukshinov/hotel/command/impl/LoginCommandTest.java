package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.service.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private static final String PASS = "password";
    private static final String LOGIN = "login";
    private static final User USER = new User(1, LOGIN, PASS, false, Role.USER);


    private static final String HOME_PAGE = "/hotel/controller?command=home";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String EMPTY = "";


    @Test
    public void testExecuteShouldReturnRedirectToHomePage() throws ServiceException {
        UserService service = Mockito.mock(UserService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(LOGIN, EMPTY);
        requestParameters.put(PASS, EMPTY);
        RequestContext context = new RequestContext( requestParameters, new HashMap<>(), new HashMap<>());
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
         RequestContext context = Mockito.mock(RequestContext.class);
         LoginCommand loginCommand = new LoginCommand(service);
         Optional<User> userOptional = Optional.empty();
         when(service.findByCredentials(anyString(), anyString())).thenReturn(userOptional);
         CommandResult expected = CommandResult.forward(LOGIN_PAGE);

         CommandResult actual = loginCommand.execute(context);

         Assert.assertEquals(actual, expected);
     }


}
