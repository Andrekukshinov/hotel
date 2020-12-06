package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.context.RequestContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BookingCommand implements Command {
    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String USER_HISTORY = "/hotel/controller?command=profileHistory";


    @Override
    public CommandResult execute(RequestContext context) {
        String personAmount = context.getRequestParameter(PERSON_AMOUNT);
        String apartment = context.getRequestParameter("apartment");
        String arrivalDate = context.getRequestParameter("arrivalDate");
        String leavingDate = context.getRequestParameter("leavingDate");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        if(arrivalDate != null) {
            try {
                Date date = (Date) formatter.parse(arrivalDate);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                System.out.println(5);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println(5);
        // TODO: 25.11.2020 correct logics
        if (personAmount != null) {
            return CommandResult.redirect(USER_HISTORY);
        }
        return CommandResult.forward(BOOKING_PAGE);
    }
}
