package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.request.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class BookingCommand implements Command {
    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String USER_HISTORY = "/hotel/controller?command=profile.history";


    @Override
    public CommandResult execute(RequestContext context) {
        Map<String, String> req = context.getRequestParameters();
        String personAmount = req.get(PERSON_AMOUNT);
        // TODO: 25.11.2020 correct logics
        if (personAmount != null) {
            return CommandResult.redirect(USER_HISTORY);
        }
        return CommandResult.forward(BOOKING_PAGE);
    }
}
