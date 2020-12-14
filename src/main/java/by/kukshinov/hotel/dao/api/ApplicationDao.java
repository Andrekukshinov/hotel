package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.User;

import java.util.List;

public interface ApplicationDao extends Dao<Application> {
    List<Application> findRangeApplications(int startFrom, int finishWith) throws DaoException;

}
