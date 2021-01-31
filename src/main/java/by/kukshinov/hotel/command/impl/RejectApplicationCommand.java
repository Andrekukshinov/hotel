package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;

public class RejectApplicationCommand implements Command {
    private static final String APPLICATIONS = "/hotel/controller?command=admin_active_applications";
    private static final String ID = "id";

    private final ApplicationService service;

    public RejectApplicationCommand(ApplicationService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);
        Long id = Long.parseLong(stringId);
        Application userApplication = service.findInOrderApplicationById(id);
        service.adminDenyOrderedApplication(userApplication);
        return CommandResult.redirect(APPLICATIONS);
    }
}
