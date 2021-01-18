package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao extends Dao<Application> {
    Optional<Application> findQueuedById(Long id) throws DaoException;

    Optional<Application> findUserQueuedById(Long appId, Long userId) throws DaoException;

    Optional<Application> findUserApprovedAppById(Long appId, Long userId) throws DaoException;

    Optional<Application> findApprovedAppById(Long appId) throws DaoException;

    List<Application> findAllOrderedApplications(int startFrom, int finishWith) throws DaoException;

    List<Application> findRangeApplications(int startFrom, int finishWith) throws DaoException;

    int getInOrderApplicationsAmount() throws DaoException;

    List<Application> findUserApplications(long userId, int startFrom, int finishWith) throws DaoException;

    List<Application> findUserBills(long userId, int startFrom, int finishWith) throws DaoException;

    int findUserApplicationsAmount(long userId) throws DaoException;

    int findUserBillsAmount(long userId) throws DaoException;

    int getAllApplicationsAmount() throws DaoException;
}
