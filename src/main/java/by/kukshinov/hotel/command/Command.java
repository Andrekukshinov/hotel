package by.kukshinov.hotel.command;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.request.context.RequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    // TODO: 04.12.2020 change signature abd write tests
    CommandResult execute(RequestContext context) throws ServletException, ServiceException;
}
