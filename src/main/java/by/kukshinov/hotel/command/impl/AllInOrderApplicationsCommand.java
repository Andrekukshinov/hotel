package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.util.PageHelper;

import java.time.LocalDate;
import java.util.List;

public class AllInOrderApplicationsCommand implements Command {
    private static final String ALL_APPLICATIONS = "WEB-INF/view/userApplications.jsp";
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 7;
    private static final String PER_PAGE = "itemsPerPage";
    private static final String APPLICATIONS = "applications";
    private static final String LAST_PAGE = "lastPage";
    private static final String NOW = "now";

    private final ApplicationService applicationService;
    private final PageHelper validator;


    public AllInOrderApplicationsCommand(ApplicationService applicationService, PageHelper validator) {
        this.applicationService = applicationService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        int orderedApplicationsAmount = applicationService.findInOrderApplicationsAmount();
        String currentPage = context.getRequestParameter(PAGE);
        int pageInt = validator.getValidPage(currentPage, orderedApplicationsAmount, ITEMS_PER_PAGE);
        int startFrom = (pageInt - 1) * ITEMS_PER_PAGE;
        List<Application> applications = applicationService.findRangeOrderedEntities(startFrom, ITEMS_PER_PAGE);
        int lastPage = validator.getLastPage(orderedApplicationsAmount, ITEMS_PER_PAGE);
        LocalDate now = LocalDate.now();

        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(NOW, now);
        context.setRequestAttribute(APPLICATIONS, applications);
        context.setRequestAttribute(PAGE, pageInt);
        context.setRequestAttribute(PER_PAGE, ITEMS_PER_PAGE);
        return CommandResult.forward(ALL_APPLICATIONS);
    }

}
