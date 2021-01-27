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
import by.kukshinov.hotel.util.PageHelper;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AvailableRoomsCommandTest {
    private static final String WRONG_APPLICATION = "Wrong application!";
    private static final String TOO_LATE_TO_APPROVE = "Too late to approve";
    private static final String LAST_PAGE = "lastPage";
    private static final String APPLICATION_ID = "applicationId";
    private static final String PAGE = "page";
    private static final String ROOMS_PARAM = "rooms";
    private static final String APPLICATION = "application";
    private static final String ONE = "1";
    private static final Integer NUMBER = 1;
    private static final long USER_ID = 1L;
    private static final Long ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final BigDecimal PRICE = new BigDecimal("505");
    private static final String CAPACITY_STRING = "1";

    private static final Room AVAILABLE_ROOM = new Room(ROOM_ID, 303, ApartmentType.BUSINESS, new Byte(CAPACITY_STRING), true, PRICE);
    private static final Application FIRST_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final List<Room> ROOMS = Arrays.asList(AVAILABLE_ROOM, AVAILABLE_ROOM, AVAILABLE_ROOM);

    private ApplicationService applicationService;
    private RoomService roomService;
    private RequestContext context;
    private PageHelper validator;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        validator = Mockito.mock(PageHelper.class);
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        applicationService = Mockito.mock(ApplicationService.class);
        roomService = Mockito.mock(RoomService.class);

        context.setRequestParameter(PAGE, ONE);

        when(validator.getValidPage(anyString(), anyInt(), anyInt())).thenReturn(NUMBER);
        when(validator.getLastPage(anyInt(), anyInt())).thenReturn(NUMBER);
    }

    @Test
    public void testExecuteShouldPutDataToContextAndReturnForwardToAvailableRoomsPage() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);
        when(applicationService.findInOrderApplicationById(anyLong())).thenReturn(Optional.of(FIRST_APPLICATION));
        when(roomService.getAvailableRoomAmount(any(), any())).thenReturn(ROOMS.size());
        when(roomService.findRangeAvailableRooms(any(), any(), anyInt(), anyInt())).thenReturn(ROOMS);
        AvailableRoomsCommand availableRoomsCommand = new AvailableRoomsCommand(applicationService, roomService, validator);
        String url = "WEB-INF/view/suggestRoom.jsp";
        CommandResult expectedResult = CommandResult.forward(url);

        CommandResult actualResult = availableRoomsCommand.execute(context);

        Integer actualLastPage = (Integer) context.getRequestAttribute(LAST_PAGE);
        List<Room> actualRooms = (List<Room>) context.getRequestAttribute(ROOMS_PARAM);
        Integer actualPage = (Integer) context.getRequestAttribute(PAGE);
        Long actualAppId = (Long) context.getRequestAttribute(APPLICATION_ID);
        Application actualApp = (Application) context.getRequestAttribute(APPLICATION);
        Assert.assertEquals(actualApp, FIRST_APPLICATION);
        Assert.assertEquals(actualLastPage, NUMBER);
        Assert.assertEquals(actualRooms, ROOMS);
        Assert.assertEquals(actualPage, NUMBER);
        Assert.assertEquals(actualAppId, ID);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = TOO_LATE_TO_APPROVE)
    public void testExecuteShouldThrowServiceExceptionWhenDataIsValidAndArrivalDateHasPassed() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);

        Application pastApplication = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now().minusDays(1), LocalDate.now().minusDays(1), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
        when(applicationService.findInOrderApplicationById(anyLong())).thenReturn(Optional.of(pastApplication));
        when(roomService.findAvailableById(anyLong())).thenReturn(Optional.of(AVAILABLE_ROOM));
        AvailableRoomsCommand availableRoomsCommand = new AvailableRoomsCommand(applicationService, roomService, validator);

        availableRoomsCommand.execute(context);
    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = WRONG_APPLICATION)
    public void testExecuteShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException {
        context.setRequestParameter(APPLICATION_ID, ONE);

        when(applicationService.findInOrderApplicationById(anyLong())).thenReturn(Optional.empty());
        when(roomService.findAvailableById(anyLong())).thenReturn(Optional.of(AVAILABLE_ROOM));
        AvailableRoomsCommand availableRoomsCommand = new AvailableRoomsCommand(applicationService, roomService, validator);

        availableRoomsCommand.execute(context);

    }

}
