package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;

import java.util.List;

public interface RangeDao<T> {
    List<T> findRange(int startFrom, int finishWith) throws DaoException;
}
