package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.AbstractDao;
import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RangeDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl extends AbstractService<Room> implements RoomService {
    private final DaoHelperFactory helperFactory;

    public RoomServiceImpl(DaoHelperFactory helperFactory) {
        super(helperFactory);
        this.helperFactory = helperFactory;
    }

    @Override
    protected RangeDao<Room> getDao(DaoHelper daoHelper) {
        return (RangeDao<Room>) daoHelper.createRoomDao();
    }

    @Override
    public void updateRoom(Room room) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            roomDao.save(room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Room> getRangeEntities(int startFrom, int finishWith) throws ServiceException {
        return super.getRangeEntities(startFrom, finishWith);
    }

    @Override
    public List<Room> getRangeAvailableRooms(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            return roomDao.findAvailableRooms(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Room> findByAvailableById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            return roomDao.findByAvailableById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
