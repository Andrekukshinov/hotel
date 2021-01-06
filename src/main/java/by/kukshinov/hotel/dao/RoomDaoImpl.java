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
    private static final String SAVE_APPLICATION = "INSERT INTO Room (apartment_type, status, price, room_number, capacity) VALUES(?, ?, ?, ?, ?)";
    private static final String GET_ROOMS_PAGINATION = "SELECT * FROM room LIMIT ?,?";
    private static final String GET_AVAILABLE_ROOMS_PAGINATION = "SELECT * FROM room WHERE status='AVAILABLE' LIMIT ?,?";
    private static final String UPDATE_ROOM = "UPDATE Room SET apartment_type=?, status=?, price=?, room_number=?,capacity=? WHERE id=?";
    private static final String GET_OCCUPIED_ROOM_BY_ID = "SELECT * FROM Room WHERE id=? AND status = 'OCCUPIED'  ";
    private static final String GET_AVAILABLE_ROOM_BY_ID = "SELECT * FROM Room WHERE id=? AND status = 'AVAILABLE'  ";
    private static final String GET_BY_ID = "SELECT * FROM Room WHERE id=?";
    private static final String ID = "id";
    private static final String NO_CONDITION = "";
    private static final String STATUS_AVAILABLE = " WHERE status='AVAILABLE'";

    protected RoomDaoImpl( Connection connection) {
        super(TABLE_NAME, connection, new RoomObjectMapper(), new RoomFieldsExtractor());
    }

    @Override
    public int getAllRoomAmount() throws DaoException {
        return getAmountEntities(NO_CONDITION);
    }
    @Override
    public int getAllAvailableRoomAmount() throws DaoException {
        return getAmountEntities(STATUS_AVAILABLE);
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
        return SAVE_APPLICATION;
    }

    @Override
    public Optional<Room> findById(Long id) throws DaoException {
        return executeForSingleItem(GET_BY_ID, id);
    }

    @Override
    public void delete(Room item) {}

    @Override
    public Optional<Room> findByOccupiedById(Long id) throws DaoException {
        return executeForSingleItem(GET_OCCUPIED_ROOM_BY_ID, id);
    }

 @Override
    public Optional<Room> findAvailableById(Long id) throws DaoException {
        return executeForSingleItem(GET_AVAILABLE_ROOM_BY_ID, id);
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getDeleteParam() {
        return ID;
    }
}
