package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    Integer getAvailableRoomAmount(LocalDate arrivalDate, LocalDate leavingDate) throws ServiceException;

    int getRoomAmount() throws ServiceException;

    List<Room> findRangeEntities(int startFrom, int finishWith) throws ServiceException;

    List<Room> findRangeAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws ServiceException;

    Room findById(Long id) throws ServiceException;

    Room findAvailableById(Long id) throws ServiceException;

    Room findDisabledById(Long id) throws ServiceException;

    void saveRoom(Room room) throws ServiceException;

    void switchRoomActivity(Room room) throws ServiceException;

}
