package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.creators.ApplicationCreator;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.text.ParseException;

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
    private final ApplicationService service;
    private final ApplicationCreator creator;


    public UpdateApplication(ApplicationService service, ApplicationCreator creator) {
        this.service = service;
        this.creator = creator;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringState = context.getRequestParameter(STATUS);
        String stringId = context.getRequestParameter(ID);
        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String apartment = context.getRequestParameter(TYPE);
        String arrivalDate = context.getRequestParameter(ARRIVAL_DATE);
        String leavingDate = context.getRequestParameter(LEAVING_DATE);
        String userIdString = context.getRequestParameter(USER_ID);
        try {
            Application userApplication = creator.getApplication(stringState, personAmount, apartment, arrivalDate, leavingDate, userIdString, stringId, PATTERN);
            service.save(userApplication);
            return CommandResult.redirect(APPLICATIONS);
        } catch (ParseException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
