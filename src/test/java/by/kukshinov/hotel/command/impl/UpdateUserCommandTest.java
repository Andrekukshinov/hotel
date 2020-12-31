package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.api.UserService;
import by.kukshinov.hotel.service.impl.UserServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateUserCommandTest {
    private static final String ALL_USERS = "/hotel/controller?command=admin_users";
    private static final String LOGIN = "login";
    private static final String ID = "userId";
    private static final String IS_DISABLED = "isDisabled";
    private static final String ROLE = "role";

    private static final String LOGIN_VALUE = "ad";
    private static final String ID_VALUE = "1";
    private static final String IS_DISABLED_VALUE = "false";
    private static final String ROLE_VALUE = "USER";

     @Test
     public void testExecuteShouldReturnRedirectToHomePage () throws ServiceException {
         Map<String, String> parameters = new HashMap<>();
         parameters.put(ID, ID_VALUE);
         parameters.put(LOGIN, LOGIN_VALUE);
         parameters.put(IS_DISABLED, IS_DISABLED_VALUE);
         parameters.put(ROLE, ROLE_VALUE);
         RequestContext context = new RequestContext(parameters, null, null);
         UserService service = Mockito.mock(UserServiceImpl.class);
         doNothing().when(service).updateUser(any());
         Command command = new UpdateUserCommand(service);
         CommandResult expected = CommandResult.redirect(ALL_USERS);
         when(service.findById(anyLong())).thenReturn(Optional.of(new User()));


         CommandResult actual = command.execute(context);

         Assert.assertEquals(actual, expected);
     }

     @Test(expectedExceptions = ServiceException.class)
     public void testExecuteShouldReturnThrowServiceException () throws ServiceException {
         Map<String, String> parameters = new HashMap<>();
         parameters.put(ID, ID_VALUE);
         parameters.put(LOGIN, LOGIN_VALUE);
         parameters.put(IS_DISABLED, IS_DISABLED_VALUE);
         parameters.put(ROLE, ROLE_VALUE);
         RequestContext context = new RequestContext(parameters, null, null);
         UserService service = Mockito.mock(UserServiceImpl.class);
         doThrow(ServiceException.class).when(service).updateUser(any());
         Command command = new UpdateUserCommand(service);

         command.execute(context);
     }
}
