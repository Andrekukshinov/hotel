package by.kukshinov.hotel.model.creators;

import by.kukshinov.hotel.model.Application;

import java.text.ParseException;

public interface ApplicationCreator {
    Application getApplication(String stringState, String personAmount, String apartment, String arrivalDate, String leavingDate, String userIdString, String stringId, String formatPattern) throws ParseException;
}
