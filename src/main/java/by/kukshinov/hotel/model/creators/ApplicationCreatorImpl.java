package by.kukshinov.hotel.model.creators;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApplicationCreatorImpl implements ApplicationCreator {

    public Application getApplication(ApplicationStatus status, String personAmount, String apartment, String arrivalDate, String leavingDate, Long userId, String stringId, String formatPattern) throws ParseException {

        LocalDate dateArrival = LocalDate .parse(arrivalDate,DateTimeFormatter.ofPattern( formatPattern ));
        LocalDate dateLeaving = LocalDate .parse(leavingDate,DateTimeFormatter.ofPattern( formatPattern ));
        byte capacity = Byte.parseByte(personAmount);
        ApartmentType apartmentType = ApartmentType.valueOf(apartment.toUpperCase());


        return new Application(null, capacity, apartmentType, dateArrival, dateLeaving, status, null, null, userId);
    }
}
