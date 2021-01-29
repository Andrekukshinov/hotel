package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.api.UserService;
import by.kukshinov.hotel.util.PageHelper;

import java.util.List;

public class AllUsersCommand implements Command {
    private static final String PER_PAGE = "itemsPerPage";
    private static final String ALL_USERS = "WEB-INF/view/allUsers.jsp";
    private static final int ITEMS_PER_PAGE = 10;
    private static final String PAGE = "page";
    private static final String USERS = "users";
    private static final String LAST_PAGE = "lastPage";

    private final UserService userService;
    private final PageHelper validator;

    public AllUsersCommand(UserService userService, PageHelper validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        int usersAmount = userService.getUsersAmount();
        String currentPage = context.getRequestParameter(PAGE);
        int pageInt = validator.getValidPage(currentPage, usersAmount, ITEMS_PER_PAGE);
        List<User> users = userService.getRangeUsers((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        int lastPage = validator.getLastPage(usersAmount, ITEMS_PER_PAGE);
        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(USERS, users);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        context.setRequestAttribute(PAGE, pageInt);
        return CommandResult.forward(ALL_USERS);
    }


}

