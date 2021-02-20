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
    private static final LocalDate NOW = LocalDate.now();
    private static final LocalDate MAX_BOOKING_DATE = NOW.plusYears(3);
    private static final int MAX_CAPACITY = 5;
    private static final int LESS_MIN_CAPACITY = 0;

    private final ApplicationService service;

    public BookingCommand(ApplicationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {

        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String apartment = context.getRequestParameter(APARTMENT);
        String arrivalDate = context.getRequestParameter(ARRIVAL_DATE);
        String leavingDate = context.getRequestParameter(LEAVING_DATE);
        Long userId = (Long) context.getSessionAttribute(USER_ID);

        byte capacity = Byte.parseByte(personAmount);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(FORMAT_PATTERN);
        LocalDate dateArrival = LocalDate.parse(arrivalDate, pattern);
        LocalDate dateLeaving = LocalDate.parse(leavingDate, pattern);
        validateBookingData(capacity, dateArrival, dateLeaving);
        ApartmentType apartmentType = ApartmentType.valueOf(apartment.toUpperCase());

        Application userApplication = new Application(null, capacity, apartmentType, dateArrival, dateLeaving, ApplicationStatus.IN_ORDER, null, null, userId);

        service.save(userApplication);
        return CommandResult.redirect(USER_HISTORY);
    }

    private void validateBookingData(byte capacity, LocalDate dateArrival, LocalDate dateLeaving) throws ServiceException {
        boolean nowNotBeforeArrival = dateArrival.isBefore(NOW);
        boolean arrivalAfterMax = dateArrival.isAfter(MAX_BOOKING_DATE);

        if (nowNotBeforeArrival || arrivalAfterMax) {
            throw new ServiceException(WRONG_ARRIVAL_DATE);
        }

        boolean leavingAfterMax = dateLeaving.isAfter(MAX_BOOKING_DATE);
        boolean leavingBeforeArrival = dateLeaving.isBefore(dateArrival);
        if (leavingBeforeArrival || leavingAfterMax) {
            throw new ServiceException(WRONG_LEAVING_DATE);
        }

        if (capacity <= LESS_MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new ServiceException(WRONG_CAPACITY);
        }
    }

}

