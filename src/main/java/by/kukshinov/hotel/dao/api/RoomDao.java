package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomDao extends Dao<Room> {

    List<Room> findAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws DaoException;

    List<Room> findRangeRooms(int startFrom, int finishWith) throws DaoException;

    Optional<Room> findAvailableById(Long id)  throws DaoException;

    int getAllRoomAmount() throws DaoException;

    int getAvailableRoomAmountForPeriod(LocalDate arrivalDate, LocalDate leavingDate) throws DaoException;
}
