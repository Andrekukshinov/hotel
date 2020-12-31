package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationRoomService;

import java.util.Optional;

public class UserRejectCommand implements Command {
    private static final String NOT_APPROVED = "Such application is not approved!";
    private static final String APPLICATION_ROOM = "applicationRoom";
    private static final String USER_ID = "user_id";
    private static final String BILL_PAGE = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "applicationId";
    private static final String PROFILE_HISTORY = "controller?command=profileHistory";
    private static final String TOO_LATE = "tooLate";
    private static final String MESSAGE = "too late to reject";
    private final ApplicationRoomService service;

    public UserRejectCommand(ApplicationRoomService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        long applicationId = Long.parseLong(applicationIdString);
        boolean isRemoved = service.removeApplicationRoomByApplicationId(applicationId, userId);
        if (isRemoved) {
            return CommandResult.redirect(PROFILE_HISTORY);
        } else {
            Optional<ApplicationRoom> optionalApplicationRoom = service.getApplicationRoomByAppId(applicationId);
            ApplicationRoom applicationRoom = optionalApplicationRoom.orElseThrow(() -> new ServiceException(NOT_APPROVED));
            context.setRequestAttribute(TOO_LATE, MESSAGE);
            context.setRequestAttribute(APPLICATION_ROOM, applicationRoom);
            return CommandResult.forward(BILL_PAGE);
        }
    }
}