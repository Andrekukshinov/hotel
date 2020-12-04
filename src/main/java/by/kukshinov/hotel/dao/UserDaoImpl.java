package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.mapper.UserObjectMapper;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE login=? AND pass=SHA1(?)";
    private static final String GET_USERS = "SELECT * FROM user";
    private static final String USER_TABLE = "user";
    private static final String GET_USERS_FOR_TABLE = "SELECT * FROM user limit ?, ?";
    private static final String UPDATE_USER = "UPDATE user SET login=?, is_disabled=? WHERE id=?";

    public UserDaoImpl(Connection connection) {
        super(USER_TABLE, connection, new UserObjectMapper());
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws DaoException {
        return executeForSingleItem(GET_USER_BY_CREDENTIALS, login, pass);
    }

    @Override
    public List<User> findRangeUsers(int startFrom, int finishWith) throws DaoException {
        return executeQuery(GET_USERS_FOR_TABLE, startFrom, finishWith);
    }


    @Override
    public Optional<User> findById(Long id) {
        // TODO: 01.12.2020 impl
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(GET_USERS);
    }

    // TODO: 03.12.2020 field extractor
    @Override
    public void update(User item) throws DaoException {
        int falseInt;
        if (item.getIsDisabled()) {
            falseInt = 0;
        } else {
            falseInt = 1;
        }
        executeUpdate(
                UPDATE_USER,
                item.getLogin(),
                falseInt,
                item.getUserId()

        );
    }

    @Override
    public void save(User item) {
    }

    @Override
    public void delete(User item) {
        // TODO: 01.12.2020 impl
    }
}
