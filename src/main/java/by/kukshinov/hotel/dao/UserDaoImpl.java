package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.mapper.UserObjectMapper;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE login=? AND pass=SHA1(?)";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserObjectMapper());
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws DaoException {
        return executeForSingleItem(GET_USER_BY_CREDENTIALS, login, pass);
    }



    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User item) {

    }

    @Override
    public void delete(User item) {

    }
}
