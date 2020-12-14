package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.sql.Date;
import java.util.*;

public class ApplicationFieldsExtractor implements FieldsExtractor<Application> {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String TYPE = "type";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String APPLICATION_STATE = "applicationState";

    @Override
    public Map<String, Object> extract(Application application) {
        byte personAmount = application.getPersonAmount();
        ApartmentType type = application.getType();
        Date arrivalDate = application.getArrivalDate();
        Date leavingDate = application.getLeavingDate();
        long userId = application.getUserId();
        ApplicationStatus status = application.getStatus();
        long id = application.getId();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(PERSON_AMOUNT, personAmount);
        result.put(TYPE, type.toString());
        result.put(ARRIVAL_DATE, arrivalDate);
        result.put(LEAVING_DATE, leavingDate);
        result.put(APPLICATION_STATE, status.toString());
        result.put(USER_ID, userId);
        if (id > 0) {
            result.put(ID, id);
        }
        return result;
    }
}
