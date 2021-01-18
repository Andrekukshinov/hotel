package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.RoomService;

import java.math.BigDecimal;

public class AddRoomCommand implements Command {
    private static final String ALL_ROOMS_PAGE = "/hotel/controller?command=admin_rooms&page=1";
    private static final String ROOM_CAPACITY = "capacity";
    private static final String APARTMENT_TYPE = "apartment-type";
    private static final String NUMBER = "number";
    private static final String PRICE = "price";
    private static final String WRONG_PRICE = "Wrong price!";
    private static final String WRONG_NUMBER = "Wrong number!";
    private static final String WRONG_CAPACITY = "Wrong capacity!";
    private static final int MAX_NUMBER_LENGTH = 7;
    private final RoomService roomService;

    public AddRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String numberString = context.getRequestParameter(NUMBER);
        String apartmentTypeString = context.getRequestParameter(APARTMENT_TYPE);
        String capacityString = context.getRequestParameter(ROOM_CAPACITY);
        String priceString = context.getRequestParameter(PRICE);

        ApartmentType apartmentType = ApartmentType.valueOf(apartmentTypeString);
        BigDecimal price = new BigDecimal(priceString);
        int number = Integer.parseInt(numberString);
        byte capacity = Byte.parseByte(capacityString);

        validateRoomData(numberString, price, number, capacity);

        Room room = new Room(null, number, apartmentType, capacity, true, price);

        roomService.saveRoom(room);
        return CommandResult.redirect(ALL_ROOMS_PAGE);
    }

    private void validateRoomData(String numberString, BigDecimal price, int number, byte capacity) throws ServiceException {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ServiceException(WRONG_PRICE);
        }
        if (numberString.length() > MAX_NUMBER_LENGTH || number < 0) {
            throw new ServiceException(WRONG_NUMBER);
        }
        if (capacity <= 0 || capacity > 5) {
            throw new ServiceException(WRONG_CAPACITY);
        }
    }
}
