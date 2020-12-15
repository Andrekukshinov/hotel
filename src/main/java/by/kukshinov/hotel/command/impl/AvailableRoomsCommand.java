package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.ApplicationRoomService;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.validators.PageValidator;

import java.util.List;
import java.util.Optional;

public class AvailableRoomsCommand implements Command {
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 4;
    private static final String ROOMS = "rooms";
    private static final String AVAILABLE_ROOMS = "WEB-INF/view/suggestRoom.jsp";
    private static final String APPLICATION_ID = "applicationId";
    private static final String APPLICATION = "application";
    private final ApplicationService applicationService;
    private final RoomService roomService;
    private final PageValidator validator;

    public AvailableRoomsCommand(ApplicationService service, RoomService roomService, PageValidator validator) {
        this.applicationService = service;
        this.roomService = roomService;
        this.validator = validator;
    }
    //todo ask
    // TODO: 15.12.2020 rework 4 mistake
    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String currentPage = context.getRequestParameter(PAGE);
        String applicationIdParam = context.getRequestParameter(APPLICATION_ID);
        long applicationId = Long.parseLong(applicationIdParam);
        int pageInt = validator.gatValidPage(currentPage);
        Optional<Application> optionalApplication = applicationService.findQueuedApplicationById(applicationId);
        List<Room> rooms = roomService.getRangeAvailableRooms((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        if (optionalApplication.isPresent()) {
            context.setRequestAttribute(ROOMS, rooms);
            context.setRequestAttribute(APPLICATION, optionalApplication.get());
            context.setRequestAttribute(PAGE, pageInt);
            context.setRequestAttribute(APPLICATION_ID, applicationId);
            return CommandResult.forward(AVAILABLE_ROOMS);
        } else {
            return CommandResult.forward("AVAILABLE_ROOMS");
        }
    }

}
