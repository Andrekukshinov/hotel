package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDao extends Dao<Room> {
    List<Room> findAvailableRooms(int startFrom, int finishWith) throws DaoException;

    Optional<Room> findByOccupiedById(Long id)  throws DaoException;

    Optional<Room> findAvailableById(Long id)  throws DaoException;

    int getAllRoomAmount() throws DaoException;

    int getAllAvailableRoomAmount() throws DaoException;
}
