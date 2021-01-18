package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ApplicationFieldsExtractor implements FieldsExtractor<Application> {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String LEAVING_DATE = "leaving_date";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String TYPE = "apartment_type";
    private static final String PERSON_AMOUNT = "person_amount";
    private static final String APPLICATION_STATE = "application_state";
    private static final String ROOM_ID = "room_id";

    @Override
    public Map<String, Object> extract(Application application) {
        Long id = application.getId();
        byte personAmount = application.getPersonAmount();
        ApartmentType type = application.getType();
        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate leavingDate = application.getLeavingDate();
        Long userId = application.getUserId();
        ApplicationStatus status = application.getStatus();
        Long roomId = application.getRoomId();
        BigDecimal totalPrice = application.getTotalPrice();

        Map<String, Object> result = new LinkedHashMap<>();

        result.put(PERSON_AMOUNT, personAmount);
        result.put(TYPE, type.toString());
        result.put(ARRIVAL_DATE, arrivalDate.toString());
        result.put(LEAVING_DATE, leavingDate.toString());
        result.put(APPLICATION_STATE, status.toString());
        result.put(USER_ID, userId);
        result.put(ROOM_ID, roomId);
        result.put("total_price", totalPrice);
        result.put(ID, id);
        // TODO: 06.01.2021 rework with T extends entity

        return result;
    }
}
