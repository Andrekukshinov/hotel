package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;

import java.util.Optional;

public class ChangeRoomActivityCommand implements Command {
    private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
    private static final String IS_AVAILABLE = "isAvailable";
    private static final String ID = "id";
    private static final String WRONG_ROOM = "No such room exists!";

    private final RoomService roomService;

    public ChangeRoomActivityCommand(RoomService roomService) {
        this.roomService = roomService;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);

        long id = Long.parseLong(stringId);
        Optional<Room> roomOptional = roomService.findById(id);
        Room room = roomOptional.orElseThrow(() -> new ServiceException(WRONG_ROOM));

        Boolean isAvailable = room.getIsAvailable();
        room.setIsAvailable(isAvailable);

        roomService.updateRoom(room);

        return CommandResult.redirect(ALL_ROOMS);
    }
}
