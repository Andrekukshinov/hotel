package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationRoomService;

import java.time.LocalDate;
import java.util.Optional;

public class UserBillCommand implements Command {
    private static final String NOT_APPROVED = "Such application is not approved!";
    private static final String APPLICATION_ROOM = "applicationRoom";
    private static final String USER_ID = "user_id";
    private static final String FORBIDDEN = "FORBIDDEN";
    private static final String BILL = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "id";

    private final ApplicationRoomService applicationRoomService;

    public UserBillCommand(ApplicationRoomService applicationRoomService) {
        this.applicationRoomService = applicationRoomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        long applicationId = Long.parseLong(applicationIdString);

        Optional<ApplicationRoom> optionalApplicationRoom = applicationRoomService.getApplicationRoomByAppId(applicationId);
        ApplicationRoom applicationRoom = optionalApplicationRoom.orElseThrow(() -> new ServiceException(NOT_APPROVED));

        Long userAuthId = (Long) context.getSessionAttribute(USER_ID);
        Application application = applicationRoom.getApplication();
        long userId = application.getUserId();
        if(userAuthId.equals(userId)) {
            context.setRequestAttribute(APPLICATION_ROOM, applicationRoom);
            return CommandResult.forward(BILL);
        } else {
            throw new ServiceException(FORBIDDEN);
        }
    }
}
