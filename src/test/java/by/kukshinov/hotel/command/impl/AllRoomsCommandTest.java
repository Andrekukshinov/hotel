package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.RoomStatus;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.service.impl.RoomServiceImpl;
import by.kukshinov.hotel.validators.PageValidatorImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AllRoomsCommandTest {
     private static final String ROOMS = "rooms";
     private static final String PAGE = "page";
     private static final String FIRST = "1";
     private static final String ZERO = "0";
     private static final Integer FIRST_PAGE = 1;
     private static final String NEGATIVE = "-1";
     private static final String ALL_ROOMS = "WEB-INF/view/allRooms.jsp";
     private static final int number = 1;
     private static final Room ROOM = new Room(1L, number, ApartmentType.BUSINESS, (byte) 2, RoomStatus.AVAILABLE, new BigDecimal(122), "");

     @Test
     public void testExecuteShouldReturnForwardToAllRooms () throws ServiceException {
          Map<String, String> param = new HashMap<>();
          param.put(PAGE, FIRST);
          RequestContext context = new RequestContext(param, new HashMap<>(), null);
          RoomService service = Mockito.mock(RoomServiceImpl.class);
          when(service.getRangeEntities(anyInt(), anyInt())).thenReturn(new ArrayList<>());
          PageValidatorImpl pageValidator = Mockito.mock(PageValidatorImpl.class);
          when(pageValidator.gatValidPage(anyString())).thenReturn(FIRST_PAGE);
          Command command = new AllRoomsCommand(service, pageValidator);

          CommandResult expected = CommandResult.forward(ALL_ROOMS);

          CommandResult actual = command.execute(context);

          Assert.assertEquals(actual, expected);
     }

     @Test(expectedExceptions = {ServiceException.class})
     public void testExecuteShouldThrowServiceException () throws ServiceException {
          Map<String, String> param = new HashMap<>();
          param.put(PAGE, FIRST);
          RequestContext context = new RequestContext(param, new HashMap<>(), null);
          RoomService service = Mockito.mock(RoomServiceImpl.class);
          when(service.getRangeEntities(anyInt(), anyInt())).thenThrow(ServiceException.class);
          PageValidatorImpl pageValidator = Mockito.mock(PageValidatorImpl.class);
          when(pageValidator.gatValidPage(anyString())).thenReturn(FIRST_PAGE);
          Command command = new AllRoomsCommand(service, pageValidator);

          command.execute(context);
     }

     @Test
     public void testExecuteShouldReturnPutToContextRooms () throws ServiceException {
          Map<String, String> param = new HashMap<>();
          param.put(PAGE, FIRST);
          RequestContext context = new RequestContext(param, new HashMap<>(), null);
          RoomService service = Mockito.mock(RoomServiceImpl.class);
          List<Room> expected = Collections.singletonList(ROOM);
          when(service.getRangeEntities(anyInt(), anyInt())).thenReturn(expected);
          PageValidatorImpl pageValidator = Mockito.mock(PageValidatorImpl.class);
          when(pageValidator.gatValidPage(anyString())).thenReturn(FIRST_PAGE);
          Command command = new AllRoomsCommand(service, pageValidator);


          command.execute(context);

          List<Room> actualRooms = (List<Room>) context.getRequestAttribute(ROOMS);
          Assert.assertEquals(actualRooms, expected);
     }
}
