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
import java.util.Optional;

public class UpdateRoomCommand implements Command {
    private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
    private static final String ID = "id";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String ROOM_STATUS = "roomStatus";
    private static final String PRICE = "price";
    private static final String ROOM_TYPE = "roomType";
    private final RoomService roomService;

    public UpdateRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    // TODO: 15.12.2020 ask

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String stringId = context.getRequestParameter(ID);
        String stringRoomStatus = context.getRequestParameter(ROOM_STATUS);
        String stringPrice = context.getRequestParameter(PRICE);
        String stringRoomType = context.getRequestParameter(ROOM_TYPE);

        long id = Long.parseLong(stringId);
        Optional<Room> roomOptional = roomService.findById(id);
        Room room = getRoomToUpdate(stringRoomStatus, stringPrice, stringRoomType, personAmount,roomOptional);

        roomService.updateRoom(room);

        return CommandResult.redirect(ALL_ROOMS);
    }

    private Room getRoomToUpdate(String stringRoomStatus, String stringPrice, String stringRoomType, String personAmountString, Optional<Room> roomOptional) throws ServiceException {
        if (!roomOptional.isPresent()) {
            throw new ServiceException("No such room exists!");
        }
        Room room = roomOptional.get();
        if (stringRoomType != null) {
            ApartmentType apartmentType = ApartmentType.valueOf(stringRoomType);
            room.setRoomType(apartmentType);
        }
        if (!stringPrice.isEmpty() && !stringPrice.startsWith("-")) {
            BigDecimal price = new BigDecimal(stringPrice);
            room.setPrice(price);
        }
        if (personAmountString != null) {
            byte personAmountByte = Byte.parseByte(personAmountString);
            room.setCapacity(personAmountByte);
        }

        if (stringRoomStatus != null) {
            RoomStatus roomStatus = RoomStatus.valueOf(stringRoomStatus);
            room.setRoomStatus(roomStatus);
        }
        return room;
    }

}
