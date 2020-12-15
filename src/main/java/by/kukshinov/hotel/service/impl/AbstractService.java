package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.AbstractDao;
import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.Dao;
import by.kukshinov.hotel.dao.api.RangeDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;

import java.util.List;

public abstract class AbstractService<T> {
    private final DaoHelperFactory helperFactory;

    public AbstractService(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    protected List<T> getRangeEntities(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()){
            RangeDao<T> dao = getDao(daoHelper);
            return dao.findRange(startFrom, finishWith);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    protected abstract RangeDao<T> getDao(DaoHelper daoHelper);
}
