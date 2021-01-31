package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Entity;

import java.util.Optional;

public interface Dao<T extends Entity> {

    Optional<T> findById(Long id) throws DaoException;

    void save(T item) throws DaoException;
}
