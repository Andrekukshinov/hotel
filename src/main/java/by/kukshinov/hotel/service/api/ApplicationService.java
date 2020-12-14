package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;

import java.util.List;

public interface ApplicationService {
    void save(Application application) throws ServiceException;

    List<Application> getRangeApplications(int startFrom, int finishWith) throws ServiceException;

    void updateApplication(Application application) throws ServiceException;

}
