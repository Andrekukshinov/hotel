package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {
    private final DaoHelperFactory helperFactory;

    public RoomServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public void updateRoom(Room room) throws ServiceException {
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
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
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
    public int getAvailableRoomAmount(LocalDate arrivalDate, LocalDate leavingDate) throws ServiceException {
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
    public Optional<Room> findById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            return roomDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
