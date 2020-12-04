package by.kukshinov.hotel.command;

import by.kukshinov.hotel.command.impl.*;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.service.UserServiceImpl;

public class CommandFactory {

    private static final String LOGIN = "login";
    private static final String BOOKING = "booking";
    private static final String HOME = "home";
    private static final String HOME_LOCATION = "WEB-INF/view/home.jsp";
    private static final String USER_HISTORY = "WEB-INF/view/profileHistory.jsp";
    private static final String PROFILE_HISTORY = "profile.history";
    private static final String ALL_USERS = "all.users";
    private static final String UPDATE = "updateUser";

    public static Command createCommand(String commandParam) {
        switch (commandParam) {
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case BOOKING:
                return new BookingCommand();
            case HOME:
                return new ForwardCommand(HOME_LOCATION);
            case PROFILE_HISTORY:
                return new ForwardCommand(USER_HISTORY);
            case ALL_USERS:
                return new UsersCommand(new UserServiceImpl(new DaoHelperFactory()));
            case UPDATE:
                return new UpdateUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException();
        }
    }
}
