package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for serving room entities from different data sources
 */
public interface RoomService {

    /**
     * Method is used for getting total amount of rooms for the certain period
     * @param arrivalDate date when room must be available from (inclusive)
     * @param leavingDate date when room must be available until (inclusive)
     * @return amount of available rooms
     * @throws ServiceException when business logics errors occur
     */
    int getAvailableRoomAmount(LocalDate arrivalDate, LocalDate leavingDate) throws ServiceException;

    /**
     * Method is used for getting total amount of rooms
     * @return amount of rooms
     * @throws ServiceException when business logics errors occur
     */
    int getRoomAmount() throws ServiceException;

    /**
     * Method for getting a part of rooms from the data source
     * @param startFrom index to start looking for with
     * @param finishWith index of the last room to be found
     * @return List of rooms
     * @throws ServiceException when business logics errors occur
     */
    List<Room> findRangeEntities(int startFrom, int finishWith) throws ServiceException;

    /**
     * Method for getting a part of available rooms for the certain period of time from the data source
     * @param arrivalDate date when room must be available from (inclusive)
     * @param leavingDate date when room must be available until (inclusive)
     * @param startFrom index to start looking for with
     * @param finishWith index of the last room to be found
     * @return List of available rooms
     * @throws ServiceException when business logics errors occur
     */
    List<Room> findRangeAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws ServiceException;

    /**
     * Method for finding room with available status
     * @param id room id to look for with
     * @return room
     * @throws ServiceException when business logics errors occur
     */
    Room findAvailableById(Long id) throws ServiceException;

    /**
     * Method for finding room with disabled status
     * @param id room id to look for with
     * @return room
     * @throws ServiceException when business logics errors occur
     */
    Room findDisabledById(Long id) throws ServiceException;

    /**
     * Method for saving room entity to the specified data source
     * @param room to be saved
     * @throws ServiceException when business logics errors occur
     */
    void saveRoom(Room room) throws ServiceException;

    /**
     * Method for changing room status on opposite one
     * @param roomId of the room status to be changed
     * @throws ServiceException when business logics errors occur
     */
    void switchRoomActivity(Long roomId) throws ServiceException;

}
