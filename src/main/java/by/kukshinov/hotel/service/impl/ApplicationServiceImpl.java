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
import by.kukshinov.hotel.model.enums.RoomStatus;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {
    private static final String WRONG_APPLICATION = "Wrong application";
    private static final String WRONG_ROOM = "Wrong room";


    private final DaoHelperFactory helperFactory;

    public ApplicationServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public void save(Application application) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    public boolean userRejectApplication(long applicationId, long roomId, long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            RoomDao roomDao = daoHelper.createRoomDao();

            Optional<Application> approvedById = applicationDao.findApprovedById(applicationId);
            Optional<Room> occupiedById = roomDao.findByOccupiedById(roomId);
            Room room = occupiedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            Application application = approvedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));

            application.setRoomId(null);
            application.setTotalPrice(null);
            room.setRoomStatus(RoomStatus.AVAILABLE);
            application.setStatus(ApplicationStatus.DENIED);
            long applicationUserId = application.getUserId();
            LocalDate arrivalDate = application.getArrivalDate();
            LocalDate now = LocalDate.now();
            if (applicationUserId == userId && now.isBefore(arrivalDate)) {
                daoHelper.startTransaction();
                applicationDao.save(application);
                roomDao.save(room);
                daoHelper.commit();
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void approveApplication(Long applicationId, Long roomId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<Application> queuedById = applicationDao.findQueuedById(applicationId);
            Optional<Room> availableById = roomDao.findAvailableById(roomId);

            Application application = queuedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            Room room = availableById.orElseThrow(() -> new ServiceException(WRONG_ROOM));
            application.setStatus(ApplicationStatus.APPROVED);
            room.setRoomStatus(RoomStatus.OCCUPIED);
            application.setRoomId(roomId);

            BigDecimal totalPrice = getTotalPrice(application, room);

            application.setTotalPrice(totalPrice);

            daoHelper.startTransaction();
            applicationDao.save(application);
            roomDao.save(room);
            daoHelper.commit();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private BigDecimal getTotalPrice(Application application, Room room) {
        BigDecimal roomPrice = room.getPrice();
        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate leavingDate = application.getLeavingDate();
        long period = arrivalDate.until(leavingDate, ChronoUnit.DAYS);
        return roomPrice.multiply(new BigDecimal(period));
    }


    @Override
    public ApplicationRoom getApprovedApplication(Long applicationId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            RoomDao roomDao = daoHelper.createRoomDao();

            Optional<Application> approvedById = applicationDao.findApprovedById(applicationId);
            Application application = approvedById.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
            Long roomId = application.getRoomId();

            Optional<Room> optionalRoom = roomDao.findByOccupiedById(roomId);
            Room room = optionalRoom.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));

            return new ApplicationRoom(application, room);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Application> getRangeEntities(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findAllOrderedApplications(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Application> getRangeUserApplications(long userId, int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserApplications(userId, startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getInOrderApplicationsAmount() throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.getInOrderApplicationsAmount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getUserApplicationsAmount(long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.getUserApplicationsAmount(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void rejectApplication(Application application) throws ServiceException {
        application.setRoomId(null);
        updateApplicationStatus(application, ApplicationStatus.DENIED);
    }

    @Override
    public Optional<Application> findInOrderApplicationById(long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findQueuedById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void updateApplicationStatus(Application application, ApplicationStatus status) throws ServiceException {
        application.setStatus(status);
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
