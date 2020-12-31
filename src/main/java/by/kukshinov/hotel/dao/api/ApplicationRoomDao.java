package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.ApplicationRoom;

import java.util.Optional;

public interface ApplicationRoomDao extends Dao<ApplicationRoom> {
    Optional<ApplicationRoom> findByApplicationId(Long id) throws DaoException;
}
