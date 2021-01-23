package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.service.impl.RoomServiceImpl;
import by.kukshinov.hotel.util.PageHelperImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AllRoomsCommandTest {
    private static final String ROOMS = "rooms";
    private static final String PAGE = "page";
    private static final String FIRST = "1";
    private static final String LAST_PAGE = "lastPage";
    private static final Integer FIRST_PAGE = 1;
    private static final String ALL_ROOMS = "WEB-INF/view/allRooms.jsp";
    private static final Integer NUMBER = 1;



    private RoomService service;
    private RequestContext context;
    private PageHelperImpl pageValidator;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = Mockito.mock(RoomServiceImpl.class);
        pageValidator = Mockito.mock(PageHelperImpl.class);

        Map<String, String> param = new HashMap<>();
        param.put(PAGE, FIRST);
        context = new RequestContext(param, new HashMap<>(), null);

        when(pageValidator.getValidPage(anyString(), anyInt(), anyInt())).thenReturn(FIRST_PAGE);
        when(pageValidator.getLastPage(anyInt(), anyInt())).thenReturn(FIRST_PAGE);
    }


    @Test
    public void testExecuteShouldAddDataToContextRoomsAndReturnForwardToPageWhenValidData() throws ServiceException {
        //given
        when(service.findRangeEntities(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        CommandResult expected = CommandResult.forward(ALL_ROOMS);
        AllRoomsCommand command = new AllRoomsCommand(service, pageValidator);
        //when
        CommandResult actual = command.execute(context);
        //then
        List<Room> actualRooms = (List<Room>) context.getRequestAttribute(ROOMS);
        Integer actualLastPage = (Integer) context.getRequestAttribute(LAST_PAGE);
        Integer actualPage = (Integer) context.getRequestAttribute(PAGE);

        Assert.assertEquals(actualRooms, new ArrayList<>());
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actualLastPage, NUMBER);
        Assert.assertEquals(actualPage, NUMBER);
    }

    @Test(expectedExceptions = {ServiceException.class})//then
    public void testExecuteShouldThrowServiceException () throws ServiceException {
        //given
        when(service.findRangeEntities(anyInt(), anyInt())).thenThrow(ServiceException.class);
        AllRoomsCommand command = new AllRoomsCommand(service, pageValidator);

        //when
        command.execute(context);
    }
}
