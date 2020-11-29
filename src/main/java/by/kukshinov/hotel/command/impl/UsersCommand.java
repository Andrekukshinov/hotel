package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersCommand implements Command {
    private static final String ALL_USERS = "WEB-INF/view/allUsers.jsp";

    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = userService.getAllUsers();
//        User user = userService.getUser();
        req.setAttribute("users", users);
//        req.setAttribute("user", user);
        return CommandResult.forward(ALL_USERS);
    }
}
