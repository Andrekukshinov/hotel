package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;

public class ApproveApplicationCommand implements Command {
    private static final String ALL_APPLICATIONS = "/hotel/controller?command=admin_active_applications";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID = "roomId";

    private final ApplicationService applicationService;
    private final RoomService roomService;

    public ApproveApplicationCommand(ApplicationService applicationService, RoomService roomService) {

        this.applicationService = applicationService;
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        String roomIdString = context.getRequestParameter(ROOM_ID);

        long applicationId = Long.parseLong(applicationIdString);
        long roomId = Long.parseLong(roomIdString);

        Room room = roomService.findAvailableById(roomId);

        applicationService.approveApplication(applicationId, room);
        return CommandResult.redirect(ALL_APPLICATIONS);
    }
}

