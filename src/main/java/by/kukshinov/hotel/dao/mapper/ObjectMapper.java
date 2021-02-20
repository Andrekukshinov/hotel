package by.kukshinov.hotel.dao.mapper;

import by.kukshinov.hotel.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface is designed for describing classes that should extract fields from ResultSet
 * into entity object
 *
 * @param <T> any class that implements Entity interface
 */
public interface ObjectMapper<T extends Entity> {

    /**
     * Method that performs actions to extract fields from ResultSet and create entity class object
     * with extracted data
     *
     * @param resultSet data received from data base (as table)
     * @return entity class object built by this method
     * @throws SQLException occurs in case of errors
     */
    T map(ResultSet resultSet) throws SQLException;
}
