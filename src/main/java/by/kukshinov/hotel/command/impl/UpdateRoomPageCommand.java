package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;

import java.util.Optional;

public class UpdateRoomPageCommand implements Command {
    private static final String ID = "id";
    private static final String WRONG_ROOM = "Wrong room!";
    private static final String ROOM = "room";
    private static final String ROOM_JSP = "WEB-INF/view/updRoom.jsp";


    private final RoomService roomService;

    public UpdateRoomPageCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String requestParameter = context.getRequestParameter(ID);
        Long id = Long.parseLong(requestParameter);

        Optional<Room> roomOptional = roomService.findById(id);

        Room room = roomOptional.orElseThrow(() -> new ServiceException(WRONG_ROOM));

        context.setRequestAttribute(ROOM, room);
        return CommandResult.forward(ROOM_JSP);
    }
}
