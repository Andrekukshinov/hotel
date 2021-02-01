package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;


/**
 * Interface for serving applicationRoom (DTO) objects from different data sources
 */
public interface ApplicationRoomService {

    /**
     * Method for returning applicationRoom DTO object from data source
     * @param applicationId to look for the applicationRoom object by applicationId
     * @return ApplicationRoom dto object
     * @throws ServiceException when business logics errors occur
     */
    ApplicationRoom findByApplicationId(Long applicationId) throws ServiceException;

    /**
     * Method for returning applicationRoom DTO object of specified user from data source
     * @param applicationId to look for the applicationRoom object
     * @param UserId for checking if found applicationRoom belongs to user
     * @return applicationRoom object of the user
     * @throws ServiceException when business logics errors occur
     */
    ApplicationRoom findUserBillByApplicationId(Long applicationId, Long UserId) throws ServiceException;

}
