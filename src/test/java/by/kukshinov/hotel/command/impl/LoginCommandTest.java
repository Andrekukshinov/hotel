package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.request.context.RequestContext;
import by.kukshinov.hotel.service.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
     private static final String HOME_PAGE = "/hotel/controller?command=home";
     @Test
     public void testExecuteShouldReturnRedirectToHomePage () throws ServiceException {
          UserService service = Mockito.mock(UserService.class);
          RequestContext context = Mockito.mock(RequestContext.class);
          LoginCommand loginCommand = new LoginCommand(service);
          User user = new User(1, "log", "pass", false, Role.USER);
          Map<String, String> requestParameters = new HashMap<>();
          Map<String, Object> requestAttributes = new HashMap<>();
          Map<String, Object> sessionAttributes = new HashMap<>();
          requestParameters.put("login", "");
          requestParameters.put("password", "");
          Optional<User> userOptional = Optional.of(user);
          when(service.findByCredentials(anyString(), anyString())).thenReturn(userOptional);
          when(context.getRequestParameters()).thenReturn(requestParameters);
          when(context.getRequestAttributes()).thenReturn(requestAttributes);
          when(context.getSessionAttributes()).thenReturn(sessionAttributes);
          CommandResult expected = CommandResult.redirect(HOME_PAGE);


          CommandResult actual = loginCommand.execute(context);
          Assert.assertEquals(actual, expected);
     }

}
