package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.ApplicationRoomDao;
import by.kukshinov.hotel.dao.extractor.ApplicationRoomFieldsExtractor;
import by.kukshinov.hotel.dao.extractor.FieldsExtractor;
import by.kukshinov.hotel.dao.mapper.ApplicationObjectMapper;
import by.kukshinov.hotel.dao.mapper.ApplicationRoomObjectMapper;
import by.kukshinov.hotel.dao.mapper.ObjectMapper;
import by.kukshinov.hotel.dao.mapper.RoomObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.ApplicationRoom;

import java.sql.Connection;
import java.util.Optional;

public class ApplicationRoomDaoImpl extends AbstractDao<ApplicationRoom> implements ApplicationRoomDao {
    private static final String TABLE_NAME = "application_room";
    private static final String SAVE_APPLICATION = "INSERT INTO application_room (application_id,  room_id, total_cost) VALUES(?, ?, ?)";
    private static final String QUERY = "SELECT *\n" +
            "FROM application\n" +
            "         INNER JOIN application_room ON application.id = application_room.application_id\n" +
            "         RIGHT JOIN room\n" +
            "                    ON Room.id = Application_Room.room_id\n" +
            "WHERE application_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM application_room AR WHERE AR.application_id=?";
    private static final String APPLICATION_ID = "application_id";


    protected ApplicationRoomDaoImpl(Connection connection) {
        super(TABLE_NAME, connection, new ApplicationRoomObjectMapper(new ApplicationObjectMapper(), new RoomObjectMapper()), new ApplicationRoomFieldsExtractor());
    }

    @Override
    public Optional<ApplicationRoom> findByApplicationId(Long id) throws DaoException {
        return executeForSingleItem(QUERY, id);
    }

    @Override
    protected String getUpdateQuery() {
        return null;
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_APPLICATION;
    }

    @Override
    public Optional<ApplicationRoom> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected String getDeleteParam() {
        return APPLICATION_ID;
    }
}
