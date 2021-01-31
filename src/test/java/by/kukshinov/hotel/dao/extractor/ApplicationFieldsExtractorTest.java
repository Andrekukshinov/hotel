package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ApplicationFieldsExtractorTest {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String LEAVING_DATE = "leaving_date";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String APARTMENT_TYPE = "apartment_type";
    private static final String PERSON_AMOUNT = "person_amount";
    private static final String APPLICATION_STATE = "application_state";
    private static final String ROOM_ID = "room_id";
    private static final String TOTAL_PRICE = "total_price";

    private static final long USER_ID_VALUE = 1L;
    private static final long ID_VALUE = 1L;
    private static final long ROOM_ID_VALUE = 1L;
    private static final String CAPACITY_STRING = "1";
    private static final BigDecimal PRICE = new BigDecimal("505");

    private static final LocalDate NOW = LocalDate.now();
    private static final Application FIRST_APPLICATION = new Application(ID_VALUE, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, NOW, NOW, ApplicationStatus.APPROVED, PRICE, ROOM_ID_VALUE, USER_ID_VALUE);

    @Test
    public void testExtractShouldExtractAllFieldsAndPutToMap() {
        Map<String, Object> expected = new HashMap<>();
        expected.put(PERSON_AMOUNT, new Byte(CAPACITY_STRING));
        expected.put(APARTMENT_TYPE, ApartmentType.BUSINESS.toString());
        expected.put(ARRIVAL_DATE, NOW);
        expected.put(LEAVING_DATE, NOW);
        expected.put(APPLICATION_STATE, ApplicationStatus.APPROVED.toString());
        expected.put(USER_ID, USER_ID_VALUE);
        expected.put(ROOM_ID, ROOM_ID_VALUE);
        expected.put(TOTAL_PRICE, PRICE);
        expected.put(ID, ID_VALUE);
        ApplicationFieldsExtractor extractor = new ApplicationFieldsExtractor();

        Map<String, Object> actual = extractor.extract(FIRST_APPLICATION);

        Assert.assertEquals(actual, expected);
    }
}
