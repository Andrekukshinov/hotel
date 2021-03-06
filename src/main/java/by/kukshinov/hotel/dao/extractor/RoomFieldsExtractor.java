package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomFieldsExtractor implements FieldsExtractor<Room> {

    private static final String ROOM_TYPE = "apartment_type";
    private static final String IS_AVAILABLE = "is_available";
    private static final String PRICE = "price";
    private static final String CAPACITY = "capacity";
    private static final String ID = "id";
    private static final String ROOM_NUMBER = "room_number";

    @Override
    public Map<String, Object> extract(Room room) {
        Long id = room.getId();
        ApartmentType roomType = room.getRoomType();
        Boolean isAvailable = room.getIsAvailable();
        BigDecimal price = room.getPrice();
        int number = room.getNumber();
        byte capacity = room.getCapacity();


        Map<String, Object> result = new LinkedHashMap<>();

        result.put(CAPACITY, capacity);
        result.put(ROOM_TYPE, roomType.toString());
        result.put(IS_AVAILABLE, isAvailable);
        result.put(PRICE, price);
        result.put(ROOM_NUMBER, number);
        result.put(ID, id);

        return result;
    }
}
