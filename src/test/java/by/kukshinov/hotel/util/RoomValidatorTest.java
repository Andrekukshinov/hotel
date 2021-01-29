package by.kukshinov.hotel.util;

import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class RoomValidatorTest {
    private static final long ID_VALUE = 1L;
    private static final int NUMBER_VALUE = 202;
    private static final Byte CAPACITY_VALUE = new Byte("3");
    private static final BigDecimal PRICE_VALUE = new BigDecimal("404");
    private static final Room ROOM = new Room(ID_VALUE, NUMBER_VALUE, ApartmentType.BUSINESS, CAPACITY_VALUE, true, PRICE_VALUE);

    @DataProvider
    public Object[][] roomDataProvider() {
        return new Object[][] {
                {new Room(ID_VALUE, NUMBER_VALUE, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("0"))},
                {new Room(ID_VALUE, NUMBER_VALUE, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("-1"))},
                {new Room(ID_VALUE, 0, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("1"))},
                {new Room(ID_VALUE, -1, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("1"))},
                {new Room(ID_VALUE, 2_000_001, ApartmentType.BUSINESS, new Byte("4"), true, new BigDecimal("1"))},
                {new Room(ID_VALUE, 202, ApartmentType.BUSINESS, new Byte("0"), true, new BigDecimal("1"))},
                {new Room(ID_VALUE, 202, ApartmentType.BUSINESS, new Byte("6"), true, new BigDecimal("1"))},
        };
    }

    @Test
    public void testValidateRoomShouldReturnTrueWhenRoomIsValid() {
        RoomValidator validator = new RoomValidator();

        boolean isValid = validator.validateRoom(ROOM);

        Assert.assertTrue(isValid);
    }

     @Test(dataProvider = "roomDataProvider")
     public void testValidateRoomShouldReturnFalseWhenRoomDataIsInvalid(Room room) {
         RoomValidator validator = new RoomValidator();

         boolean isValid = validator.validateRoom(room);

         Assert.assertFalse(isValid);
     }
}
