package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.util.PageHelper;

import java.util.List;

public class HistoryCommand implements Command {
    private static final String USER_ID = "user_id";
    private static final String PROFILE_HISTORY = "WEB-INF/view/profileHistory.jsp";
    private static final String APPLICATIONS = "applications";
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 5;
    private static final String PER_PAGE = "itemsPerPage";
    private static final String LAST_PAGE = "lastPage";

    private final ApplicationService applicationService;
    private final PageHelper validator;

    public HistoryCommand(ApplicationService applicationService, PageHelper validator) {
        this.applicationService = applicationService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String currentPage = context.getRequestParameter(PAGE);

        int userApplicationsAmount = applicationService.findUserApplicationsAmount(userId);
        int page = validator.getValidPage(currentPage, userApplicationsAmount, ITEMS_PER_PAGE);
        int startFrom = (page - 1) * ITEMS_PER_PAGE;
        List<Application> allUserApplications = applicationService.findRangeUserApplications(userId, startFrom, ITEMS_PER_PAGE);
        int lastPage = validator.getLastPage(userApplicationsAmount, ITEMS_PER_PAGE);
        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(APPLICATIONS, allUserApplications);
        context.setRequestAttribute(PAGE, page);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        return CommandResult.forward(PROFILE_HISTORY);
    }
}
