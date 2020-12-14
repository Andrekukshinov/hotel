package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.Room;

import java.util.List;

public interface RoomDao extends Dao<Room> {
    List<Room> findRangeUsers(int startFrom, int finishWith) throws DaoException;
}
