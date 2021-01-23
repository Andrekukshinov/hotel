package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.model.CommandResult;

public class LogoutCommand implements Command {
    private static final String DOMAIN_PAGE = "/hotel";
    private static final String INVALIDATE_SESSION = "invalidateSession";

    @Override
    public CommandResult execute(RequestContext context) {
        context.setSessionAttribute(INVALIDATE_SESSION, true);
        return CommandResult.redirect(DOMAIN_PAGE);
    }
}
