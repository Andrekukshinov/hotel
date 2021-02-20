package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationRoomService;

import java.time.LocalDate;

public class UserBillCommand implements Command {
    private static final String APPLICATION = "application";
    private static final String USER_ID = "user_id";
    private static final String BILL = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "id";
    private static final String IS_REJECTABLE = "isRejectable";

    private final ApplicationRoomService applicationRoomService;

    public UserBillCommand(ApplicationRoomService applicationRoomService) {
        this.applicationRoomService = applicationRoomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        Long userAuthId = (Long) context.getSessionAttribute(USER_ID);

        long applicationId = Long.parseLong(applicationIdString);
        ApplicationRoom applicationDTO = applicationRoomService.findUserBillByApplicationId(applicationId, userAuthId);


        LocalDate arrivalDate = applicationDTO.getArrivalDate();
        LocalDate now = LocalDate.now();
        boolean isBeforeArrival = now.isBefore(arrivalDate);

        context.setRequestAttribute(APPLICATION, applicationDTO);
        context.setRequestAttribute(IS_REJECTABLE, isBeforeArrival);
        return CommandResult.forward(BILL);

    }
}
