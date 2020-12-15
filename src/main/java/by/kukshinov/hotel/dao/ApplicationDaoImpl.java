package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RangeDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.dao.extractor.ApplicationFieldsExtractor;
import by.kukshinov.hotel.dao.extractor.FieldsExtractor;
import by.kukshinov.hotel.dao.mapper.ApplicationObjectMapper;
import by.kukshinov.hotel.dao.mapper.ObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl extends AbstractDao<Application> implements ApplicationDao, RangeDao<Application> {
    private static final String BOOKING_TABLE = "application";
    private static final String SAVE_APPLICATION = "INSERT INTO application (person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_APPLICATIONS_FOR_TABLE = "SELECT * FROM Application WHERE application_state = 'IN_ORDER' limit ?, ?  ";
    private static final String GET_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? ";
    private static final String GET_QUEUED_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND application_state = 'IN_ORDER'  ";
    private static final String UPDATE_APPLICATION = "UPDATE application SET person_amount=?, apartment_type=?, arrival_date=?, leaving_date=?, application_state=?, user_id=? WHERE id=?";


    protected ApplicationDaoImpl(Connection connection) {
        super(BOOKING_TABLE, connection, new ApplicationObjectMapper(), new ApplicationFieldsExtractor());
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_APPLICATION;
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_APPLICATION;
    }

    @Override
    public Optional<Application> findById(Long id) throws DaoException {
        return executeForSingleItem(GET_APPLICATION_BY_ID, id);
    }

    @Override
    public List<Application> findRange(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_APPLICATIONS_FOR_TABLE, startFrom, finishWith);
    }

    @Override
    public void delete(Application item) {

    }

    @Override
    public Optional<Application> findQueuedById(Long id) throws DaoException {
            return executeForSingleItem(GET_QUEUED_APPLICATION_BY_ID, id);
    }
}
