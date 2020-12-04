package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.request.context.RequestContext;
import by.kukshinov.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class UsersCommand implements Command {
    private static final String ALL_USERS = "WEB-INF/view/allUsers.jsp";
    private static final int ITEMS_PER_PAGE = 7;
    private static final String PAGE = "page";
    private static final String USERS = "users";
    private static final int FIRST_PAGE = 1;

    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Map<String, String> requestParameters = context.getRequestParameters();
        Map<String, Object> requestAttributes = context.getRequestAttributes();
        String currentPage = requestParameters.get(PAGE);
        int pageInt;
        if (currentPage == null) {
            pageInt = FIRST_PAGE;
        } else {
            pageInt = Integer.parseInt(currentPage);
        }
        List<User> users = userService.getRangeUsers((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        requestAttributes.put(USERS, users);
        requestAttributes.put(PAGE, pageInt);
        return CommandResult.forward(ALL_USERS);
    }


}
