package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.service.api.UserService;
import by.kukshinov.hotel.validators.PageValidator;
import by.kukshinov.hotel.validators.PageValidatorImpl;

import java.util.List;

public class AllUsersCommand implements Command {
    private static final String ALL_USERS = "WEB-INF/view/allUsers.jsp";
    private static final int ITEMS_PER_PAGE = 10;
    private static final String PAGE = "page";
    private static final String USERS = "users";
    private final UserService userService;
    private final PageValidator validator;

    public AllUsersCommand(UserService userService, PageValidatorImpl validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String currentPage = context.getRequestParameter(PAGE);
        int pageInt = validator.gatValidPage(currentPage);
        List<User> users = userService.getRangeEntities((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        context.setRequestAttribute(USERS, users);
        context.setRequestAttribute(PAGE, pageInt);
        return CommandResult.forward(ALL_USERS);
    }


}

