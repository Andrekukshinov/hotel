package by.kukshinov.hotel.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> getById(Long id);

    List<T> getAll();

    void save(T item);

    void delete(T item);
}
