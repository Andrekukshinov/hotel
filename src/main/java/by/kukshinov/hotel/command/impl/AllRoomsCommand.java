package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.util.PageHelper;
import by.kukshinov.hotel.util.PageHelperImpl;

import java.util.List;

public class AllRoomsCommand implements Command {
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 4;
    private static final String ROOMS = "rooms";
    private static final String ALL_ROOMS = "WEB-INF/view/allRooms.jsp";
    private static final String LAST_PAGE = "lastPage";
    private final RoomService service;
    private final PageHelper validator;

    public AllRoomsCommand(RoomService service, PageHelperImpl validator) {
        this.service = service;
        this.validator = validator;
    }

    // TODO: 11.12.2020 add validation for the page
    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        int roomAmount = service.getRoomAmount();
        String currentPage = context.getRequestParameter(PAGE);
        int pageInt = validator.getValidPage(currentPage, roomAmount, ITEMS_PER_PAGE);
        List<Room> rooms = service.findRangeEntities((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        int lastPage = validator.getLastPage(roomAmount, ITEMS_PER_PAGE);
        context.setRequestAttribute(LAST_PAGE, lastPage);
        context.setRequestAttribute(ROOMS, rooms);
        context.setRequestAttribute(PAGE, pageInt);
        return CommandResult.forward(ALL_ROOMS);
    }

}
