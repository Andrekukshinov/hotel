package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.model.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingCommand implements Command {
    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String USER_HISTORY = "/hotel/controller?command=profile.history";


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String personAmount = req.getParameter(PERSON_AMOUNT);
        // TODO: 25.11.2020 correct logics
        if (personAmount != null) {
            return CommandResult.redirect(USER_HISTORY);
        }
        return CommandResult.forward(BOOKING_PAGE);
    }
}
