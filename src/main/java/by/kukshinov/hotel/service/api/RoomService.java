package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    int getAvailableRoomAmount(LocalDate arrivalDate, LocalDate leavingDate) throws ServiceException;

    int getRoomAmount() throws ServiceException;

    List<Room> findRangeAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws ServiceException;

    Optional<Room> findById(Long id) throws ServiceException;

    List<Room> findRangeEntities(int startFrom, int finishWith) throws ServiceException;

    void saveRoom(Room room)  throws ServiceException;

    void updateRoom(Room room) throws ServiceException;

}
