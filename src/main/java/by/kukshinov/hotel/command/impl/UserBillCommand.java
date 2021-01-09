package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

public class UserBillCommand implements Command {
    private static final String APPLICATION = "application";
    private static final String USER_ID = "user_id";
    private static final String FORBIDDEN = "FORBIDDEN";
    private static final String BILL = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "id";

    private final ApplicationService applicationRoomService;

    public UserBillCommand(ApplicationService applicationRoomService) {
        this.applicationRoomService = applicationRoomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        long applicationId = Long.parseLong(applicationIdString);

        ApplicationRoom applicationDTO = applicationRoomService.getApprovedApplication(applicationId);

        Application application = applicationDTO.getApplication();
//
        Long userAuthId = (Long) context.getSessionAttribute(USER_ID);

        long userId = application.getUserId();
        if(userAuthId.equals(userId)) {
            context.setRequestAttribute(APPLICATION, applicationDTO);
            return CommandResult.forward(BILL);
        } else {
            throw new ServiceException(FORBIDDEN);
        }
    }
}
