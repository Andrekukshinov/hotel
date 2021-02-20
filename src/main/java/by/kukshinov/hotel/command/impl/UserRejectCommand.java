package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

public class UserRejectCommand implements Command {
    private static final String USER_ID = "user_id";
    private static final String APPLICATION_ID = "applicationId";
    private static final String PROFILE_HISTORY = "controller?command=profileHistory";


    private final ApplicationService service;

    public UserRejectCommand(ApplicationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);

        long applicationId = Long.parseLong(applicationIdString);

        service.userRejectApprovedApplication(applicationId, userId);
        return CommandResult.redirect(PROFILE_HISTORY);
    }
}
