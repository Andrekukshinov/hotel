package by.kukshinov.hotel.command;

import by.kukshinov.hotel.command.impl.*;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.model.creators.ApplicationCreatorImpl;
import by.kukshinov.hotel.service.impl.ApplicationServiceImpl;
import by.kukshinov.hotel.service.impl.RoomServiceImpl;
import by.kukshinov.hotel.service.impl.UserServiceImpl;
import by.kukshinov.hotel.validators.PageValidatorImpl;

public class CommandFactory {

    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String HOME_LOCATION = "WEB-INF/view/home.jsp";
    private static final String USER_HISTORY = "WEB-INF/view/profileHistory.jsp";
    private static final String PROFILE_HISTORY = "profileHistory";
    private static final String ALL_USERS = "admin_users";
    private static final String LOGIN = "login";
    private static final String BOOKING = "booking";
    private static final String HOME = "home";
    private static final String UPDATE_USER = "admin_update_user";
    private static final String BOOK_ROOM = "bookRoom";
    private static final String ROOMS = "admin_rooms";
    private static final String UPDATE_ROOM = "admin_update_room";
    private static final String LOGOUT = "logout";
    private static final String APPLICATIONS = "admin_applications";
    private static final String UPDATE_APPLICATION = "admin_update_application";

    public static Command createCommand(String commandParam) {
        switch (commandParam) {
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            case BOOKING:
                return new ForwardCommand(BOOKING_PAGE);
            case HOME:
                return new ForwardCommand(HOME_LOCATION);
            case ROOMS:
                return new AllRoomsCommand(new RoomServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case APPLICATIONS:
                return new AllApplicationsCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case UPDATE_APPLICATION:
                return new UpdateApplication(new ApplicationServiceImpl(new DaoHelperFactory()), new ApplicationCreatorImpl());
            case PROFILE_HISTORY:
                return new ForwardCommand(USER_HISTORY);
            case BOOK_ROOM:
                return new BookingCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new ApplicationCreatorImpl());
            case ALL_USERS:
                return new AllUsersCommand(new UserServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case UPDATE_USER:
                return new UpdateUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            case UPDATE_ROOM:
                return new UpdateRoomCommand(new RoomServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException();
        }
    }
}
