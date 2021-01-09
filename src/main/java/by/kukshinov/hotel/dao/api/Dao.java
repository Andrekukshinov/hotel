package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Entity;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    // TODO: 04.12.2020 write tests

    Optional<T> findById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    void save(T item) throws DaoException;

    void delete(T item) throws DaoException;
}
