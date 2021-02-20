package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.User;

import java.util.List;
import java.util.Optional;
/**
 * Interface for serving user entities according to the business logics of application
 */
public interface UserService {
    /**
     * This is the method for getting a part of users whose status is not admin
     * @param startFrom index to start looking for
     * @param finishWith index of the last app to be found
     * @return List users (without admins)
     * @throws ServiceException when business logics errors occur
     */
    List<User> getRangeCustomers(int startFrom, int finishWith) throws ServiceException;

    /**
     * This method is used for user authentication
     * @param login user login
     * @param password user password
     * @return Optional of user if found
     * @throws ServiceException when business logics errors occur
     */
    Optional<User> findByCredentials(String login, String password) throws ServiceException;

    /**
     * This method is used for finding user by id
     * @param id to look for the user
     * @return Optional of user
     * @throws ServiceException if user is not found or when business logics errors occur
     */
    User findCustomerById(Long id) throws ServiceException;

    /**
     * This method is used to update user
     * @param user to be updated
     * @throws ServiceException when business logics errors occur
     */
    void updateCustomer(User user) throws ServiceException;

    /**
     * Method for changing user status on opposite one if received status differs from source object status
     * @param userId to change status
     * @param isDisabled status fro updating
     * @throws ServiceException when business logics errors occur
     */
    void switchUserStatus(Long userId, boolean isDisabled) throws ServiceException;

    /**
     * Method for getting total amount of enabled customers
     * @return total enabled users amount
     * @throws ServiceException when business logics errors occur
     */
    int getCustomersAmount() throws ServiceException;
}
