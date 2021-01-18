package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.time.LocalDate;
import java.util.Optional;

public class UserRejectCommand implements Command {
    private static final String APPLICATION_ROOM = "application";
    private static final String USER_ID = "user_id";
    private static final String BILL_PAGE = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "applicationId";
    private static final String PROFILE_HISTORY = "controller?command=profileHistory";
    private static final String TOO_LATE = "tooLate";
    private static final String MESSAGE = "late";
    private static final String WRONG_APPLICATION = "wrong application!";
    private static final String ROLE = "role";
    private static final String ADMIN = "ADMIN";
    private final ApplicationService service;
    private final ApplicationRoomService applicationRoomService;

    public UserRejectCommand(ApplicationService service, ApplicationRoomService applicationRoomService) {
        this.service = service;
        this.applicationRoomService = applicationRoomService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String role = (String) context.getSessionAttribute(ROLE);

        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        if (applicationIdString != null) {
            context.setSessionAttribute(APPLICATION_ID, applicationIdString);
        } else {
            applicationIdString = (String) context.getSessionAttribute(APPLICATION_ID);
        }

        long applicationId = Long.parseLong(applicationIdString);
        Optional<Application> applicationOptional = service.findApprovedUserApplicationById(applicationId, userId);
        Application application = applicationOptional.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));

        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate now = LocalDate.now();

        if (now.isBefore(arrivalDate) && !role.equalsIgnoreCase(ADMIN)) {
            service.userRejectApplication(application);
            return CommandResult.redirect(PROFILE_HISTORY);
        } else {
            ApplicationRoom applicationRoom = applicationRoomService.findApplicationRoom(applicationId);
            context.setRequestAttribute(TOO_LATE, MESSAGE);
            context.setRequestAttribute(ROLE, role);
            context.setRequestAttribute(APPLICATION_ROOM, applicationRoom);
            return CommandResult.forward(BILL_PAGE);
        }
    }
}
