package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import org.valid4j.Validation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {
    private static final String TOO_LATE_TO_APPROVE = "Too late to approve";
    private static final String WRONG_ROOM = "Wrong room";


    private final DaoHelperFactory helperFactory;


    public RoomServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }


    @Override
    public void switchRoomActivity(Room room) throws ServiceException {
        Boolean isAvailable = room.getIsAvailable();
        room.setIsAvailable(!isAvailable);
        saveAndUpdate(room);
    }

    @Override
    public void saveRoom(Room room) throws ServiceException {
        saveAndUpdate(room);
    }

    private void saveAndUpdate(Room room) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            roomDao.save(room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Room> findRangeEntities(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao dao = daoHelper.createRoomDao();
            return dao.findRangeRooms(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getRoomAmount() throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            return roomDao.getAllRoomAmount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Integer getAvailableRoomAmount(LocalDate arrivalDate, LocalDate leavingDate) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            return roomDao.getAvailableRoomAmountForPeriod(arrivalDate, leavingDate);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Room> findRangeAvailableRooms(LocalDate arrivalDate, LocalDate leavingDate, int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            return roomDao.findAvailableRooms(arrivalDate, leavingDate, startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Room findById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            Optional<Room> optionalRoom = roomDao.findById(id);
            return optionalRoom.orElseThrow(() -> new ServiceException(WRONG_ROOM));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Room findAvailableById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            Optional<Room> roomOptional = roomDao.findById(id);

            Room room = roomOptional.orElseThrow(() -> new ServiceException(WRONG_ROOM));
            Validation.validate(room.getIsAvailable(), new ServiceException(WRONG_ROOM));

            return room;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Room findDisabledById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            Optional<Room> roomOptional = roomDao.findById(id);
            Room room = roomOptional.orElseThrow(() -> new ServiceException(WRONG_ROOM));
            Validation.validate(!room.getIsAvailable(), new ServiceException(WRONG_ROOM));
            return room;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
