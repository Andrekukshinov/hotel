package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;

public interface ApplicationRoomService {
    ApplicationRoom findByApplicationId(Long applicationId) throws ServiceException;

    ApplicationRoom findUserBillByApplicationId(Long applicationId, Long UserId) throws ServiceException;

}
