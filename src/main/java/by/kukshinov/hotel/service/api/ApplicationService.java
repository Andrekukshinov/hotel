package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;

import java.util.List;

public interface ApplicationService {

    List<Application> findRangeUserBills(long userId, int startFrom, int finishWith) throws ServiceException;

    List<Application> findRangeUserApplications(long userId, int startFrom, int finishWith) throws ServiceException;

    List<Application> findRangeOrderedEntities(int startFrom, int finishWith) throws ServiceException;

    void approveApplication(Application application, Room room) throws ServiceException;

    void userRejectApprovedApplication(Application application) throws ServiceException;

    Application findApprovedUserApplicationById(Long id, Long userId) throws ServiceException;

    Application findInOrderUserApplicationById(Long appId, Long userId) throws ServiceException;

    Application findInOrderApplicationById(Long id) throws ServiceException;

    Application findFutureArrivalInOrderApplicationById(Long id) throws ServiceException;

    int findInOrderApplicationsAmount() throws ServiceException;

    int findAllApplicationsAmount() throws ServiceException;

    int findUserBillsAmount(long id) throws ServiceException;

    int findUserApplicationsAmount(long id) throws ServiceException;

    void save(Application application) throws ServiceException;

    void adminDenyOrderedApplication(Application application) throws ServiceException;

    void userCancelOrderedApplication(Application application) throws ServiceException;

}
