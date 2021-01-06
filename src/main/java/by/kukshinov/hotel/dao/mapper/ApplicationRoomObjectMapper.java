package by.kukshinov.hotel.dao.mapper;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationRoomObjectMapper implements ObjectMapper<ApplicationRoom> {
    private static final String TOTAL_COST = "total_cost";
    private static final String ROOM_ID = "room_id";
    private static final String APPLICATION_ID = "application_id";
    private final ApplicationObjectMapper applicationObjectMapper;
    private final RoomObjectMapper roomObjectMapper;

    public ApplicationRoomObjectMapper(ApplicationObjectMapper applicationObjectMapper, RoomObjectMapper roomObjectMapper) {
        this.applicationObjectMapper = applicationObjectMapper;
        this.roomObjectMapper = roomObjectMapper;
    }

    @Override
    public ApplicationRoom map(ResultSet resultSet) throws SQLException {
        Room room  = roomObjectMapper.map(resultSet);
        long roomId = resultSet.getLong(ROOM_ID);
        room.setId(roomId);
        Application application = applicationObjectMapper.map(resultSet);
        long  appId = resultSet.getLong(APPLICATION_ID);
        application.setId(appId);

        BigDecimal totalCost = resultSet.getBigDecimal(TOTAL_COST);

        return new ApplicationRoom(application, room, totalCost);
    }
}
