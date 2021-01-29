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
import by.kukshinov.hotel.util.PageHelper;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
    private static final User USER = new User(1l, "admin", false, Role.USER);
    private static final String USERS = "users";


    private UserService service;
    private RequestContext context;
    private PageHelper pageValidator;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = Mockito.mock(UserServiceImpl.class);
        pageValidator = Mockito.mock(PageHelper.class);

        Map<String, String> param = new HashMap<>();
        param.put(PAGE, FIRST);
        context = new RequestContext(param, new HashMap<>(), null);

        when(pageValidator.getValidPage(anyString(), anyInt(), anyInt())).thenReturn(FIRST_PAGE);
    }

    @Test(expectedExceptions = {ServiceException.class})//then
    public void testExecuteShouldThrowServiceException () throws ServiceException {
        //given
        when(service.getRangeUsers(anyInt(), anyInt())).thenThrow(ServiceException.class);
        PageHelper pageValidator = Mockito.mock(PageHelper.class);
        Command command = new AllUsersCommand(service, pageValidator);
        //when
        command.execute(context);
    }

    @Test
     public void testExecuteShouldReturnForwardToAllUsersAndSetUsersToContext() throws ServiceException {
        //given
        List<User> expectedUsers = Collections.singletonList(USER);
        when(service.getRangeUsers(anyInt(), anyInt())).thenReturn(expectedUsers);
        Command command = new AllUsersCommand(service, pageValidator);
        CommandResult expected = CommandResult.forward(ALL_USERS);
        //when
        CommandResult actual = command.execute(context);

        //then
        List<Room> actualRooms = (List<Room>) context.getRequestAttribute(USERS);
        Assert.assertEquals(actualRooms, expectedUsers);
        Assert.assertEquals(actual, expected);
     }

}
