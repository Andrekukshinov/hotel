package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.util.Optional;

public class UserCancelCommand implements Command {
    private static final String USER_ID = "user_id";
    private static final String APPLICATION_ID = "id";
    private static final String PROFILE_HISTORY = "controller?command=profileHistory";
    private static final String PROFILE_HISTORY_PAGE = "controller?command=profileHistory&page=";
    private static final String WRONG_APPLICATION = "wrong application!";
    private static final String PAGE = "page";
    private final ApplicationService service;

    public UserCancelCommand(ApplicationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        Long userId = (Long) context.getSessionAttribute(USER_ID);
        String page = context.getRequestParameter(PAGE);
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);

        long applicationId = Long.parseLong(applicationIdString);
        Optional<Application> applicationOptional = service.findInOrderUserApplicationById(applicationId, userId);
        Application application = applicationOptional.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));

        service.userCancelOrderedApplication(application);
        if (page == null) {
            return CommandResult.redirect(PROFILE_HISTORY);
        } else {
            return CommandResult.redirect(PROFILE_HISTORY_PAGE + page);
        }
    }
}
