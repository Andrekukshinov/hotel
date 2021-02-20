package by.kukshinov.hotel.command;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;

/**
 * This interface is specified to convert request data to a specified action to be performed
 */
public interface Command {
    /**
     * Accepts request data to handle that and perform specified activity with the data
     * and lastly returns command result that contains instructions where and how to go to(for server)
     *
     * @param context is a request data storage (session, request attributes, request params)
     * @return CommandResult - an object that contains page path and forward or redirect info
     * @throws ServiceException exception is thrown when business logics errors take place
     *                          also wraps exceptions from lower layers of application
     */
    CommandResult execute(RequestContext context) throws ServiceException;
}
