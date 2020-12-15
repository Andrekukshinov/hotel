package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.ApplicationRoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.service.api.ApplicationRoomService;

public class ApplicationRoomServiceImpl implements ApplicationRoomService {
    private final DaoHelperFactory helperFactory;

    public ApplicationRoomServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public void saveApplicationRoom(ApplicationRoom applicationRoom) throws ServiceException {
        try(DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationRoomDao applicationDao = daoHelper.createApplicationRoomDao();
            applicationDao.save(applicationRoom);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
