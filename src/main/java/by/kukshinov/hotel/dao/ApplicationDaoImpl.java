package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.builder.RequestBuilder;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.extractor.ApplicationFieldsExtractor;
import by.kukshinov.hotel.dao.mapper.ApplicationObjectMapper;
import by.kukshinov.hotel.dao.mapper.RoomObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl extends AbstractDao<Application> implements ApplicationDao {
    private static final String APPLICATION_USER_ID = " LEFT JOIN room R ON application.room_id = R.id WHERE user_id=?";
    private static final String APPLICATION_STATE_IN_ORDER = " WHERE application_state='IN_ORDER'";
    private static final String BOOKING_TABLE = "application";

    private static final String GET_ALL_USER_APPLICATIONS = "SELECT * FROM Application WHERE user_id=? ORDER BY arrival_date DESC LIMIT ?, ?";
    private static final String GET_APPROVED_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND application_state = 'APPROVED'";

    private static final String GET_QUEUED_APPLICATIONS_FOR_TABLE = "SELECT * FROM Application WHERE application_state = 'IN_ORDER' ORDER BY arrival_date DESC LIMIT ?, ?";
    private static final String GET_QUEUED_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND application_state = 'IN_ORDER'  ";
    private static final String ID = "id";
    private static final String SAVE_APPLICATION = "INSERT INTO Application (person_amount, apartment_type, arrival_date, leaving_date, application_state, user_id, room_id, total_price) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_APPLICATION = "UPDATE Application SET person_amount=?, apartment_type=?, arrival_date=?, leaving_date=?, application_state=?, user_id=?, room_id=?, total_price=? WHERE id=?";


    protected ApplicationDaoImpl(Connection connection) {
        super(new<Application> RequestBuilder<Application>(), BOOKING_TABLE, connection, new ApplicationObjectMapper(), new ApplicationFieldsExtractor());
    }

    @Override
    public int getInOrderApplicationsAmount() throws DaoException {
        return getAmountEntities(APPLICATION_STATE_IN_ORDER);
    }

    @Override
    public int getUserApplicationsAmount(long userId) throws DaoException {
        return getAmountEntities(APPLICATION_USER_ID, userId);
    }

    public List<Application> findUserApplications(long userId, int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_ALL_USER_APPLICATIONS, userId, startFrom, finishWith);
    }

    @Override
    public List<Application> findAllOrderedApplications(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_QUEUED_APPLICATIONS_FOR_TABLE, startFrom, finishWith);
    }

    @Override
    public Optional<Application> findQueuedById(Long id) throws DaoException {
        return executeForSingleItem(GET_QUEUED_APPLICATION_BY_ID, id);
    }

    @Override
    public Optional<Application> findApprovedById(Long id) throws DaoException {
        return executeForSingleItem(GET_APPROVED_APPLICATION_BY_ID, id);
    }

    @Override
    protected String getDeleteParam() {
        return ID;
    }


    @Override
    protected String getDeleteQuery() {
        return null;
    }

}
