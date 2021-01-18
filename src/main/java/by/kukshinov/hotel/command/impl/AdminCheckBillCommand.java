package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.UserService;

import java.util.Optional;

public class AdminCheckBillCommand implements Command {
    private static final String APPLICATION = "application";
    private static final String BILL = "WEB-INF/view/userBill.jsp";
    private static final String APPLICATION_ID = "id";
    private static final String WRONG_APPLICATION = "Wrong application!";
    private static final String LOGIN = "login";
    private static final String ROLE = "ROLE";

    private final UserService userService;

    private final ApplicationRoomService applicationRoomService;

    public AdminCheckBillCommand(UserService userService, ApplicationRoomService applicationRoomService) {
        this.userService = userService;
        this.applicationRoomService = applicationRoomService;
    }


    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdString = context.getRequestParameter(APPLICATION_ID);
        long applicationId = Long.parseLong(applicationIdString);
        Object role = context.getSessionAttribute(ROLE);

        ApplicationRoom applicationDTO = applicationRoomService.findApplicationRoom(applicationId);
        Application application = applicationDTO.getApplication();

        Long userId = application.getUserId();
        Optional<User> optionalUser = userService.findById(userId);
        User user = optionalUser.orElseThrow(() -> new ServiceException(WRONG_APPLICATION));
        String login = user.getLogin();


        context.setRequestAttribute(APPLICATION, applicationDTO);
        context.setRequestAttribute(LOGIN, login);
        context.setRequestAttribute(ROLE, role);
        return CommandResult.forward(BILL);
    }
}
