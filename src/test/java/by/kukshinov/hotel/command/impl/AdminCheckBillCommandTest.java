package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.*;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import by.kukshinov.hotel.service.api.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.Mockito.when;

public class AdminCheckBillCommandTest {
    private static final String PASS = "password";
    private static final String LOGIN = "login";
    private static final String APPLICATION = "application";

    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final String CAPACITY_STRING = "1";
    private static final BigDecimal PRICE = new BigDecimal("505");

    private static final Application FIRST_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Room AVAILABLE_ROOM = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), true, PRICE);
    private static final User USER = new User(2l, LOGIN, false, Role.USER);
    private static final String APPLICATION_ID = "id";
    private static final String ROLE = "ANY";
    private static final String ROLE_NAME = "ROLE";
    private static final String ID_VALUE = "1";

    private UserService userService;
    private ApplicationRoomService applicationRoomService;
    private RequestContext context;

    @BeforeMethod
    public void mockServicesAndRequestContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());

        userService = Mockito.mock(UserService.class);
        applicationRoomService = Mockito.mock(ApplicationRoomService.class);

        context.setSessionAttribute(ROLE_NAME, ROLE);
    }

    @Test
    public void testExecuteShouldAddToContextDataAndReturnForwardToPageWhenValidData() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ID_VALUE);
        AdminCheckBillCommand command = new AdminCheckBillCommand(userService, applicationRoomService);
        ApplicationRoom expectedAppRoom = new ApplicationRoom(FIRST_APPLICATION, AVAILABLE_ROOM);
        when(applicationRoomService.findByApplicationId(ID)).thenReturn(expectedAppRoom);
        when(userService.findCustomerById(USER_ID)).thenReturn(USER);
        String url = "WEB-INF/view/userBill.jsp";
        CommandResult expected = CommandResult.forward(url);

        CommandResult actual = command.execute(context);

        ApplicationRoom actualAppRoom = (ApplicationRoom) context.getRequestAttribute(APPLICATION);
        String actualLogin = (String) context.getRequestAttribute(LOGIN);
        String actualRole = (String) context.getRequestAttribute(ROLE_NAME);
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actualAppRoom, expectedAppRoom);
        Assert.assertEquals(actualLogin, LOGIN);
        Assert.assertEquals(actualRole, ROLE);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testExecuteShouldThrowIllegalArgumentsExceptionWhenIdInvalid() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, PASS);
        AdminCheckBillCommand command = new AdminCheckBillCommand(userService, applicationRoomService);

        command.execute(context);
    }
}
