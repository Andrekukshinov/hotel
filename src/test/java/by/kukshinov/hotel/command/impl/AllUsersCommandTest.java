package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.UserService;
import by.kukshinov.hotel.service.impl.UserServiceImpl;
import by.kukshinov.hotel.validators.PageValidatorImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AllUsersCommandTest {
    private static final String ALL_USERS = "WEB-INF/view/allUsers.jsp";
    private static final String PAGE = "page";
    private static final String FIRST = "1";
    private static final String ZERO = "0";
    private static final Integer FIRST_PAGE = 1;
    private static final String NEGATIVE = "-1";
    private static final User USER = new User(1, "admin", "pass", false, Role.USER);
    private static final String USERS = "users";


    @Test(expectedExceptions = {ServiceException.class})
    public void testExecuteShouldThrowServiceException () throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put(PAGE, FIRST);
        RequestContext context = new RequestContext(param, new HashMap<>(), null);
        UserService service = Mockito.mock(UserServiceImpl.class);
        when(service.getRangeUsers(anyInt(), anyInt())).thenThrow(ServiceException.class);
        PageValidatorImpl pageValidator = Mockito.mock(PageValidatorImpl.class);
        when(pageValidator.gatValidPage(anyString(), anyInt(), anyInt())).thenReturn(FIRST_PAGE);
        Command command = new AllUsersCommand(service, pageValidator);

        command.execute(context);
    }

    @Test
     public void testExecuteShouldReturnForwardToAllUsers () throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put(PAGE, FIRST);
        RequestContext context = new RequestContext(param, new HashMap<>(), null);
        UserService service = Mockito.mock(UserServiceImpl.class);
        when(service.getRangeUsers(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        PageValidatorImpl pageValidator = Mockito.mock(PageValidatorImpl.class);
        when(pageValidator.gatValidPage(anyString(), anyInt(), anyInt())).thenReturn(FIRST_PAGE);
        Command command = new AllUsersCommand(service, pageValidator);
        CommandResult expected = CommandResult.forward(ALL_USERS);

        CommandResult actual = command.execute(context);

        Assert.assertEquals(actual, expected);
     }


    @Test
    public void testExecuteShouldReturnPutToContextRooms () throws ServiceException {
        Map<String, String> param = new HashMap<>();
        param.put(PAGE, FIRST);
        RequestContext context = new RequestContext(param, new HashMap<>(), null);
        UserService service = Mockito.mock(UserServiceImpl.class);
        List<User> expected = Collections.singletonList(USER);
        when(service.getRangeUsers(anyInt(), anyInt())).thenReturn(expected);
        PageValidatorImpl pageValidator = Mockito.mock(PageValidatorImpl.class);
        when(pageValidator.gatValidPage(anyString(), anyInt(), anyInt())).thenReturn(FIRST_PAGE);
        Command command = new AllUsersCommand(service, pageValidator);

        command.execute(context);

        List<Room> actualRooms = (List<Room>) context.getRequestAttribute(USERS);
        Assert.assertEquals(actualRooms, expected);
    }
}
