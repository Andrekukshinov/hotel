package by.kukshinov.hotel.command;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.context.RequestContext;

import javax.servlet.ServletException;

public interface Command {
    // TODO: 04.12.2020 change signature abd write tests
    CommandResult execute(RequestContext context) throws ServletException, ServiceException;
}
