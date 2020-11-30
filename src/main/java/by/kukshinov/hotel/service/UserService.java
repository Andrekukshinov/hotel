package by.kukshinov.hotel.service;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers() throws ServiceException;
    List<User> getRangeUsers(int startFrom, int finishWith) throws ServiceException;
    Optional<User> findByCredentials(String login, String pass) throws ServiceException;
}
