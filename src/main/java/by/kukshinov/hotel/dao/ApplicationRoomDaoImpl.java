package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.ApplicationRoomDao;
import by.kukshinov.hotel.dao.extractor.ApplicationRoomFieldsExtractor;
import by.kukshinov.hotel.dao.extractor.FieldsExtractor;
import by.kukshinov.hotel.dao.mapper.ObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.ApplicationRoom;

import java.sql.Connection;
import java.util.Optional;

public class ApplicationRoomDaoImpl extends AbstractDao<ApplicationRoom> implements ApplicationRoomDao {
    private static final String TABLE_NAME = "application_room";
    private static final String SAVE_APPLICATION = "INSERT INTO application_room (application_id,  room_id) VALUES(?, ?)";



    protected ApplicationRoomDaoImpl(Connection connection) {
        super(TABLE_NAME, connection, null, new ApplicationRoomFieldsExtractor());
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
    public void delete(ApplicationRoom item) {

    }
}
