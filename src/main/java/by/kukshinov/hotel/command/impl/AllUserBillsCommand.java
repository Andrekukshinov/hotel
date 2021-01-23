package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.util.PageHelper;

import java.util.List;

public class AllUserBillsCommand implements Command {
    private static final String USER_ID = "user_id";
    private static final String USER_BILLS = "WEB-INF/view/userBills.jsp";
    private static final String APPLICATIONS = "applications";
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 5;
    private static final String PER_PAGE = "itemsPerPage";
    private static final String LAST_PAGE = "lastPage";

    private final PageHelper validator;
    private final ApplicationService applicationService;

    public AllUserBillsCommand(ApplicationService applicationService, PageHelper validator) {
        this.applicationService = applicationService;
        this.validator = validator;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);

        int userApplicationsAmount = applicationService.findUserBillsAmount(userId);
        String currentPage = context.getRequestParameter(PAGE);
        int page = validator.getValidPage(currentPage, userApplicationsAmount, ITEMS_PER_PAGE);
        List<Application> allUserBills = applicationService.findRangeUserBills(userId, (page - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        int lastPage = validator.getLastPage(userApplicationsAmount, ITEMS_PER_PAGE);
        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(APPLICATIONS, allUserBills);
        context.setRequestAttribute(PAGE, page);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        return CommandResult.forward(USER_BILLS);
    }
}
