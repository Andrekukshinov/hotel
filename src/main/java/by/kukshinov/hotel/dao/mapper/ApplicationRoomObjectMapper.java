package by.kukshinov.hotel.dao.mapper;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationRoomObjectMapper implements ObjectMapper<ApplicationRoom> {
    private static final String TOTAL_COST = "total_cost";
    private final ApplicationObjectMapper applicationObjectMapper;
    private final RoomObjectMapper roomObjectMapper;

    public ApplicationRoomObjectMapper(ApplicationObjectMapper applicationObjectMapper, RoomObjectMapper roomObjectMapper) {
        this.applicationObjectMapper = applicationObjectMapper;
        this.roomObjectMapper = roomObjectMapper;
    }

    @Override
    public ApplicationRoom map(ResultSet resultSet) throws SQLException {
        Application application = applicationObjectMapper.map(resultSet);
        Room room = roomObjectMapper.map(resultSet);

        BigDecimal totalCost = resultSet.getBigDecimal(TOTAL_COST);

        return new ApplicationRoom(application, room, totalCost);
    }
}
