package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.util.RoomValidator;

import java.math.BigDecimal;

public class SaveUpdatedRoomCommand implements Command {
    private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String PRICE = "price";
    private static final String ROOM_TYPE = "roomType";
    private static final String WRONG_DATA = "Wrong data!";

    private final RoomValidator validator;
    private final RoomService roomService;

    public SaveUpdatedRoomCommand(RoomValidator validator, RoomService roomService) {
        this.validator = validator;
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);
        String capacityString = context.getRequestParameter(CAPACITY);
        String priceString = context.getRequestParameter(PRICE);
        String apartmentTypeString = context.getRequestParameter(ROOM_TYPE);

        ApartmentType apartmentType = ApartmentType.valueOf(apartmentTypeString);
        BigDecimal price = new BigDecimal(priceString);
        long id = Long.parseLong(stringId);
        byte capacity = Byte.parseByte(capacityString);

        Room room = roomService.findDisabledById(id);

        room.setRoomType(apartmentType);
        room.setPrice(price);
        room.setCapacity(capacity);

        if (!validator.validateRoom(room)) {
            throw new ServiceException(WRONG_DATA);
        }

        roomService.saveRoom(room);

        return CommandResult.redirect(ALL_ROOMS);

    }

}
