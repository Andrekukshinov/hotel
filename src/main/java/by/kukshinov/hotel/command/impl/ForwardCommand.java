package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.request.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
