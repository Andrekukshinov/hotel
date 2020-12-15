package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    void save(Application application) throws ServiceException;

    List<Application> getRangeEntities(int startFrom, int finishWith) throws ServiceException;

    void updateApplication(Application application) throws ServiceException;

    Optional<Application> findQueuedApplicationById(long id) throws ServiceException;


}
