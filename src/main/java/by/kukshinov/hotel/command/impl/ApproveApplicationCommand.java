package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;

import java.time.LocalDate;
import java.util.Optional;

public class ApproveApplicationCommand implements Command {
    private static final String ALL_APPLICATIONS = "/hotel/controller?command=admin_active_applications";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID = "roomId";
    private static final String WRONG_APPLICATION = "Wrong application!";
    private static final String WRONG_ROOM = "Wrong room";
    private static final String TOO_LATE_TO_APPROVE = "Too late to approve";

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

        Optional<Room> roomOptional = roomService.findAvailableById(roomId);
        Optional<Application> queuedById = applicationService.findInOrderApplicationById(applicationId);

        Application application = queuedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
        Room room = roomOptional.orElseThrow(() -> new ServiceException(WRONG_ROOM));

        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate now = LocalDate.now();

        if (!now.isBefore(arrivalDate)) {
            throw new ServiceException(TOO_LATE_TO_APPROVE);
        } else {
            applicationService.approveApplication(application, room);
            return CommandResult.redirect(ALL_APPLICATIONS);
        }
    }
}
