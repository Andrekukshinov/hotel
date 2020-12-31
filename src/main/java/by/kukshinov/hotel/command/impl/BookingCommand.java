package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.creators.ApplicationCreator;
import by.kukshinov.hotel.model.creators.ApplicationCreatorImpl;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.text.ParseException;

public class BookingCommand implements Command {
    private static final String FORMAT_PATTERN = "yyyy-MM-dd";

    private static final String USER_HISTORY = "/hotel/controller?command=profileHistory";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String APARTMENT = "apartment";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String USER_ID = "user_id";
    private static final String DEFAULT_ID_FOR_NEW = "0";

    private final ApplicationService service;
    private final ApplicationCreator creator;


    public BookingCommand(ApplicationService service, ApplicationCreator creator) {
        this.service = service;
        this.creator = creator;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        // TODO: 25.11.2020 correct validation
        // TODO: 15.12.2020 ask about that

        try {
            String personAmount = context.getRequestParameter(PERSON_AMOUNT);
            String apartment = context.getRequestParameter(APARTMENT);
            String arrivalDate = context.getRequestParameter(ARRIVAL_DATE);
            String leavingDate = context.getRequestParameter(LEAVING_DATE);
            Long userIdString = (Long) context.getSessionAttribute(USER_ID);

            Application userApplication = creator.getApplication(ApplicationStatus.IN_ORDER, personAmount, apartment, arrivalDate, leavingDate, userIdString, DEFAULT_ID_FOR_NEW, FORMAT_PATTERN);
            service.save(userApplication);
            return CommandResult.redirect(USER_HISTORY);
        } catch (ParseException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}

