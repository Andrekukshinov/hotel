package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;

public interface ApplicationRoomService {
    //todo ask
    ApplicationRoom findApplicationRoom(Long applicationId) throws ServiceException;

}
