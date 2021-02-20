package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RoomDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import org.valid4j.Validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {
    private static final String TOO_LATE_TO_APPROVE = "Too late to approve";

    private static final String WRONG_APPLICATION = "wrong application!";
    private static final String WRONG_ROOM = "wrong room!";

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

    private BigDecimal getTotalPrice(Application application, Room room) {
        BigDecimal roomPrice = room.getPrice();
        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate leavingDate = application.getLeavingDate();
        long period = arrivalDate.until(leavingDate, ChronoUnit.DAYS);
        BigDecimal priceFromSecondDay = roomPrice.multiply(new BigDecimal(period));
        return priceFromSecondDay.add(roomPrice);
    }

    @Override
    public List<Application> findRangeOrderedEntities(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findAllOrderedApplications(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Application> findRangeUserApplications(Long userId, int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserApplications(userId, startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Application> findRangeUserBills(Long userId, int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserBills(userId, startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int findInOrderApplicationsAmount() throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.getInOrderApplicationsAmount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int findUserApplicationsAmount(Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserApplicationsAmount(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int findUserBillsAmount(Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserBillsAmount(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int findAllApplicationsAmount() throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.getAllApplicationsAmount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private Application getApplicationById(Long applicationId, DaoHelper daoHelper) throws ServiceException {
        try {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<Application> optionalApplication = applicationDao.findById(applicationId);
            return optionalApplication.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Application findFutureArrivalInOrderApplicationById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Application application = getApplicationById(id, daoHelper);

            isInOrder(application.getStatus());
            isDateValid(application.getArrivalDate(), application.getLeavingDate());

            return application;
        }
    }

    private Application findInOrderUserApplicationById(Long appId, Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Application application = getApplicationById(appId, daoHelper);

            boolean isInOrder = application.getStatus().equals(ApplicationStatus.IN_ORDER);
            boolean isUserApplication = userId.equals(application.getUserId());
            Validation.validate(isInOrder && isUserApplication, new ServiceException(WRONG_APPLICATION));

            return application;
        }
    }

    private void updateApplicationStatus(Application application, ApplicationStatus status, DaoHelper daoHelper) throws ServiceException {
        application.setStatus(status);
        try {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void adminDenyOrderedApplication(Long applicationId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Application application = getApplicationById(applicationId, daoHelper);
            isInOrder(application.getStatus());
            updateApplicationStatus(application, ApplicationStatus.DENIED, daoHelper);
        }
    }

    @Override
    public void approveApplication(Long applicationId, Long roomId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Room room = getRoom(roomId, daoHelper);

            Application application = getApplicationById(applicationId, daoHelper);
            isDateValid(application.getArrivalDate(), application.getLeavingDate());
            isInOrder(application.getStatus());

            BigDecimal totalPrice = getTotalPrice(application, room);
            application.setRoomId(roomId);
            application.setTotalPrice(totalPrice);

            updateApplicationStatus(application, ApplicationStatus.APPROVED, daoHelper);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private Room getRoom(Long roomId, DaoHelper daoHelper) throws DaoException, ServiceException {
        RoomDao roomDao = daoHelper.createRoomDao();
        Optional<Room> optionalRoom = roomDao.findById(roomId);
        Room room = optionalRoom.orElseThrow(() -> new ServiceException(WRONG_ROOM));
        Validation.validate(room.getIsAvailable(), new ServiceException(WRONG_ROOM));
        return room;
    }

    @Override
    public void userRejectApprovedApplication(Long applicationId, Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            Application application = findApprovedUserApplicationById(applicationId, userId, daoHelper);

            isDateValid(application.getArrivalDate(), application.getLeavingDate());

            application.setRoomId(null);
            application.setTotalPrice(null);

            updateApplicationStatus(application, ApplicationStatus.USER_REJECTED, daoHelper);
        }
    }

    private Application findApprovedUserApplicationById(Long id, Long userId, DaoHelper daoHelper) throws ServiceException {
        Application application = getApplicationById(id, daoHelper);

        boolean isApproved = application.getStatus().equals(ApplicationStatus.APPROVED);
        boolean isUserApplication = userId.equals(application.getUserId());
        Validation.validate(isApproved && isUserApplication, new ServiceException(WRONG_APPLICATION));

        return application;
    }

    @Override
    public void userCancelOrderedApplication(Long applicationId, Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {

            Application application = findInOrderUserApplicationById(applicationId, userId);
            isInOrder(application.getStatus());
            application.setRoomId(null);
            application.setTotalPrice(null);
            updateApplicationStatus(application, ApplicationStatus.CANCELLED, daoHelper);
        }
    }

    private void isDateValid(LocalDate arrivalDate, LocalDate leavingDate) throws ServiceException {
        LocalDate now = LocalDate.now();
        if (!now.isBefore(arrivalDate) || leavingDate.isBefore(arrivalDate)) {
            throw new ServiceException(TOO_LATE_TO_APPROVE);
        }
    }

    private void isInOrder(ApplicationStatus applicationStatus) throws ServiceException {
        boolean isInOrder = applicationStatus.equals(ApplicationStatus.IN_ORDER);
        Validation.validate(isInOrder, new ServiceException(WRONG_APPLICATION));
    }
}
