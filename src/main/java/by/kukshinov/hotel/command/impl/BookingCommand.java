package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingCommand implements Command {
    private static final String FORMAT_PATTERN = "yyyy-MM-dd";

    private static final String USER_HISTORY = "/hotel/controller?command=profileHistory";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String APARTMENT = "apartment";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String USER_ID = "user_id";
    private static final String WRONG_ARRIVAL_DATE = "wrong arrival date!";
    private static final String WRONG_LEAVING_DATE = "Wrong leaving date!";
    private static final String WRONG_CAPACITY = "Wrong capacity!";


    private final ApplicationService service;


    public BookingCommand(ApplicationService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        // TODO: 25.11.2020 correct validation
        // TODO: 15.12.2020 ask about that

        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String apartment = context.getRequestParameter(APARTMENT);
        String arrivalDate = context.getRequestParameter(ARRIVAL_DATE);
        String leavingDate = context.getRequestParameter(LEAVING_DATE);
        Long userId = (Long) context.getSessionAttribute(USER_ID);

        byte capacity = Byte.parseByte(personAmount);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(FORMAT_PATTERN);
        LocalDate dateArrival = LocalDate .parse(arrivalDate, pattern);
        LocalDate dateLeaving = LocalDate .parse(leavingDate, pattern);
        validateBookingData(capacity, dateArrival, dateLeaving);
        ApartmentType apartmentType = ApartmentType.valueOf(apartment.toUpperCase());

        Application userApplication = new Application(null, capacity, apartmentType, dateArrival, dateLeaving, ApplicationStatus.IN_ORDER, null, null, userId);

        service.save(userApplication);
        return CommandResult.redirect(USER_HISTORY);
    }

    private void validateBookingData(byte capacity, LocalDate dateArrival, LocalDate dateLeaving) throws ServiceException {
        if (dateArrival.isBefore(LocalDate.now())) {
            throw new ServiceException(WRONG_ARRIVAL_DATE);
        }
        if (dateLeaving.isBefore(dateArrival)) {
            throw new ServiceException(WRONG_LEAVING_DATE);
        }
        if (capacity <= 0 || capacity > 5) {
            throw new ServiceException(WRONG_CAPACITY);
        }
    }

}

