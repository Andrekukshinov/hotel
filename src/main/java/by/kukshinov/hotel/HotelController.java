package by.kukshinov.hotel;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.command.CommandFactory;
import by.kukshinov.hotel.connection.ConnectionPool;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.context.RequestContextHelper;
import by.kukshinov.hotel.exceptions.NoSuchCommandException;
import by.kukshinov.hotel.model.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HotelController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(HotelController.class);
    private static final String COMMAND = "command";
    private static final int ERROR = 500;
    private static final int NOT_FOUND = 404;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestContextHelper contextManager = new RequestContextHelper();
        RequestContext requestContext = contextManager.create(req);
        try {
            String commandParam = req.getParameter(COMMAND);
            Command command = CommandFactory.createCommand(commandParam);
            CommandResult commandResult = command.execute(requestContext);
            contextManager.updateRequest(req, requestContext);
            dispatchRequest(req, resp, commandResult);
        } catch (NoSuchCommandException e) {
            LOGGER.error(e.getMessage(), e);
            resp.sendError(NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            resp.sendError(ERROR);
        }
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp, CommandResult commandResult) throws ServletException, IOException {
        boolean redirect = commandResult.isRedirect();
        String urlToGoTo = commandResult.getPageUrl();
        if (redirect) {
            resp.sendRedirect(urlToGoTo);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(urlToGoTo);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool instance = ConnectionPool.getInstance();
            instance.killConnections();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        super.destroy();
    }
}
