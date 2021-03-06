package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;

import java.util.List;

/**
 * Interface for serving application entities according to the business logics of application
 */
public interface ApplicationService {

    /**
     * This is the method for getting a part of approved applications that belong to
     * a specified user (with a certain userId)
     * @param userId user id whose applications we are looking for
     * @param startFrom index to start looking for with
     * @param finishWith index of the last app to be found
     * @return List of user applications that are approved
     * @throws ServiceException when business logics errors occur
     */
    List<Application> findRangeUserBills(Long userId, int startFrom, int finishWith) throws ServiceException;

    /**
     * This is the method for getting a part of all applications that belong to
     * a specified user (with a certain userId)
     * @param userId user id whose applications we are looking for
     * @param startFrom index to start looking for with
     * @param finishWith index of the last app to be found
     * @return List of all user applications
     * @throws ServiceException when business logics errors occur
     */
    List<Application> findRangeUserApplications(Long userId, int startFrom, int finishWith) throws ServiceException;

    /**
     * This is the method for getting a part of applications that are
     * in order(waiting for approval or denying)
     * @param startFrom index to start looking for with
     * @param finishWith index of the last app to be found
     * @return List applications with sate in order
     * @throws ServiceException when business logics errors occur
     */
    List<Application> findRangeOrderedEntities(int startFrom, int finishWith) throws ServiceException;

    /**
     * This method is used for updating application which status is in order,
     * to the approved one(and also associating room data with it) room id of the room to be found
     * for this application, sets approval data
     * (total price, updates application state, and assigns room for the app) and saves updated app
     * @param applicationId to get application with state in order
     * @param roomId to be associated with the app
     * @throws ServiceException in case of application is not found or if business logics errors occur
     */
    void approveApplication(Long applicationId, Long roomId) throws ServiceException;

    /**
     * This method looks for the application with state approved of certain user,
     * and removes approval data if arrival date is from future and saves updated application
     * @param applicationId to find application with state approved
     * @param userId user whose application is to be found
     * @throws ServiceException in case of arrival date has passed, application doesn't belong to user,
     * state is not approved or if business logics errors occur
     */
    void userRejectApprovedApplication(Long applicationId, Long userId) throws ServiceException;


    /**
     * This is the method for finding a specific application with in order state
     * which arrival date has not passed yet
     * @param appId application id to look for with
     * @return Application that is in order
     * @throws ServiceException if such application cannot be found or when business logics errors occur
     */
    Application findFutureArrivalInOrderApplicationById(Long appId) throws ServiceException;

    /**
     * This is the method for getting all in order applications amount
     * @return applications amount that are in order
     * @throws ServiceException when business logics errors occur
     */
    int findInOrderApplicationsAmount() throws ServiceException;

    /**
     * This is the method for getting all applications amount
     * @return applications amount
     * @throws ServiceException when business logics errors occur
     */
    int findAllApplicationsAmount() throws ServiceException;

    /**
     * This is the method for getting all approved user applications amount
     * @param userId user applications to be found
     * @return approved user applications amount
     * @throws ServiceException when business logics errors occur
     */
    int findUserBillsAmount(Long userId) throws ServiceException;

    /**
     * This is the method for getting all user applications amount
     * @param id user applications to be found
     * @return all applications amount of user
     * @throws ServiceException when business logics errors occur
     */
    int findUserApplicationsAmount(Long id) throws ServiceException;

    /**
     * This method saves application
     * @param application to be saved
     * @throws ServiceException when business logics errors occur
     */
    void save(Application application) throws ServiceException;


    /**
     * This method is used for denying application that is in order and save changes
     * @param applicationId to be updated
     * @throws ServiceException if application state is not found or application state is not in order
     * and also when business logics errors occur
     */
    void adminDenyOrderedApplication(Long applicationId) throws ServiceException;

    /**
     * This method is used for canceling application that is in order and save changes
     * (if comparing with adminDenyOrderedApplication, this one is available only for customers, not for admins)
     * @param applicationId to be updated
     * @throws ServiceException if application state is not found or application state is not in order
     * and also when business logics errors occur
     */
    void userCancelOrderedApplication(Long applicationId, Long userId) throws ServiceException;
}
