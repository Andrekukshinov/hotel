package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getRangeEntities(int startFrom, int finishWith) throws ServiceException;

    List<Room> getRangeAvailableRooms(int startFrom, int finishWith) throws ServiceException;

    void updateRoom(Room room) throws ServiceException;

    Optional<Room> findByAvailableById(Long id) throws ServiceException;

}
