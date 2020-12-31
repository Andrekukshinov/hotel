package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateRoomCommandTest {
     private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
     private static final String PERSON_AMOUNT = "personAmount";
     private static final String ONE = "1";
     private static final String ID = "id";
     private static final String ROOM_STATUS = "roomStatus";
     private static final String OCCUPIED = "OCCUPIED";
     private static final String PRICE = "price";
     private static final String PRICE_VALUE = "371.2";
     private static final String ROOM_TYPE = "roomType";
     private static final String SKY_WALKER = "SKY_WALKER";


     @Test
     public void testExecuteShouldReturnRedirectToAllRooms () throws ServiceException {
          RoomService service = Mockito.mock(RoomService.class);
          doNothing().when(service).updateRoom(any());
          Map<String, String> params = new HashMap<>();
          params.put(PERSON_AMOUNT, ONE);
          params.put(ID, ONE);
          params.put(ROOM_STATUS, OCCUPIED);
          params.put(PRICE, PRICE_VALUE);
          params.put(ROOM_TYPE, SKY_WALKER);
          RequestContext context = new RequestContext(params, null, null);
          UpdateRoomCommand command = new UpdateRoomCommand(service);
          CommandResult expected = CommandResult.redirect(ALL_ROOMS);
          when(service.findById(anyLong())).thenReturn(Optional.of(new Room()));

          CommandResult actual = command.execute(context);

          Assert.assertEquals(actual, expected);
     }

     @Test(expectedExceptions = ServiceException.class)
     public void testExecuteShouldThrowServiceException () throws ServiceException {
          RoomService service = Mockito.mock(RoomService.class);
          doThrow(ServiceException.class).when(service).updateRoom(any());
          Map<String, String> params = new HashMap<>();
          params.put(PERSON_AMOUNT, ONE);
          params.put(ID, ONE);
          params.put(ROOM_STATUS, OCCUPIED);
          params.put(PRICE, PRICE_VALUE);
          params.put(ROOM_TYPE, SKY_WALKER);
          RequestContext context = new RequestContext(params, null, null);
          UpdateRoomCommand command = new UpdateRoomCommand(service);

          command.execute(context);
     }
}
