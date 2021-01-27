package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.RoomService;

import java.math.BigDecimal;
import java.util.Optional;

public class SaveUpdatedRoomCommand implements Command {
    private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
    private static final String ID = "id";
    private static final String PERSON_AMOUNT = "capacity";
    private static final String ROOM_STATUS = "roomStatus";
    private static final String PRICE = "price";
    private static final String ROOM_TYPE = "roomType";
    private static final String WRONG_ROOM = "No such room exists!";
    private final RoomService roomService;

    public SaveUpdatedRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String stringId = context.getRequestParameter(ID);
        String stringRoomStatus = context.getRequestParameter(ROOM_STATUS);
        String stringPrice = context.getRequestParameter(PRICE);
        String stringRoomType = context.getRequestParameter(ROOM_TYPE);

        long id = Long.parseLong(stringId);
        Optional<Room> roomOptional = roomService.findDisabledById(id);
        Room room = roomOptional.orElseThrow(() -> new ServiceException(WRONG_ROOM));
        updateRoom(stringRoomStatus, stringPrice, stringRoomType, personAmount, room);

        roomService.updateRoom(room);

        return CommandResult.redirect(ALL_ROOMS);
    }

    private void updateRoom(String stringRoomStatus, String stringPrice, String stringRoomType, String personAmountString, Room room) throws ServiceException {
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
            Boolean roomStatus = Boolean.valueOf(stringRoomStatus);
            room.setIsAvailable(roomStatus);
        }
    }

}
