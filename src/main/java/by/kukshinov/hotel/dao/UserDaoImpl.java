package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.RangeDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.dao.extractor.UserFieldExtractor;
import by.kukshinov.hotel.dao.mapper.UserObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao, RangeDao<User> {

    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE login=? AND password=SHA1(?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id=? ";
    private static final String GET_USERS = "SELECT * FROM user";
    private static final String USER_TABLE = "user";
    private static final String GET_USERS_FOR_TABLE = "SELECT * FROM user ORDER BY role DESC LIMIT ?,?";
    private static final String UPDATE_USER = "UPDATE user SET password=?, login=?, is_disabled=?, role=? WHERE id=?";
    private static final String SAVE_USER = "INSERT INTO user VALUES(password, login, is_disabled, role) VALUES(SHA1(?), ?, ?, ?)";
    private static final String ID = "id";
    private static final String NO_CONDITION = "";

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
    public List<User> findRange(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_USERS_FOR_TABLE, startFrom, finishWith);
    }


    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> user = executeForSingleItem(GET_USER_BY_ID, id);
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(GET_USERS);
    }

    @Override
    public void delete(User item) {
        // TODO: 01.12.2020 impl
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
    protected String getDeleteQuery() {
        return null;
    }

    @Override
    protected String getDeleteParam() {
        return ID;
    }
}
