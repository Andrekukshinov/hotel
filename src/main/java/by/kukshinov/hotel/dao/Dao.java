package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(Long id);

    List<T> findAll() throws DaoException;

    void save(T item);

    void delete(T item);
}
