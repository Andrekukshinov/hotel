package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getRangeUsers(int startFrom, int finishWith) throws ServiceException;

    Optional<User> findByCredentials(String login, String pass) throws ServiceException;

    Optional<User> findById(Long id) throws ServiceException;

    void updateUser(User user) throws ServiceException;

}
