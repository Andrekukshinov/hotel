package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationUsernameDto;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.ApplicationUsernameService;
import by.kukshinov.hotel.validators.PageValidator;

import java.util.List;

public class AllApplicationsCommand implements Command {
    private static final String ALL_APPLICATIONS = "WEB-INF/view/AllApplications.jsp";
    private static final String APPLICATIONS = "applications";
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 5;
    private static final String PER_PAGE = "itemsPerPage";
    private static final String LAST_PAGE = "lastPage";


    private final ApplicationService applicationService;
    private final ApplicationUsernameService applicationUsernameService;
    private final PageValidator validator;

    public AllApplicationsCommand(ApplicationService applicationService, ApplicationUsernameService applicationUsernameService, PageValidator validator) {
        this.applicationService = applicationService;
        this.applicationUsernameService = applicationUsernameService;
        this.validator = validator;
    }
    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        int userApplicationsAmount = applicationService.findAllApplicationsAmount();
        String currentPage = context.getRequestParameter(PAGE);
        int page = validator.getValidPage(currentPage, userApplicationsAmount, ITEMS_PER_PAGE);

        List<ApplicationUsernameDto> allUserApplications = applicationUsernameService.findRange((page - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);

        int lastPage = validator.getLastPage(userApplicationsAmount, ITEMS_PER_PAGE);

        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(APPLICATIONS, allUserApplications);
        context.setRequestAttribute(PAGE, page);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        return CommandResult.forward(ALL_APPLICATIONS);
    }
}
