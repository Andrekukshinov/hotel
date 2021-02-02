package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Entity;

import java.util.Optional;

/**
 * Common interface for all entity classes of the application for interacting with data sources
 * @param <T> any class that implements Entity interface
 */
public interface Dao<T extends Entity> {

    /**
     * Method for extracting single Entity object from the data source by id
     * @param id entity id to look for with
     * @return Optional of entity object
     * @throws DaoException in case of errors
     */
    Optional<T> findById(Long id) throws DaoException;

    /**
     * This method is used for saving and updating Entity objects in the data source
     * @param item Entity object to be saved
     * @throws DaoException in case of errors
     */
    void save(T item) throws DaoException;
}
