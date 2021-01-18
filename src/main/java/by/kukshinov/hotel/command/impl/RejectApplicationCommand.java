package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

import java.util.Optional;

public class RejectApplicationCommand implements Command {
    private static final String APPLICATIONS = "/hotel/controller?command=admin_active_applications";
    private static final String ID = "id";
    private static final String ERROR_MESSAGE = "such application doesn't exist";
    private final ApplicationService service;


    public RejectApplicationCommand(ApplicationService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);
        long id = Long.parseLong(stringId);
        Optional<Application> optionalApplication = service.findInOrderApplicationById(id);
        Application userApplication = optionalApplication.orElseThrow(() -> new ServiceException(ERROR_MESSAGE));
        service.rejectApplication(userApplication);
        return CommandResult.redirect(APPLICATIONS);
    }
}
