package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    void approveApplication(Application application, Long roomId) throws ServiceException;

    Optional<Application> findApprovedUserApplicationById(Long id, Long useId) throws ServiceException;

    Optional<Application> findInOrderApplicationById(Long id) throws ServiceException;

    Optional<Application> findInOrderUserApplicationById(Long appId, Long userId) throws ServiceException;

    List<Application> findRangeUserApplications(long userId, int startFrom, int finishWith) throws ServiceException;

    List<Application> findRangeUserBills(long userId, int startFrom, int finishWith) throws ServiceException;

    int findInOrderApplicationsAmount() throws ServiceException;

    int findUserApplicationsAmount(long id) throws ServiceException;

    int findUserBillsAmount(long id) throws ServiceException;

    int findAllApplicationsAmount() throws ServiceException;

    List<Application> findRangeOrderedEntities(int startFrom, int finishWith) throws ServiceException;

    void rejectApplication(Application application) throws ServiceException;

    void save(Application application) throws ServiceException;

    void userRejectApplication(Application application) throws ServiceException;

    void userCancelApplication(Application application) throws ServiceException;

}
