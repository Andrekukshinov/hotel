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
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import org.valid4j.Validation;

import java.util.Optional;

public class ApplicationRoomServiceImpl implements ApplicationRoomService {
    private static final String WRONG_APPLICATION = "Wrong application";


    private final DaoHelperFactory helperFactory;

    public ApplicationRoomServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }


    @Override
    public ApplicationRoom findByApplicationId(Long applicationId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Application application = getApprovedApplication(applicationId, daoHelper);
            Long roomId = application.getRoomId();

            Room room = getRoom(daoHelper, roomId);

            return new ApplicationRoom(application, room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ApplicationRoom findUserBillByApplicationId(Long applicationId, Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Application application = getApprovedApplication(applicationId, daoHelper);
            Validation.validate(userId.equals(application.getUserId()), new ServiceException(WRONG_APPLICATION));

            Long roomId = application.getRoomId();
            Room room = getRoom(daoHelper, roomId);

            return new ApplicationRoom(application, room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    private Room getRoom(DaoHelper daoHelper, Long roomId) throws DaoException, ServiceException {
        RoomDao roomDao = daoHelper.createRoomDao();
        Optional<Room> optionalRoom = roomDao.findById(roomId);
        return optionalRoom.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
    }

    private Application getApprovedApplication(Long applicationId, DaoHelper daoHelper) throws DaoException, ServiceException {
        ApplicationDao applicationDao = daoHelper.createApplicationDao();
        Optional<Application> approvedById = applicationDao.findById(applicationId);

        Application application = approvedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
        boolean isApproved = application.getStatus().equals(ApplicationStatus.APPROVED);
        Validation.validate(isApproved, new ServiceException(WRONG_APPLICATION));
        return application;
    }

}
