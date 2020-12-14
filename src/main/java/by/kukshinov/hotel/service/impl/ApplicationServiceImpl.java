package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    private final DaoHelperFactory helperFactory;

    public ApplicationServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public void save(Application application) throws ServiceException {
        try(DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
//
    @Override
    public List<Application> getRangeApplications(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findRangeApplications(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateApplication(Application application) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
