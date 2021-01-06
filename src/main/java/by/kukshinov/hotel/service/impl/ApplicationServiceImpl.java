package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;

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
