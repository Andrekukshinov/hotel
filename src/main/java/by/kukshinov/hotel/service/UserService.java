package by.kukshinov.hotel.service;

import by.kukshinov.hotel.dao.DaoException;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.exceptions.UserNotFoundException;
import by.kukshinov.hotel.model.User;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> findByCredentials(String login, String pass) throws ServiceException;
}
