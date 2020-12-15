package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.Room;
import by.kukshinov.hotel.service.api.RoomService;
import by.kukshinov.hotel.validators.PageValidator;
import by.kukshinov.hotel.validators.PageValidatorImpl;

import java.util.List;

public class AllRoomsCommand implements Command {
    private static final String PAGE = "page";
    private static final int ITEMS_PER_PAGE = 4;
    private static final String ROOMS = "rooms";
    private static final String ALL_ROOMS = "WEB-INF/view/allRooms.jsp";
    private final RoomService service;
    private final PageValidator validator;

    public AllRoomsCommand(RoomService service, PageValidatorImpl validator) {
        this.service = service;
        this.validator = validator;
    }

    // TODO: 11.12.2020 add validation for the page
    @Override
    public CommandResult execute(RequestContext context) throws ServiceException {
        String currentPage = context.getRequestParameter(PAGE);
        int pageInt = validator.gatValidPage(currentPage);
        List<Room> rooms = service.getRangeEntities((pageInt - 1) * ITEMS_PER_PAGE, ITEMS_PER_PAGE);
        context.setRequestAttribute(ROOMS, rooms);
        context.setRequestAttribute(PAGE, pageInt);
        return CommandResult.forward(ALL_ROOMS);
    }

}
