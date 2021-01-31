package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;

import java.util.List;

public interface ApplicationDao extends Dao<Application> {

    List<Application> findAllOrderedApplications(int startFrom, int finishWith) throws DaoException;

    List<Application> findRangeApplications(int startFrom, int finishWith) throws DaoException;

    List<Application> findUserApplications(long userId, int startFrom, int finishWith) throws DaoException;

    List<Application> findUserBills(long userId, int startFrom, int finishWith) throws DaoException;

    int getInOrderApplicationsAmount() throws DaoException;

    int findUserApplicationsAmount(long userId) throws DaoException;

    int findUserBillsAmount(long userId) throws DaoException;

    int getAllApplicationsAmount() throws DaoException;
}
