package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ApproveApplicationCommandTest {
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID_NAME = "roomId";

    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final Long ROOM_ID = 1L;
    private static final String CAPACITY_STRING = "1";
    private static final BigDecimal PRICE = new BigDecimal("505");


    private static final Application APPROVED = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Room AVAILABLE_ROOM = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), true, PRICE);

    private static final String ONE = "1";

    private ApplicationService applicationService;
    private RoomService roomService;
    private RequestContext context;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        applicationService = Mockito.mock(ApplicationService.class);
        roomService = Mockito.mock(RoomService.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectToAllApplicationsCmdWhenDataIsValidAndArrivalDateFromFuture() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);
        context.setRequestParameter(ROOM_ID_NAME, ONE);
        when(applicationService.findInOrderApplicationById(anyLong())).thenReturn(APPROVED);
        when(roomService.findAvailableById(anyLong())).thenReturn(AVAILABLE_ROOM);
        ApproveApplicationCommand approveApplicationCommand = new ApproveApplicationCommand(applicationService, roomService);
        String url = "/hotel/controller?command=admin_active_applications";
        CommandResult expectedResult = CommandResult.redirect(url);

        CommandResult actualResult = approveApplicationCommand.execute(context);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenApplicationIsWrong() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);
        context.setRequestParameter(ROOM_ID_NAME, ONE);
        when(applicationService.findInOrderApplicationById(anyLong())).thenThrow(new ServiceException());
        when(roomService.findAvailableById(anyLong())).thenReturn(AVAILABLE_ROOM);
        ApproveApplicationCommand approveApplicationCommand = new ApproveApplicationCommand(applicationService, roomService);

        approveApplicationCommand.execute(context);

    }

    @Test(expectedExceptions = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenRoomIsWrong() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);
        context.setRequestParameter(ROOM_ID_NAME, ONE);
        when(applicationService.findInOrderApplicationById(anyLong())).thenReturn(APPROVED);
        when(roomService.findAvailableById(anyLong())).thenThrow(new ServiceException());
        ApproveApplicationCommand approveApplicationCommand = new ApproveApplicationCommand(applicationService, roomService);

        approveApplicationCommand.execute(context);

    }
}
