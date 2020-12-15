package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.RangeDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.dao.extractor.RoomFieldsExtractor;
import by.kukshinov.hotel.dao.mapper.RoomObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Room;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao, RangeDao<Room> {
    private static final String TABLE_NAME = "Room";
    private static final String GET_ROOMS_PAGINATION = "SELECT * FROM room limit ?,?";
    private static final String GET_AVAILABLE_ROOMS_PAGINATION = "SELECT * FROM room WHERE status='AVAILABLE' limit ?,?";
    private static final String UPDATE_ROOM = "UPDATE Room SET apartment_type=?, status=?, price=?, room_number=?,capacity=? WHERE id=?";
    private static final String GET_AVAILABLE_ROOM_BY_ID = "SELECT * FROM Room WHERE id=? AND status = 'AVAILABLE'  ";



    protected RoomDaoImpl( Connection connection) {
        super(TABLE_NAME, connection, new RoomObjectMapper(), new RoomFieldsExtractor());
    }

    @Override
    public List<Room> findRange(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_ROOMS_PAGINATION, startFrom, finishWith);
    }

    @Override
    public List<Room> findAvailableRooms(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_AVAILABLE_ROOMS_PAGINATION, startFrom, finishWith);
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_ROOM;
    }

    @Override
    protected String getSaveQuery() {
        return null;
    }

    @Override
    public Optional<Room> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Room item) {}

    @Override
    public Optional<Room> findByAvailableById(Long id) throws DaoException {
        return executeForSingleItem(GET_AVAILABLE_ROOM_BY_ID, id);
    }
}
