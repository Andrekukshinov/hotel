package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.util.Optional;

public class UpdateApplication implements Command {
    private static final String APPLICATIONS = "/hotel/controller?command=admin_applications";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String TYPE = "type";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String STATUS = "status";
    private static final String USER_ID = "userId";
    private static final String ID = "id";
    private static final String PATTERN = "yyyy-MM-dd";
    private static final String ERROR_MESSAGE = "such application doesn't exist";
    private final ApplicationService service;


    public UpdateApplication(ApplicationService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);
        String stringStatus = context.getRequestParameter(STATUS);
        ApplicationStatus applicationStatus = ApplicationStatus.valueOf(stringStatus);
        long id = Long.parseLong(stringId);
        Optional<Application> optionalApplication = service.findQueuedApplicationById(id);
        if (optionalApplication.isPresent()) {
            Application userApplication = optionalApplication.get();
            userApplication.setStatus(applicationStatus);
            service.updateApplication(userApplication);
            return CommandResult.redirect(APPLICATIONS);
        } else {
            throw new ServiceException(ERROR_MESSAGE);
        }
    }
}
