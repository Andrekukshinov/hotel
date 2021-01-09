package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    void save(Application application) throws ServiceException;

    boolean userRejectApplication(long applicationId, long roomId, long userId) throws ServiceException;

    void approveApplication(Long applicationId, Long roomId) throws ServiceException;

    List<Application> getRangeEntities(int startFrom, int finishWith) throws ServiceException;

    ApplicationRoom getApprovedApplication(Long applicationId) throws ServiceException;

    void rejectApplication(Application application) throws ServiceException;

    Optional<Application> findInOrderApplicationById(long id) throws ServiceException;

    List<Application> getRangeUserApplications(long userId, int startFrom, int finishWith) throws ServiceException;

    int getInOrderApplicationsAmount() throws ServiceException;

    int getUserApplicationsAmount(long id) throws ServiceException;

}
