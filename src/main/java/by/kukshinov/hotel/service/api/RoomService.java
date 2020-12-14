package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRangeRooms(int startFrom, int finishWith) throws ServiceException;

    void updateRoom(Room room) throws ServiceException;
}
