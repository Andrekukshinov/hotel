package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.creators.ApplicationCreator;
import by.kukshinov.hotel.model.creators.ApplicationCreatorImpl;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.text.ParseException;

public class BookingCommand implements Command {
    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String FORMAT_PATTERN = "yyyy-MM-dd'T'hh:mm";

    private static final String USER_HISTORY = "/hotel/controller?command=profileHistory";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String APARTMENT = "apartment";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String USER_ID = "userId";
    private static final String STATE = "state";
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
        // TODO: 15.12.2020 change to get by id + update
        try {
            String stringState = context.getRequestParameter(STATE);
            String personAmount = context.getRequestParameter(PERSON_AMOUNT);
            String apartment = context.getRequestParameter(APARTMENT);
            String arrivalDate = context.getRequestParameter(ARRIVAL_DATE);
            String leavingDate = context.getRequestParameter(LEAVING_DATE);
            String userIdString = context.getRequestParameter(USER_ID);
//            Map<String, String> requestFields = new HashMap<>();
//            requestFields.put(STATE, stringState);
//            requestFields.put(ID, stringId);
//            requestFields.put(PERSON_AMOUNT, personAmount);
//            requestFields.put(APARTMENT, apartment);
//            requestFields.put(ARRIVAL_DATE, arrivalDate);
//            requestFields.put(LEAVING_DATE, leavingDate);
//            requestFields.put(USER_ID, userIdString);
            Application userApplication = creator.getApplication(stringState, personAmount, apartment, arrivalDate, leavingDate, userIdString, DEFAULT_ID_FOR_NEW, FORMAT_PATTERN);
            service.save(userApplication);
            return CommandResult.redirect(USER_HISTORY);
        } catch (ParseException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}

