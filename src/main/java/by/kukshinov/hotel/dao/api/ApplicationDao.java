package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;

import java.util.List;

/**
 * Interface for managing Application entities with different data sources
 */
public interface ApplicationDao extends Dao<Application> {

    /**
     * Method for extracting list of applications with in order state
     *
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of in order applications
     * @throws DaoException in case of errors
     */
    List<Application> findAllOrderedApplications(int startFrom, int finishWith) throws DaoException;

    /**
     * Method for extracting list of applications
     *
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of applications
     * @throws DaoException in case of errors
     */
    List<Application> findRangeApplications(int startFrom, int finishWith) throws DaoException;

    /**
     * Method for extracting list of applications of a certain user
     *
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of applications that belong to the user
     * @throws DaoException in case of errors
     */
    List<Application> findUserApplications(long userId, int startFrom, int finishWith) throws DaoException;

    /**
     * Method for extracting list of applications with approved state that belong to the certain user
     *
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of approved user's applications
     * @throws DaoException in case of errors
     */
    List<Application> findUserBills(long userId, int startFrom, int finishWith) throws DaoException;

    /**
     * Method for receiving all in order applications amount
     *
     * @return all in order applications amount
     * @throws DaoException in case of errors
     */
    int getInOrderApplicationsAmount() throws DaoException;

    /**
     * Method for receiving all applications amount of a specified user
     *
     * @param userId applications to look for with
     * @return all approved applications amount of a specified user
     * @throws DaoException in case of errors
     */
    int findUserApplicationsAmount(Long userId) throws DaoException;


    /**
     * Method for receiving all approved applications amount of a specified user
     *
     * @param userId applications to look for with
     * @return all approved applications amount of a specified user
     * @throws DaoException in case of errors
     */
    int findUserBillsAmount(Long userId) throws DaoException;

    /**
     * Method for receiving all applications amount
     *
     * @return all applications amount
     * @throws DaoException in case of errors
     */
    int getAllApplicationsAmount() throws DaoException;
}
