package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.model.enums.RoomStatus;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;

import java.util.Optional;

public class ApproveApplicationCommand implements Command {
    private static final String ALL_APPLICATIONS = "/hotel/controller?command=admin_applications";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID = "roomId";
    private static final String ROOM_STATUS = "roomStatus";
    private static final String APPLICATION_STATUS = "applicationStatus";
    private final ApplicationService applicationService;
    private final RoomService roomService;
    private final ApplicationRoomService applicationRoomService;

    public ApproveApplicationCommand(ApplicationService applicationService, RoomService roomService, ApplicationRoomService applicationRoomService) {
        this.applicationService = applicationService;
        this.roomService = roomService;
        this.applicationRoomService = applicationRoomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        String roomIdString = context.getRequestParameter(ROOM_ID);
        String roomStatusString = context.getRequestParameter(ROOM_STATUS);
        String applicationStatusString = context.getRequestParameter(APPLICATION_STATUS);


        RoomStatus roomStatus = RoomStatus.valueOf(roomStatusString);
        ApplicationStatus applicationStatus = ApplicationStatus.valueOf(applicationStatusString);
// TODO: 15.12.2020 ask abuot this
        long applicationId = Long.parseLong(applicationIdString);
        long roomId = Long.parseLong(roomIdString);
        Optional<Application> optionalApplication = applicationService.findQueuedApplicationById(applicationId);
        Optional<Room> optionalRoom = roomService.findByAvailableById(roomId);
        if (optionalApplication.isPresent() && optionalRoom.isPresent()) {
            Application application = optionalApplication.get();
            Room room = optionalRoom.get();
            application.setStatus(applicationStatus);
            room.setRoomStatus(roomStatus);
            applicationService.updateApplication(application);
            roomService.updateRoom(room);
            ApplicationRoom applicationRoom = new ApplicationRoom(application, room);
            applicationRoomService.saveApplicationRoom(applicationRoom);
            return CommandResult.redirect(ALL_APPLICATIONS);
        } else {
            throw new ServiceException("Wrong room and application");
        }
    }
}
