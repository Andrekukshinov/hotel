package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.RoomService;

public class ChangeRoomActivityCommand implements Command {
    private static final String ALL_ROOMS = "/hotel/controller?command=admin_rooms";
    private static final String ID = "id";
    private static final String IS_AVAILABLE = "isAvailable";

    private final RoomService roomService;

    public ChangeRoomActivityCommand(RoomService roomService) {
        this.roomService = roomService;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);
        String isAvailableString = context.getRequestParameter(IS_AVAILABLE);

        long id = Long.parseLong(stringId);
        boolean isAvailable = Boolean.parseBoolean(isAvailableString);

        roomService.switchRoomActivity(id, isAvailable);

        return CommandResult.redirect(ALL_ROOMS);
    }
}
