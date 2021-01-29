package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class RoomFieldsExtractorTest {

    private static final String ROOM_TYPE = "apartment_type";
    private static final String IS_AVAILABLE = "is_available";
    private static final String PRICE = "price";
    private static final String CAPACITY = "capacity";
    private static final String ID = "id";
    private static final String ROOM_NUMBER = "room_number";

    private static final long ID_VALUE = 1L;
    private static final int NUMBER_VALUE = 202;
    private static final Byte CAPACITY_VALUE = new Byte("3");
    private static final BigDecimal PRICE_VALUE = new BigDecimal("404");
    private static final Room ROOM = new Room(ID_VALUE, NUMBER_VALUE, ApartmentType.BUSINESS, CAPACITY_VALUE, true, PRICE_VALUE);


    @Test
    public void testExtractShouldExtractAllFieldsFromRoomAndPutToMap() {
        Map<String, Object> expected = new HashMap<>();
        expected.put(ROOM_TYPE, ApartmentType.BUSINESS.toString());
        expected.put(IS_AVAILABLE, true);
        expected.put(PRICE, PRICE_VALUE);
        expected.put(CAPACITY, CAPACITY_VALUE);
        expected.put(ID, ID_VALUE);
        expected.put(ROOM_NUMBER,  NUMBER_VALUE);
        RoomFieldsExtractor fieldsExtractor = new RoomFieldsExtractor();

        Map<String, Object> actual = fieldsExtractor.extract(ROOM);

        Assert.assertEquals(actual, expected);
    }
}
