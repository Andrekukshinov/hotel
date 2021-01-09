package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.builder.RequestBuilder;
import by.kukshinov.hotel.dao.api.Dao;
import by.kukshinov.hotel.dao.extractor.FieldsExtractor;
import by.kukshinov.hotel.dao.mapper.ObjectMapper;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    private static final String GET_ENTITIES = "SELECT * FROM ";
    private static final String ID_CONDITION = " WHERE id=? ";
    private final String tableName;
    private final String getById;
    private final String entitiesCount = "SELECT COUNT(*) FROM ";

    private Connection connection;
    private final RequestBuilder<T> requestBuilder;
    private ObjectMapper<T> objectMapper;
    private FieldsExtractor<T> fieldsExtractor;


    protected AbstractDao(RequestBuilder<T> requestBuilder, String tableName, Connection connection, ObjectMapper<T> objectMapper, FieldsExtractor<T> fieldsExtractor) {
        this.tableName = tableName;
        this.connection = connection;
        this.objectMapper = objectMapper;
        this.fieldsExtractor = fieldsExtractor;
        this.requestBuilder = requestBuilder;
        getById = GET_ENTITIES + tableName + ID_CONDITION;
    }

    protected void executeForSave(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatement(query, params)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected int executeQueryForSum(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatement(query, params)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findAll() throws DaoException {
        return executeQuery(GET_ENTITIES + tableName);
    }

    @Override
    public void save(T item) throws DaoException {
        Map<String, Object> extracted = fieldsExtractor.extract(item);
        List<Object> fields = new ArrayList<>(extracted.values());
        Object[] values = fields.toArray();
        String query = requestBuilder.buildQuery(item, tableName, extracted);
        executeForSave(query, values);
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        return executeForSingleItem(getById, id);
    }


    @Override
    public void delete(T item) throws DaoException {
        Map<String, Object> extracted = fieldsExtractor.extract(item);
        Object deleteParam = extracted.get(getDeleteParam());
        executeForSave(getDeleteQuery(), deleteParam);
    }

    protected int getAmountEntities(String condition, Object... params) throws DaoException {
        return executeQueryForSum(entitiesCount + tableName + condition, params);
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

    protected Optional<T> executeForSingleItem(String query, Object... params) throws DaoException {
        List<T> users = executeQuery(query, params);
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getDeleteQuery();

    protected abstract String getDeleteParam();

}
