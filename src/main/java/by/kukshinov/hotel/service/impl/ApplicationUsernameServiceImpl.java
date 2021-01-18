package by.kukshinov.hotel.service.impl;

import by.kukshinov.hotel.dao.DaoHelper;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.dao.api.ApplicationDao;
import by.kukshinov.hotel.dao.api.UserDao;
import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationUsernameDto;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.api.ApplicationUsernameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationUsernameServiceImpl implements ApplicationUsernameService {
    private static final String WRONG_APPLIATION = "Wrong appliation";
    private final DaoHelperFactory helperFactory;

    public ApplicationUsernameServiceImpl(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

    @Override
    public List<ApplicationUsernameDto> findRange(int startFrom, int finishWith) throws ServiceException {
        try (DaoHelper daoHelper = helperFactory.createDaoHelper()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            UserDao userDao = daoHelper.createUserDao();

            List<Application> applications = dao.findRangeApplications(startFrom, finishWith);
            List<ApplicationUsernameDto> result = new ArrayList<>();

            for (Application application: applications) {
                Long userId = application.getUserId();
                Optional<User> userOptional = userDao.findById(userId);
                User user = userOptional.orElseThrow(() -> new ServiceException(WRONG_APPLIATION));
                String login = user.getLogin();
                result.add(new ApplicationUsernameDto(application, login));
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
