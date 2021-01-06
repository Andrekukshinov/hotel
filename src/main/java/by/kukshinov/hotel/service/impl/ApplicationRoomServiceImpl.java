package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.ApplicationRoomDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.model.enums.RoomStatus;
import by.kukshinov.hotel.service.api.ApplicationRoomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class ApplicationRoomServiceImpl implements ApplicationRoomService {
    private static final String NOT_APPROVED = "Such application is not approved!";
    private static final String WRONG_APPLICATION = "Wrong application";
    private static final String WRONG_ROOM = "Wrong room";
    private final DaoHelperFactory helperFactory;

    public ApplicationRoomServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public void createApplicationRoom(Long applicationId, Long roomId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            ApplicationRoomDao applicationRoomDao = daoHelper.createApplicationRoomDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<Application> queuedById = applicationDao.findQueuedById(applicationId);
            Optional<Room> availableById = roomDao.findAvailableById(roomId);

            Application application = queuedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            Room room = availableById.orElseThrow(() -> new ServiceException(WRONG_ROOM));
            application.setStatus(ApplicationStatus.APPROVED);
            room.setRoomStatus(RoomStatus.OCCUPIED);

            ApplicationRoom applicationRoom = getApplicationRoom(application, room);

            daoHelper.startTransaction();
            applicationDao.save(application);
            roomDao.save(room);
            applicationRoomDao.save(applicationRoom);
            daoHelper.commit();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private ApplicationRoom getApplicationRoom(Application application, Room room) {
        BigDecimal roomPrice = room.getPrice();
        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate leavingDate = application.getLeavingDate();
        long period = arrivalDate.until(leavingDate, ChronoUnit.DAYS);
        BigDecimal totalPrice = roomPrice.multiply(new BigDecimal(period));
        return new ApplicationRoom(application, room, totalPrice);
    }

    @Override
    public Optional<ApplicationRoom> getApplicationRoomByAppId(long applicationId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationRoomDao applicationRoomDao = daoHelper.createApplicationRoomDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<Application> optionalApplication = applicationDao.findById(applicationId);
            Application application = optionalApplication.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            ApplicationStatus applicationStatus = application.getStatus();
            if (!applicationStatus.equals(ApplicationStatus.APPROVED)) {
                throw new ServiceException(NOT_APPROVED);
            }
            return applicationRoomDao.findByApplicationId(applicationId);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean removeApplicationRoomByApplicationId(long applicationId, long roomId, long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            ApplicationRoomDao applicationRoomDao = daoHelper.createApplicationRoomDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            RoomDao roomDao = daoHelper.createRoomDao();

            Optional<Application> approvedById = applicationDao.findApprovedById(applicationId);
            Optional<ApplicationRoom> applicationRoomOptional = applicationRoomDao.findByApplicationId(applicationId);
            Optional<Room> occupiedById = roomDao.findByOccupiedById(roomId);
            Room room = occupiedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            Application application = approvedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            ApplicationRoom applicationRoom = applicationRoomOptional.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));

            room.setRoomStatus(RoomStatus.AVAILABLE);
            application.setStatus(ApplicationStatus.DENIED);
            long applicationUserId = application.getUserId();
            LocalDate arrivalDate = application.getArrivalDate();
            LocalDate now = LocalDate.now();
            if (applicationUserId == userId && now.isBefore(arrivalDate)) {
                daoHelper.startTransaction();
                applicationDao.save(application);
                roomDao.save(room);
                applicationRoomDao.delete(applicationRoom);
                daoHelper.commit();
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }
}


