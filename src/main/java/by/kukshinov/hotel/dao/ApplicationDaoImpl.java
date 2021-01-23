package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.builder.RequestBuilder;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.extractor.ApplicationFieldsExtractor;
import by.kukshinov.hotel.dao.mapper.ApplicationObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl extends AbstractDao<Application> implements ApplicationDao {
    private static final String USER_APPS_AMOUNT_CONDITION = " LEFT JOIN room R ON application.room_id = R.id WHERE user_id=? AND application_state != 'CANCELLED'";
    private static final String USER_BILLS_AMOUNT_CONDITION = " LEFT JOIN room R ON application.room_id = R.id WHERE user_id=? AND application_state = 'APPROVED'";
    private static final String ALL_APPLICATIONS = " LEFT JOIN room R ON application.room_id = R.id WHERE application_state != 'CANCELLED'";
    private static final String APPLICATION_STATE_IN_ORDER = " WHERE application_state='IN_ORDER'";
    private static final String BOOKING_TABLE = "application";

    private static final String GET_ALL_USER_APPLICATIONS = "SELECT * FROM Application WHERE user_id=? AND application_state != 'CANCELLED' ORDER BY arrival_date DESC LIMIT ?, ?";
    private static final String GET_ALL_USER_BILLS = "SELECT * FROM Application WHERE user_id=? AND application_state = 'APPROVED' ORDER BY arrival_date DESC LIMIT ?, ?";
    private static final String GET_APPROVED_USER_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND user_id=? AND application_state = 'APPROVED'";
    private static final String GET_APPROVED_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND application_state = 'APPROVED'";

    private static final String GET_QUEUED_APPLICATIONS_FOR_TABLE = "SELECT * FROM Application WHERE application_state = 'IN_ORDER' ORDER BY arrival_date DESC LIMIT ?, ?";
    private static final String GET_APPLICATIONS_RANGE = "SELECT * FROM Application WHERE application_state != 'CANCELLED' ORDER BY arrival_date DESC LIMIT ?, ?";
    private static final String GET_QUEUED_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND application_state = 'IN_ORDER'";

    private static final String GET_USER_QUEUED_APPLICATION_BY_ID = "SELECT * FROM Application WHERE id=? AND application_state = 'IN_ORDER' AND user_id=? ";
    private static final String ID = "id";

    protected ApplicationDaoImpl(Connection connection) {
        super(new<Application> RequestBuilder<Application>(), BOOKING_TABLE, connection, new ApplicationObjectMapper(), new ApplicationFieldsExtractor());
    }

    @Override
    public int getInOrderApplicationsAmount() throws DaoException {
        return getAmountEntities(APPLICATION_STATE_IN_ORDER);
    }

    @Override
    public int findUserApplicationsAmount(long userId) throws DaoException {
        return getAmountEntities(USER_APPS_AMOUNT_CONDITION, userId);
    }

    @Override
    public int findUserBillsAmount(long userId) throws DaoException {
        return getAmountEntities(USER_BILLS_AMOUNT_CONDITION, userId);
    }

    @Override
    public int getAllApplicationsAmount() throws DaoException {
        return getAmountEntities(ALL_APPLICATIONS);
    }

    public List<Application> findUserApplications(long userId, int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_ALL_USER_APPLICATIONS, userId, startFrom, finishWith);
    }

    public List<Application> findUserBills(long userId, int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_ALL_USER_BILLS, userId, startFrom, finishWith);
    }

    @Override
    public List<Application> findAllOrderedApplications(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_QUEUED_APPLICATIONS_FOR_TABLE, startFrom, finishWith);
    }


    @Override
    public     List<Application> findRangeApplications(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_APPLICATIONS_RANGE, startFrom, finishWith);
    }

    @Override
    public Optional<Application> findQueuedById(Long id) throws DaoException {
        return executeForSingleItem(GET_QUEUED_APPLICATION_BY_ID, id);
    }

    @Override
    public Optional<Application> findUserQueuedById(Long appId, Long userId) throws DaoException {
        return executeForSingleItem(GET_USER_QUEUED_APPLICATION_BY_ID, appId, userId);
    }

    @Override
    public Optional<Application> findUserApprovedAppById(Long appId, Long userId) throws DaoException {
        return executeForSingleItem(GET_APPROVED_USER_APPLICATION_BY_ID, appId, userId);
    }

    @Override
    public Optional<Application> findApprovedAppById(Long appId)  throws DaoException {
        return executeForSingleItem(GET_APPROVED_APPLICATION_BY_ID, appId);
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
