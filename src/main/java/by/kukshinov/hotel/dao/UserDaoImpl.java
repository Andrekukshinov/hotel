package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.dao.extractor.UserFieldExtractor;
import by.kukshinov.hotel.dao.mapper.UserObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE login=? AND password=SHA1(?)";
    private static final String USER_TABLE = "user";
    private static final String UPDATE_USER = "UPDATE user SET password=?, login=?, is_disabled=?, role=? WHERE id=?";
    private static final String SAVE_USER = "INSERT INTO user VALUES(password, login, is_disabled, role) VALUES(SHA1(?), ?, ?, ?)";
    private static final String ID = "id";
    private static final String NO_CONDITION = "";
    private static final String SELECT_FROM_USER_WHERE_ROLE_ADMIN = "SELECT * FROM user WHERE role ='USER' ORDER BY login LIMIT ?,?";

    public UserDaoImpl(Connection connection) {
        super(USER_TABLE, connection, new UserObjectMapper(), new UserFieldExtractor());
    }

    @Override
    public int getAllUsersAmount() throws DaoException {
        return getAmountEntities(NO_CONDITION);
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws DaoException {
        return executeForSingleItem(GET_USER_BY_CREDENTIALS, login, pass);
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_USER;
    }

    @Override
    protected String getSaveQuery() {
        return SAVE_USER;
    }

    @Override
    protected String getDeleteParam() {
        return ID;
    }

    @Override
    public List<User> findRangeUsers(int startFrom, int finishWith) throws DaoException {
        return executeQuery(SELECT_FROM_USER_WHERE_ROLE_ADMIN, startFrom, finishWith);
    }

    @Override
    protected String getDeleteQuery() {
        return null;
    }

}
