package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.util.RoomValidator;
import javafx.scene.control.TextFormatter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaveUpdatedRoomCommandTest {
     private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
     private static final String CAPACITY = "capacity";
     private static final String ONE = "1";
     private static final String ID = "id";
     private static final String ROOM_STATUS = "roomStatus";
     private static final String OCCUPIED = "OCCUPIED";
     private static final String PRICE = "price";
     private static final String PRICE_VALUE = "371.2";
     private static final String ROOM_TYPE = "roomType";
     private static final String SKY_WALKER = "SKY_WALKER";

     private RoomService service;
     private RequestContext context;
     private RoomValidator validator;


     @BeforeMethod
     public void mockServicesAndRequestContext() {
          service = mock(RoomService.class);
          validator = mock(RoomValidator.class);

          Map<String, String> param = new HashMap<>();
          param.put(CAPACITY, ONE);
          param.put(ID, ONE);
          param.put(ROOM_STATUS, OCCUPIED);
          param.put(PRICE, PRICE_VALUE);
          param.put(ROOM_TYPE, SKY_WALKER);
          context = new RequestContext(param, new HashMap<>(), null);
     }


     @Test
     public void testExecuteShouldReturnRedirectToAllRooms () throws ServiceException {
          //given
          SaveUpdatedRoomCommand command = new SaveUpdatedRoomCommand(validator, service);
          CommandResult expected = CommandResult.redirect(ALL_ROOMS);
          when(service.findDisabledById(anyLong())).thenReturn(Optional.of(new Room()));
          when(validator.validateRoom(any())).thenReturn(true);
          doNothing().when(service).saveRoom(any());
          //when
          CommandResult actual = command.execute(context);
          //then
          Assert.assertEquals(actual, expected);
     }

     @Test(expectedExceptions = ServiceException.class)
     public void testExecuteShouldThrowServiceExceptionWhenRoomDataIsInvalid () throws ServiceException {
          //given
          SaveUpdatedRoomCommand command = new SaveUpdatedRoomCommand(validator, service);
          when(service.findDisabledById(anyLong())).thenReturn(Optional.of(new Room()));
          when(validator.validateRoom(any())).thenReturn(false);
          doNothing().when(service).saveRoom(any());
          //when
          command.execute(context);
     }

     @Test(expectedExceptions = ServiceException.class)//then
     public void testExecuteShouldThrowServiceException () throws ServiceException {
          //given
          SaveUpdatedRoomCommand command = new SaveUpdatedRoomCommand(validator, service);
          doThrow(ServiceException.class).when(service).saveRoom(any());
          //when
          command.execute(context);
     }
}
