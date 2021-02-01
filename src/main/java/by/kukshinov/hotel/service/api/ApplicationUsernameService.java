package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationUsernameDto;

import java.util.List;

/**
 * Interface for serving applicationUsernameService (DTO) objects from different data sources
 */
public interface ApplicationUsernameService {
    /**
     * Method that returns a part of dto objects containing login and application
     * @param startFrom index to start looking for with
     * @param finishWith index of the last object to be found
     * @return List of applicationUsernameService objects
     * @throws ServiceException when business logics occur
     */
    List<ApplicationUsernameDto> findRange(int startFrom, int finishWith) throws ServiceException;
}
