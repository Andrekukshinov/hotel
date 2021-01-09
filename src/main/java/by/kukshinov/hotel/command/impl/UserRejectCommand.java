package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

public class UserRejectCommand implements Command {
    private static final String APPLICATION_ROOM = "application";
    private static final String USER_ID = "user_id";
    private static final String BILL_PAGE = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "applicationId";
    private static final String PROFILE_HISTORY = "controller?command=profileHistory";
    private static final String TOO_LATE = "tooLate";
    private static final String MESSAGE = "late";
    private static final String ROOM_ID = "roomId";
    private final ApplicationService service;

    public UserRejectCommand(ApplicationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        String roomIdString = context.getRequestParameter(ROOM_ID);
        if (applicationIdString != null) {
            context.setSessionAttribute(APPLICATION_ID, applicationIdString);
            context.setSessionAttribute(ROOM_ID, roomIdString);
        } else {
            applicationIdString = (String) context.getSessionAttribute(APPLICATION_ID);
            roomIdString = (String) context.getSessionAttribute(ROOM_ID);
        }

        long applicationId = Long.parseLong(applicationIdString);
        long roomId = Long.parseLong(roomIdString);
        boolean isRemoved = service.userRejectApplication(applicationId, roomId, userId);
        if (isRemoved) {
            return CommandResult.redirect(PROFILE_HISTORY);
        } else {
            ApplicationRoom applicationRoom = service.getApprovedApplication(applicationId);
            context.setRequestAttribute(TOO_LATE, MESSAGE);
            context.setRequestAttribute(APPLICATION_ROOM, applicationRoom);
            return CommandResult.forward(BILL_PAGE);
        }
    }
}
