package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.validators.PageValidator;

import java.util.List;

public class HistoryCommand implements Command {
    private static final String USER_ID = "user_id";
    private static final String PROFILE_HISTORY = "WEB-INF/view/profileHistory.jsp";
    private static final String APPLICATIONS = "applications";
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 5;
    private static final String PER_PAGE = "itemsPerPage";

    private final ApplicationService applicationService;
    private final PageValidator validator;

    public HistoryCommand(ApplicationService applicationService, PageValidator validator) {
        this.applicationService = applicationService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String pageString = context.getRequestParameter(PAGE);
        int page = validator.gatValidPage(pageString);
        List<Application> allUserApplications = applicationService.getRangeInOrderApplications(userId, (page - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        context.setRequestAttribute(APPLICATIONS, allUserApplications);
        context.setRequestAttribute(PAGE, page);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        return CommandResult.forward(PROFILE_HISTORY);
    }
}
