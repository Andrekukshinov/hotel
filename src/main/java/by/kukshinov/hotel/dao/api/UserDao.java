package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByCredentials(String login, String pass) throws DaoException;

    List<User> findRangeUsers(int startFrom, int finishWith) throws DaoException;

    int getAllUsersAmount() throws DaoException;
}
