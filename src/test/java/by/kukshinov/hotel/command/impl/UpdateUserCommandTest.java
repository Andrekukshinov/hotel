package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
    private static final String SESSION_USER_ID = "user_id";

    private UserService service;
    private RequestContext context;

    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = mock(UserService.class);

        Map<String, String> param = new HashMap<>();
        Map<String, Object> sessionAttributes = new HashMap<>();

        sessionAttributes.put(SESSION_USER_ID, 1L);

        param.put(ID, ID_VALUE);
        param.put(LOGIN, LOGIN_VALUE);
        param.put(IS_DISABLED, IS_DISABLED_VALUE);
        param.put(ROLE, ROLE_VALUE);
        context = new RequestContext(param, null, sessionAttributes);

    }


    @Test
    public void testExecuteShouldReturnRedirectToHomePage() throws ServiceException {
        //given
        doNothing().when(service).changeUserStatus(any(), anyBoolean());
        Command command = new UpdateUserCommand(service);
        CommandResult expected = CommandResult.redirect(ALL_USERS);
        User value = new User(1L, "l", false, Role.ADMIN);
        when(service.findById(anyLong())).thenReturn(Optional.of(value));
        //when
        CommandResult actual = command.execute(context);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testExecuteShouldReturnThrowServiceException() throws ServiceException {
        //given
        doThrow(ServiceException.class).when(service).updateUser(any());
        Command command = new UpdateUserCommand(service);
        //when
        command.execute(context);
    }
}
