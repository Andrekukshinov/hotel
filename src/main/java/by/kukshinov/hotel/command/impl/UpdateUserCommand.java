package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.service.api.UserService;

public class UpdateUserCommand implements Command {
    private static final String LOGIN = "login";
    private static final String ID = "userId";
    private static final String IS_DISABLED = "isDisabled";
    private static final String ROLE = "role";
    private static final String ALL_USERS = "/hotel/controller?command=admin_users";
    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
// TODO: 11.12.2020 validation
        // TODO: 15.12.2020 change to get by id + update
        String login = context.getRequestParameter(LOGIN);
        String stringId = context.getRequestParameter(ID);
        String stringRole = context.getRequestParameter(ROLE);
        String stringIsDisabled = context.getRequestParameter(IS_DISABLED);

        long id = Long.parseLong(stringId);
        boolean isDisabled = Boolean.parseBoolean(stringIsDisabled);
        Role role = Role.valueOf(stringRole);

        User user = new User(id, login, null, isDisabled, role);

        userService.updateUser(user);
        return CommandResult.redirect(ALL_USERS);
    }

}
