package by.kukshinov.hotel.command;

import by.kukshinov.hotel.command.impl.*;
import by.kukshinov.hotel.dao.DaoHelperFactory;
import by.kukshinov.hotel.exceptions.NoSuchCommandException;
import by.kukshinov.hotel.service.impl.*;
import by.kukshinov.hotel.util.PageHelper;
import by.kukshinov.hotel.util.RoomValidator;

public class CommandFactory {

    private static final String ADD_ROOM_PAGE = "WEB-INF/view/addRoom.jsp";
    private static final String ADMIN_ADD_ROOM = "admin_add_room";
    private static final String ADMIN_ALL_APPLICATIONS = "admin_all_applications";
    private static final String ADMIN_SUGGEST_ROOM = "admin_suggest_room";
    private static final String ADMIN_CHANGE_ROOM_ACTIVITY = "admin_change_room_activity";
    private static final String ADMIN_CREATE_ROOM = "admin_create_room";
    private static final String ADMIN_CHECK_STATUS = "admin_check_status";
    private static final String ADMIN_APPROVE_APPLICATION = "admin_approve_application";
    private static final String ADMIN_ACTIVE_APPLICATIONS = "admin_active_applications";
    private static final String ADMIN_USERS = "admin_users";
    private static final String ADMIN_UPDATE_USER = "admin_update_user";
    private static final String ADMIN_ROOMS = "admin_rooms";
    private static final String ADMIN_REJECT_APPLICATION = "admin_reject_application";
    private static final String ADMIN_SAVE_UPDATED_ROOM = "admin_save_updated_room";
    private static final String ADMIN_UPDATE_ROOM = "admin_update_room";
    private static final String BOOKING = "booking";
    private static final String BOOKING_PAGE = "WEB-INF/view/booking.jsp";
    private static final String BOOK_ROOM = "bookRoom";
    private static final String DEFAULT_REJECT_REASON = "reject_reason";
    private static final String DEFAULT_REJECT_PAGE = "WEB-INF/view/defaultRejectReason.jsp";
    private static final String HOME = "home";
    private static final String HOME_LOCATION = "WEB-INF/view/home.jsp";
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String PROFILE_HISTORY = "profileHistory";
    private static final String USER_BILL = "user_bill";
    private static final String USER_CANCEL_APPLICATION = "user_cancel_order";
    private static final String USER_REJECT = "user_reject_application";
    private static final String MESSAGE = "bad command";
    private static final String USER_BILLS = "user_bills";

    public static Command createCommand(String commandParam) {
        switch (commandParam) {
            case ADMIN_ADD_ROOM:
                return new AddRoomCommand(new RoomServiceImpl(new DaoHelperFactory()), new RoomValidator());
            case ADMIN_ALL_APPLICATIONS:
                return new AllApplicationsCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory()),
                        new ApplicationUsernameServiceImpl(new DaoHelperFactory()),
                        new PageHelper()
                );
            case ADMIN_CHECK_STATUS:
                return new AdminCheckBillCommand(
                        new UserServiceImpl(new DaoHelperFactory()),
                        new ApplicationRoomServiceImpl(new DaoHelperFactory())
                );
            case ADMIN_SUGGEST_ROOM:
                return new AvailableRoomsCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory()),
                        new RoomServiceImpl(new DaoHelperFactory()),
                        new PageHelper()
                );
            case ADMIN_USERS:
                return new AllUsersCommand(new UserServiceImpl(new DaoHelperFactory()), new PageHelper());
            case ADMIN_ACTIVE_APPLICATIONS:
                return new AllInOrderApplicationsCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new PageHelper());
            case ADMIN_APPROVE_APPLICATION:
                return new ApproveApplicationCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory()),
                        new RoomServiceImpl(new DaoHelperFactory())
                );
            case BOOKING:
                return new ForwardCommand(BOOKING_PAGE);
            case BOOK_ROOM:
                return new BookingCommand(new ApplicationServiceImpl(new DaoHelperFactory()));
            case ADMIN_CHANGE_ROOM_ACTIVITY:
                return new ChangeRoomActivityCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case ADMIN_CREATE_ROOM:
                return new ForwardCommand(ADD_ROOM_PAGE);
            case ADMIN_SAVE_UPDATED_ROOM:
                return new SaveUpdatedRoomCommand(new RoomValidator(), new RoomServiceImpl(new DaoHelperFactory()));
            case ADMIN_UPDATE_USER:
                return new UpdateUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            case ADMIN_UPDATE_ROOM:
                return new UpdateRoomPageCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case ADMIN_REJECT_APPLICATION:
                return new RejectApplicationCommand(new ApplicationServiceImpl(new DaoHelperFactory()));
            case ADMIN_ROOMS:
                return new AllRoomsCommand(new RoomServiceImpl(new DaoHelperFactory()), new PageHelper());
            case HOME:
                return new ForwardCommand(HOME_LOCATION);
            case DEFAULT_REJECT_REASON:
                return new ForwardCommand(DEFAULT_REJECT_PAGE);
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            case PROFILE_HISTORY:
                return new HistoryCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new PageHelper());
            case USER_BILL:
                return new UserBillCommand(
                        new ApplicationRoomServiceImpl(new DaoHelperFactory())
                );
            case USER_BILLS:
                return new AllUserBillsCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory()),
                        new PageHelper()
                );
            case USER_CANCEL_APPLICATION:
                return new UserCancelCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory())
                );
            case USER_REJECT:
                return new UserRejectCommand(
                        new ApplicationServiceImpl(new DaoHelperFactory())
                );
            default:
                throw new NoSuchCommandException(MESSAGE);
        }
    }
}
