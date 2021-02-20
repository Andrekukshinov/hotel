package by.kukshinov.hotel.dao.mapper;

import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApartmentType;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomObjectMapper implements ObjectMapper<Room> {

    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String PRICE = "price";
    private static final String ROOM_STATUS = "is_available";
    private static final String APARTMENT_TYPE = "apartment_type";
    private static final String ROOM_NUMBER = "room_number";

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        String apartmentString = resultSet.getString(APARTMENT_TYPE);

        Boolean isAvailable = resultSet.getBoolean(ROOM_STATUS);
        int number = resultSet.getInt(ROOM_NUMBER);
        ApartmentType apartmentType = ApartmentType.valueOf(apartmentString);

        Long id = resultSet.getLong(ID);
        byte capacity = resultSet.getByte(CAPACITY);
        BigDecimal price = resultSet.getBigDecimal(PRICE);

        return new Room(id, number, apartmentType, capacity, isAvailable, price);
    }
}
