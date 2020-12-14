package by.kukshinov.hotel.model.creators;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationCreatorImpl implements ApplicationCreator {


    public Application getApplication(String stringState, String personAmount, String apartment, String arrivalDate, String leavingDate, String userIdString, String stringId, String formatPattern) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(formatPattern);
        Date dateArrival = formatter.parse(arrivalDate);
        Date dateLeaving = formatter.parse(leavingDate);

        ApplicationStatus status = ApplicationStatus.valueOf(stringState);
        byte capacity = Byte.parseByte(personAmount);
        ApartmentType apartmentType = ApartmentType.valueOf(apartment.toUpperCase());
        java.sql.Date sqlDateArrival = new java.sql.Date(dateArrival.getTime());
        java.sql.Date sqlDateLeaving = new java.sql.Date(dateLeaving.getTime());
        long userId = Long.parseLong(userIdString);
        long id = Long.parseLong(stringId);
        return new Application(id, capacity, apartmentType, sqlDateArrival, sqlDateLeaving, status, userId);
    }
}
