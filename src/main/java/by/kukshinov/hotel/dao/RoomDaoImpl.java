package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.dao.extractor.RoomFieldsExtractor;
import by.kukshinov.hotel.dao.mapper.RoomObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.util.RequestCreator;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {
    private static final String TABLE_NAME = "Room";
    private static final String GET_AVAILABLE_ROOMS_PAGINATION =
                    " SELECT * FROM Room" +
                    " WHERE id NOT IN ( " +
                        " SELECT DISTINCT room_id FROM Application " +
                        " WHERE room_id IS NOT NULL " +
                        " AND (? <= Application.leaving_date " +
                        " AND ? >= Application.arrival_date)" +
                    " ) AND is_available = true" +
                    " LIMIT ?,?";
    private static final String NO_CONDITION = "";
    private static final String ROOM_BY_NUMBER = "SELECT * FROM Room WHERE room_number=?";
    private static final String STATUS_AVAILABLE =
            " WHERE id NOT IN ( " +
                    " SELECT DISTINCT room_id FROM Application " +
                    " WHERE room_id IS NOT NULL " +
                    " AND (? <= Application.leaving_date " +
                    " AND ? >= Application.arrival_date)) " +
                    " AND is_available = true";
    private static final String ROOM_LIMIT = "SELECT * FROM Room LIMIT ?,?";

    protected RoomDaoImpl(Connection connection) {
        super(new <Room>RequestCreator(), TABLE_NAME, connection, new RoomObjectMapper(), new RoomFieldsExtractor());
    }

    @Override
    public int getAllRoomAmount() throws DaoException {
        return getAmountEntities(NO_CONDITION);
    }

    @Override
    public int getAvailableRoomAmountForPeriod(LocalDate arrivalDate, LocalDate leavingDate) throws DaoException {
        return getAmountEntities(STATUS_AVAILABLE, arrivalDate, leavingDate);
    }

    @Override
    public List<Room> findAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_AVAILABLE_ROOMS_PAGINATION, arrivalDate, leavingDate, startFrom, finishWith);
    }

    @Override
    public Optional<Room> findByRoomNumber(int number) throws DaoException {
        return executeForSingleItem(ROOM_BY_NUMBER, number);
    }

    @Override
    public List<Room> findRangeRooms(int startFrom, int finishWith) throws DaoException {
        return executeQuery(ROOM_LIMIT, startFrom, finishWith);
    }

}
