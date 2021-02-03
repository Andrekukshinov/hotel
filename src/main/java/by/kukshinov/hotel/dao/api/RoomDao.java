package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *  Interface for managing room entities with different data sources
 */
public interface RoomDao extends Dao<Room> {

    /**
     * Method for extracting list of rooms that are available for certain period of time
     *
     * @param arrivalDate date to be available from(inclusive)
     * @param leavingDate date to be available until(inclusive)
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of available rooms
     * @throws DaoException in case of errors
     */
    List<Room> findAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws DaoException;

    /**
     * Method for extracting list of rooms
     *
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of rooms
     * @throws DaoException in case of errors
     */
    List<Room> findRangeRooms(int startFrom, int finishWith) throws DaoException;

    /**
     * Method for extracting total amount of rooms
     *
     * @return amount of rooms
     * @throws DaoException in case of errors
     */
    int getAllRoomAmount() throws DaoException;

    /**
     * Method for extracting total amount of rooms that are available for certain period of time
     *
     * @param arrivalDate date to be available from(inclusive)
     * @param leavingDate date to be available until(inclusive)
     * @return amount of available rooms
     * @throws DaoException in case of errors
     */
    int getAvailableRoomAmountForPeriod(LocalDate arrivalDate, LocalDate leavingDate) throws DaoException;

    /**
     * Method for getting room by its number
     * @param number to look room for with
     * @return Optioonal of room
     * @throws DaoException in case of errors
     */
    Optional<Room> findByRoomNumber(int number) throws DaoException;
}
