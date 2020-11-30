package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersCommand implements Command {
    private static final String ALL_USERS = "WEB-INF/view/allUsers.jsp";
    private static final int ITEMS_PER_PAGE = 7;

    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String currentPage = req.getParameter("page");
        String increasePage = req.getParameter("+");
        int pageInt = 0;
        if (currentPage == null) {
            pageInt = 1;
        } else if (increasePage != null) {
             pageInt = Integer.parseInt(currentPage) + 1;
        } else {
            pageInt = Integer.parseInt(currentPage) - 1;
        }
        List<User> users = userService.getRangeUsers(pageInt, ITEMS_PER_PAGE);
        req.setAttribute("users", users);
        req.setAttribute("page", pageInt);
        return CommandResult.forward(ALL_USERS);
    }


}
