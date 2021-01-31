package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UserRejectCommandTest {
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID_NAME = "roomId";


    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final Long ROOM_ID = 1L;
    private static final String CAPACITY_STRING = "1";
    private static final BigDecimal PRICE = new BigDecimal("505");

    private static final Application FIRST_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);

    private static final String ONE = "1";
    private static final long ONE_LONG = 1L;
    private static final String USER_ID_SESSION = "user_id";

    private ApplicationService applicationService;
    private RequestContext context;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        applicationService = Mockito.mock(ApplicationService.class);
        context.setSessionAttribute(USER_ID_SESSION, ONE_LONG);
    }

    @Test
    public void testExecuteShouldReturnRedirectToProfileHistoryCommandWhenDataIsValidAndArrivalDateFromFuture() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);
        context.setRequestParameter(ROOM_ID_NAME, ONE);
        when(applicationService.findApprovedUserApplicationById(anyLong(), anyLong())).thenReturn(FIRST_APPLICATION);
        UserRejectCommand approveApplicationCommand = new UserRejectCommand(applicationService);
        String url = "controller?command=profileHistory";
        CommandResult expectedResult = CommandResult.redirect(url);

        CommandResult actualResult = approveApplicationCommand.execute(context);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenApplicationIsWrong() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);
        context.setRequestParameter(ROOM_ID_NAME, ONE);
        when(applicationService.findApprovedUserApplicationById(anyLong(), anyLong())).thenThrow(new ServiceException());
        UserRejectCommand approveApplicationCommand = new UserRejectCommand(applicationService);

        approveApplicationCommand.execute(context);

    }

//
//    @Test
//    public void testExecuteShouldReturnForwardToBillPageAndSetContextDataCommandWhenArrivalDateHasPassed() throws ServiceException {
//        context.setRequestParameter(APPLICATION_ID, ONE);
//        context.setRequestParameter(ROOM_ID_NAME, ONE);
//        when(applicationService.findApprovedUserApplicationById(anyLong(), anyLong())).thenReturn(INVALID_APP);
//        UserRejectCommand approveApplicationCommand = new UserRejectCommand(applicationService);
//        String url = "WEB-INF/view/userBill.jsp";
//        CommandResult expectedResult = CommandResult.forward(url);
//
//        CommandResult actualResult = approveApplicationCommand.execute(context);
//
//
//        String actualMessage = (String) context.getRequestAttribute(TOO_LATE);
//        ApplicationRoom actualAppRoom = (ApplicationRoom) context.getRequestAttribute(APPLICATION_ROOM_NAME);
//        Assert.assertEquals(actualMessage, MESSAGE);
//        Assert.assertEquals(actualAppRoom, INVALID_APP_ROOM);
//        Assert.assertEquals(actualResult, expectedResult);
//    }

}
