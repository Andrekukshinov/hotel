package by.kukshinov.hotel.util;

import by.kukshinov.hotel.model.Room;

import java.math.BigDecimal;

public class RoomValidator {
    private static final int MAX_NUMBER_VALUE = 2_000_000;

    public boolean validateRoom(Room room) {
        if (BigDecimal.ONE.compareTo(room.getPrice()) > 0) {
            return false;
        }
        int number = room.getNumber();
        if (MAX_NUMBER_VALUE < number || number <= 0) {
            return false;
        }
        byte capacity = room.getCapacity();
        if (capacity <= 0 || capacity > 5) {
            return false;
        }
        return true;
    }
}
