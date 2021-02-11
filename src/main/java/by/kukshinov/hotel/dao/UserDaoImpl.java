package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.dao.extractor.UserFieldExtractor;
import by.kukshinov.hotel.dao.mapper.UserObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.util.RequestCreator;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String GET_USER_BY_CREDENTIALS =
            "SELECT User.id, login, password, is_disabled, role " +
                    "FROM User LEFT JOIN Role R on R.id = User.role_id " +
                    "WHERE login=? " +
                    "AND password=SHA1(?)";
    private static final String USER_TABLE = "User";
    private static final String FIND_BY_ID =
            "SELECT User.id, login, is_disabled, role " +
                    "FROM User LEFT JOIN Role R on R.id = User.role_id " +
                    "WHERE User.id=?";
    private static final String LEFT_JOIN_ROLE = " LEFT JOIN Role R on R.id = User.role_id ";
    private static final String SELECT_FROM_USER_WHERE_ROLE_ADMIN =
            "SELECT * " +
                    "FROM User " +
                    "LEFT JOIN Role R on R.id = User.role_id " +
                    "WHERE R.role ='USER' " +
                    "ORDER BY login " +
                    "LIMIT ?,?";

    public UserDaoImpl(Connection connection) {
        super(new <User>RequestCreator(), USER_TABLE, connection, new UserObjectMapper(), new UserFieldExtractor());
    }

    @Override
    public int getAllUsersAmount() throws DaoException {
        return getAmountEntities(LEFT_JOIN_ROLE);
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        return executeForSingleItem(FIND_BY_ID, id);
    }

    @Override
    public Optional<User> findByCredentials(String login, String password) throws DaoException {
        return executeForSingleItem(GET_USER_BY_CREDENTIALS, login, password);
    }

    @Override
    public List<User> findRangeUsers(int startFrom, int finishWith) throws DaoException {
        return executeQuery(SELECT_FROM_USER_WHERE_ROLE_ADMIN, startFrom, finishWith);
    }
}
