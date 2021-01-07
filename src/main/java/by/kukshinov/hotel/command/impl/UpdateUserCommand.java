package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.service.api.UserService;

import java.util.Optional;

public class UpdateUserCommand implements Command {
    private static final String ID = "userId";
    private static final String IS_DISABLED = "isDisabled";
    private static final String ALL_USERS = "/hotel/controller?command=admin_users";
    private static final String WRONG_USER = "Wrong user";
    private static final String USER_ID = "user_id";
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String stringId = context.getRequestParameter(ID);
        String stringIsDisabled = context.getRequestParameter(IS_DISABLED);
        Long sessionUserId = (Long) context.getSessionAttribute(USER_ID);

        long id = Long.parseLong(stringId);
        boolean isDisabled = Boolean.parseBoolean(stringIsDisabled);

        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Role userRole = user.getRole();
            if (user.getUserId() != sessionUserId && !Role.ADMIN.equals(userRole)) {
                userService.changeUserStatus(user, isDisabled);
            }
            return CommandResult.redirect(ALL_USERS);
        } else {
            throw new ServiceException(WRONG_USER);
        }
    }
}
