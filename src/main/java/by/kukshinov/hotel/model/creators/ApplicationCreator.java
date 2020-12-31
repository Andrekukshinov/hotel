package by.kukshinov.hotel.model.creators;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.text.ParseException;

public interface ApplicationCreator {
    Application getApplication(ApplicationStatus state, String personAmount, String apartment, String arrivalDate, String leavingDate, Long userId, String stringId, String formatPattern) throws ParseException;
}
