package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.dao.extractor.FieldsExtractor;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.dao.mapper.ObjectMapper;
import by.kukshinov.hotel.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {

    private static final String GET_USERS = "SELECT * FROM ";
    private final String tableName;

    private Connection connection;
    private ObjectMapper<T> objectMapper;
    private FieldsExtractor<T> fieldsExtractor;


    protected AbstractDao(String tableName, Connection connection, ObjectMapper<T> objectMapper, FieldsExtractor<T> fieldsExtractor) {
        this.tableName = tableName;
        this.connection = connection;
        this.objectMapper = objectMapper;
        this.fieldsExtractor = fieldsExtractor;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatement(query, params)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables.getMessage(), throwables);
        }
    }

    @Override
    public List<T> findAll() throws DaoException {
        return executeQuery(GET_USERS + tableName);
    }

    @Override
    public void save(T item) throws DaoException {
        List<Object> fields = fieldsExtractor.extract(item);
        Object[] values = fields.toArray();
        executeUpdate(getUpdateQuery(), values);
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                T resultItem = objectMapper.map(resultSet);
                result.add(resultItem);
            }
            return result;
        } catch (SQLException throwables) {
            throw new DaoException(throwables.getMessage(), throwables);
        }
    }

    private PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement result = connection.prepareStatement(query);
        for (int runner = 1; runner < params.length + 1; ++runner) {
            result.setObject(runner, params[runner - 1]);
        }
        return result;
    }

    protected Optional<T> executeForSingleItem(String query, String login, String pass) throws DaoException {
        List<T> users = executeQuery(query, login, pass);
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getUpdateQuery();

}
