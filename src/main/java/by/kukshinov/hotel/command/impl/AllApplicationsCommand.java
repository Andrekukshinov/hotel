package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.validators.PageValidator;
import by.kukshinov.hotel.validators.PageValidatorImpl;

import java.util.List;

public class AllApplicationsCommand implements Command {
    private static final String ALL_APPLICATIONS = "WEB-INF/view/userApplications.jsp";
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 7;
    private static final String PER_PAGE = "itemsPerPage";
    private static final String APPLICATIONS = "applications";

    private final ApplicationService applicationService;
    private final PageValidator validator;


    public AllApplicationsCommand(ApplicationService applicationService, PageValidatorImpl validator) {
        this.applicationService = applicationService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String currentPage = context.getRequestParameter(PAGE);
        int pageInt = validator.gatValidPage(currentPage);
        List<Application> applications = applicationService.getRangeEntities((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        context.setRequestAttribute(APPLICATIONS, applications);
        context.setRequestAttribute(PAGE, pageInt);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        return CommandResult.forward(ALL_APPLICATIONS);

    }
}
