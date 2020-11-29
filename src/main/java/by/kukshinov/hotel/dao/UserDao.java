package by.kukshinov.hotel.dao;

import by.kukshinov.hotel.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User>{
    Optional<User> findByCredentials(String login, String pass) throws DaoException;
}
