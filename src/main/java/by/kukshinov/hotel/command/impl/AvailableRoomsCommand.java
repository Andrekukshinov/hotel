package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.util.PageHelper;

import java.time.LocalDate;
import java.util.List;

public class AvailableRoomsCommand implements Command {
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 4;
    private static final String ROOMS = "rooms";
    private static final String AVAILABLE_ROOMS = "WEB-INF/view/suggestRoom.jsp";
    private static final String APPLICATION_ID = "applicationId";
    private static final String APPLICATION = "application";
    private static final String LAST_PAGE = "lastPage";
    private static final String NOW = "now";

    private final ApplicationService applicationService;
    private final RoomService roomService;
    private final PageHelper validator;

    public AvailableRoomsCommand(ApplicationService service, RoomService roomService, PageHelper validator) {
        this.applicationService = service;
        this.roomService = roomService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String applicationIdParam = context.getRequestParameter(APPLICATION_ID);
        String currentPage = context.getRequestParameter(PAGE);
        Long applicationId = Long.parseLong(applicationIdParam);
        Application application = applicationService.findFutureArrivalInOrderApplicationById(applicationId);

        LocalDate arrivalDate = application.getArrivalDate();
        LocalDate leavingDate = application.getLeavingDate();

        int availableRooms = roomService.getAvailableRoomAmount(arrivalDate, leavingDate);
        Integer pageInt = validator.getValidPage(currentPage, availableRooms, ITEMS_PER_PAGE);

        List<Room> rooms = roomService.findRangeAvailableRooms(arrivalDate, leavingDate, (pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        Integer lastPage = validator.getLastPage(availableRooms, ITEMS_PER_PAGE);


        context.setRequestAttribute(APPLICATION, application);
        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(ROOMS, rooms);
        context.setRequestAttribute(PAGE, pageInt);
        context.setRequestAttribute(NOW, LocalDate.now());
        context.setRequestAttribute(APPLICATION_ID, applicationId);
        return CommandResult.forward(AVAILABLE_ROOMS);

    }
}
