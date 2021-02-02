package by.kukshinov.hotel.dao.api;

import by.kukshinov.hotel.exceptions.DaoException;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;

/**
 *   Interface for managing user entities with different data sources
 */
public interface UserDao extends Dao<User> {

    /**
     * Method for authenticating user by login and password
     * @param login user login
     * @param password user password
     * @return optional of user
     * @throws DaoException in case of errors
     */
    Optional<User> findByCredentials(String login, String password) throws DaoException;

    /**
     * Method for extracting list of users
     *
     * @param startFrom  index to start looking for with(inclusive)
     * @param finishWith index to finish looking for with(inclusive)
     * @return list of users
     * @throws DaoException in case of errors
     */
    List<User> findRangeUsers(int startFrom, int finishWith) throws DaoException;

    /**
     * Method for extracting total amount of users
     *
     * @return amount of users
     * @throws DaoException in case of errors
     */
    int getAllUsersAmount() throws DaoException;
}
