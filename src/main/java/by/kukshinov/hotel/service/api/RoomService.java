package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    void saveRoom(Room room)  throws ServiceException;

    List<Room> getRangeEntities(int startFrom, int finishWith) throws ServiceException;

    List<Room> getRangeAvailableRooms(int startFrom, int finishWith) throws ServiceException;

    void updateRoom(Room room) throws ServiceException;

    Optional<Room> findAvailableById(Long id) throws ServiceException;

    void setOccupied(Room room) throws ServiceException;

    Optional<Room> findById(Long id) throws ServiceException;

    int getRoomAmount() throws ServiceException;

    int getAvailableRoomAmount() throws ServiceException;

}
