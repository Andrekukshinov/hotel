package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.model.CommandResult;

public class ForwardCommand implements Command {
    private final String pageUrl;

    public ForwardCommand(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public CommandResult execute(RequestContext context) {
        return CommandResult.forward(pageUrl);
    }
}
