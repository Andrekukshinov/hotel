package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.RoomStatus;
import by.kukshinov.hotel.service.api.RoomService;

import java.math.BigDecimal;

public class UpdateRoomCommand implements Command {
    private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
    private static final String ID = "id";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String ROOM_STATUS = "roomStatus";
    private static final String PRICE = "price";
    private static final String ROOM_TYPE = "roomType";
    private static final String ROOM_NUMBER = "roomNumber";
    private final RoomService roomService;

    public UpdateRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String stringId = context.getRequestParameter(ID);
        String stringRoomNumber = context.getRequestParameter(ROOM_NUMBER);
        String stringRoomStatus = context.getRequestParameter(ROOM_STATUS);
        String stringPrice = context.getRequestParameter(PRICE);
        String stringRoomType = context.getRequestParameter(ROOM_TYPE);
        byte personAmountByte = Byte.parseByte(personAmount);


        RoomStatus roomStatus = RoomStatus.valueOf(stringRoomStatus);
        ApartmentType apartmentType = ApartmentType.valueOf(stringRoomType);
        long id = Long.parseLong(stringId);
        int number = Integer.parseInt(stringRoomNumber);
        BigDecimal price = new BigDecimal(stringPrice);

        Room room = new Room(id, number, apartmentType, personAmountByte, roomStatus, price, null);

        roomService.updateRoom(room);

        return CommandResult.redirect(ALL_ROOMS);
    }

}
