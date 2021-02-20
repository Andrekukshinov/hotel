package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

public class ApproveApplicationCommand implements Command {
    private static final String ALL_APPLICATIONS = "/hotel/controller?command=admin_active_applications";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID = "roomId";

    private final ApplicationService applicationService;

    public ApproveApplicationCommand(ApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        String roomIdString = context.getRequestParameter(ROOM_ID);

        long applicationId = Long.parseLong(applicationIdString);
        long roomId = Long.parseLong(roomIdString);

        applicationService.approveApplication(applicationId, roomId);
        return CommandResult.redirect(ALL_APPLICATIONS);
    }
}

