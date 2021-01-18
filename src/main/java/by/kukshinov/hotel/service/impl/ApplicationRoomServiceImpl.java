package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.ApplicationRoomService;

import java.util.Optional;

public class ApplicationRoomServiceImpl implements ApplicationRoomService {
    private static final String WRONG_APPLICATION = "Wrong application";


    private final DaoHelperFactory helperFactory;

    public ApplicationRoomServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }


    @Override
    public ApplicationRoom findApplicationRoom(Long applicationId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            RoomDao roomDao = daoHelper.createRoomDao();

            Optional<Application> approvedById = applicationDao.findApprovedAppById(applicationId);
            Application application = approvedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            Long roomId = application.getRoomId();

            Optional<Room> optionalRoom = roomDao.findById(roomId);
            Room room = optionalRoom.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));

            return new ApplicationRoom(application, room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
