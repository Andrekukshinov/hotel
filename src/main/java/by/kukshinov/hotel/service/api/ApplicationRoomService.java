package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationRoomService {

    void saveApplicationRoom(ApplicationRoom applicationRoom) throws ServiceException;

}
