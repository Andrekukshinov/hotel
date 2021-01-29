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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {


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
    public void approveApplication(Application application, Room room) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();

            BigDecimal totalPrice = getTotalPrice(application, room);
            Long roomId = room.getId();

            application.setStatus(ApplicationStatus.APPROVED);
            application.setRoomId(roomId);
            application.setTotalPrice(totalPrice);

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
    public List<Application> findRangeUserApplications(long userId, int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserApplications(userId, startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Application> findRangeUserBills(long userId, int startFrom, int finishWith) throws ServiceException {
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
    public int findUserApplicationsAmount(long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.findUserApplicationsAmount(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int findUserBillsAmount(long userId) throws ServiceException {
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

    @Override
    public Optional<Application> findInOrderApplicationById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findQueuedById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Application> findInOrderUserApplicationById(Long appId, Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findUserQueuedById(appId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Application> findApprovedUserApplicationById(Long id, Long userId) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findUserApprovedAppById(id, userId);
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

    @Override
    public void adminDenyOrderedApplication(Application application) throws ServiceException {
        updateApplicationStatus(application, ApplicationStatus.DENIED);
    }

    @Override
    public void userRejectApprovedApplication(Application application) throws ServiceException {
        application.setRoomId(null);
        application.setTotalPrice(null);
        updateApplicationStatus(application, ApplicationStatus.USER_REJECTED);
    }

    @Override
    public void userCancelOrderedApplication(Application application) throws ServiceException {
        application.setRoomId(null);
        application.setTotalPrice(null);
        updateApplicationStatus(application, ApplicationStatus.CANCELLED);
    }
}
