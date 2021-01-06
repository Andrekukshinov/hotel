package by.kukshinov.hotel.command;

import by.kukshinov.hotel.command.impl.*;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.exceptions.NoSuchCommandException;
import by.kukshinov.hotel.model.creators.ApplicationCreatorImpl;
import by.kukshinov.hotel.service.impl.ApplicationRoomServiceImpl;
import by.kukshinov.hotel.service.impl.ApplicationServiceImpl;
import by.kukshinov.hotel.service.impl.RoomServiceImpl;
import by.kukshinov.hotel.service.impl.UserServiceImpl;
import by.kukshinov.hotel.validators.PageValidatorImpl;

public class CommandFactory {

    private static final String ADD_ROOM = "admin_add_room";
    private static final String ADD_ROOM_PAGE = "WEB-INF/view/addRoom.jsp";
    private static final String ADMIN_SUGGEST_ROOM = "admin_suggest_room";
    private static final String ALL_USERS = "admin_users";
    private static final String APPLICATIONS = "admin_applications";
    private static final String APPROVE_APPLICATION = "admin_approve_application";
    private static final String BOOKING = "booking";
    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String BOOK_ROOM = "bookRoom";
    private static final String CREATE_ROOM = "admin_create_room";
    private static final String UPDATE_USER = "admin_update_user";
    private static final String HOME = "home";
    private static final String HOME_LOCATION = "WEB-INF/view/home.jsp";
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String ROOMS = "admin_rooms";
    private static final String PROFILE_HISTORY = "profileHistory";
    private static final String REJECT_APPLICATION = "admin_reject_application";
    private static final String UPDATE_ROOM = "admin_update_room";
    private static final String USER_BILL = "user_bill";
    private static final String USER_REJECT = "user_reject_application";
    private static final String MESSAGE = "bad command";

    public static Command createCommand(String commandParam) {
        switch (commandParam) {
            case ADD_ROOM:
                return new AddRoomCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case ADMIN_SUGGEST_ROOM:
                return new AvailableRoomsCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory()),
                        new RoomServiceImpl(new DaoHelperFactory()),
                        new PageValidatorImpl()
                );
            case ALL_USERS:
                return new AllUsersCommand(new UserServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case APPLICATIONS:
                return new AllInOrderApplicationsCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case APPROVE_APPLICATION:
                return new ApproveApplicationCommand(new ApplicationRoomServiceImpl(new DaoHelperFactory()));
            case BOOKING:
                return new ForwardCommand(BOOKING_PAGE);
            case BOOK_ROOM:
                return new BookingCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new ApplicationCreatorImpl());
            case CREATE_ROOM:
                return new ForwardCommand(ADD_ROOM_PAGE);
            case HOME:
                return new ForwardCommand(HOME_LOCATION);
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            case REJECT_APPLICATION:
                return new RejectApplicationCommand(new ApplicationServiceImpl(new DaoHelperFactory()));
            case ROOMS:
                return new AllRoomsCommand(new RoomServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case PROFILE_HISTORY:
                return new HistoryCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new PageValidatorImpl());
            case UPDATE_ROOM:
                return new UpdateRoomCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case UPDATE_USER:
                return new UpdateUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            case USER_BILL:
                return new UserBillCommand(new ApplicationRoomServiceImpl(new DaoHelperFactory()));
            case USER_REJECT:
                return new UserRejectCommand(new ApplicationRoomServiceImpl(new DaoHelperFactory()));
            default:
                throw new NoSuchCommandException(MESSAGE);
        }
    }
}
