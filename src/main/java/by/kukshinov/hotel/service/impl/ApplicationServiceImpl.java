package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.AbstractDao;
import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.RangeDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl extends AbstractService<Application> implements ApplicationService {
    private final DaoHelperFactory helperFactory;

    public ApplicationServiceImpl(DaoHelperFactory helperFactory) {
        super(helperFactory);
        this.helperFactory = helperFactory;
    }

    @Override
    protected RangeDao<Application> getDao(DaoHelper daoHelper) {
       return (RangeDao<Application>) daoHelper.createApplicationDao();
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

    @Override
    public List<Application> getRangeEntities(int startFrom, int finishWith) throws ServiceException {
        return super.getRangeEntities(startFrom, finishWith);
    }
//

    @Override
    public void updateApplication(Application application) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            applicationDao.save(application);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Application> findQueuedApplicationById(long id) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            return applicationDao.findQueuedById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
