package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserBillCommandTest {
    private static final String APPLICATION_NAME = "application";
    private static final String IS_REJECTABLE = "isRejectable";
    private static final String BILL = "WEB-INF/view/userBill.jsp";
    private static final String USER_ID = "user_id";
    private static final long ONE = 1L;
    private static final String APPLICATION_ID = "id";
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private static final Application APPLICATION = new Application(1L, (byte) 2, ApartmentType.BUSINESS, TOMORROW, TOMORROW, ApplicationStatus.APPROVED, new BigDecimal("505"), 1L, 1L);
    private static final Room ROOM = new Room();
    private static final ApplicationRoom APPLICATION_ROOM = new ApplicationRoom(APPLICATION, ROOM);
    private static final long WRONG_ID = 12L;

    private ApplicationRoomService applicationRoomService;
    private RequestContext context;


    @BeforeMethod
    public void setUpContextAndService() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        applicationRoomService = Mockito.mock(ApplicationRoomService.class);
        context.setSessionAttribute(USER_ID, ONE);
    }

    @Test
    public void testExecuteShouldReturnForwardToBillAndSetDataToContext() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, Long.toString(ONE));
        when(applicationRoomService.findUserBillByApplicationId(any(), any())).thenReturn(APPLICATION_ROOM);
        UserBillCommand command = new UserBillCommand(applicationRoomService);
        CommandResult expected = CommandResult.forward(BILL);

        CommandResult actual = command.execute(context);

        ApplicationRoom actualAppDto = (ApplicationRoom) context.getRequestAttribute(APPLICATION_NAME);
        Boolean actuallyIsRejecetable = (Boolean) context.getRequestAttribute(IS_REJECTABLE);
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actualAppDto, APPLICATION_ROOM);
        Assert.assertTrue(actuallyIsRejecetable);
    }
}
