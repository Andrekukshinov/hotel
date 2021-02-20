package by.kukshinov.hotel.dao.mapper;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ApplicationObjectMapper implements ObjectMapper<Application> {

    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String LEAVING_DATE = "leaving_date";
    private static final String ID = "id";
    private static final String APARTMENT_TYPE = "apartment_type";
    private static final String APPLICATION_STATE = "application_state";
    private static final String USER_ID = "user_id";
    private static final String PERSON_AMOUNT = "person_amount";
    private static final String ROOM_ID = "room_id";
    private static final String TOTAL_PRICE = "total_price";

    @Override
    public Application map(ResultSet resultSet) throws SQLException {
        String apartment = resultSet.getString(APARTMENT_TYPE);
        String applicationStateString = resultSet.getString(APPLICATION_STATE);
        ApplicationStatus applicationState = ApplicationStatus.valueOf(applicationStateString);
        long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        byte person_amount = resultSet.getByte(PERSON_AMOUNT);
        LocalDate arrival_date = resultSet.getObject(ARRIVAL_DATE, LocalDate.class);
        LocalDate leaving_date = resultSet.getObject(LEAVING_DATE, LocalDate.class);
        Long roomId = (Long) resultSet.getObject(ROOM_ID);
        BigDecimal totalPrice = resultSet.getBigDecimal(TOTAL_PRICE);
        ApartmentType apartmentType = ApartmentType.valueOf(apartment);


        return new Application(id, person_amount, apartmentType, arrival_date, leaving_date, applicationState, totalPrice, roomId, userId);
    }
}
