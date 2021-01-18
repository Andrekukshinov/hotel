package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.creators.ApplicationCreator;
import by.kukshinov.hotel.model.creators.ApplicationCreatorImpl;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.UserService;
import by.kukshinov.hotel.service.impl.ApplicationServiceImpl;
import by.kukshinov.hotel.service.impl.UserServiceImpl;
import by.kukshinov.hotel.validators.PageValidatorImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingCommandTest {
    private static final String USER_HISTORY = "/hotel/controller?command=profileHistory";

    private static final String PERSON_AMOUNT = "personAmount";
    private static final String APARTMENT = "apartment";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String USER_ID = "user_id";
    private static final String CAPACITY_VALUE = "2";
    private static final String STANDARD = "standard";
    private static final Long USER_ID_VALUE = 2L;

    private ApplicationService service;
    private RequestContext context;

    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = mock(ApplicationService.class);

        Map<String, String> param = new HashMap<>();
        Map<String, Object> sessionAttributes = new HashMap<>();

        LocalDate arrivalDate = LocalDate.now().plusDays(1);
        LocalDate leavingDate = LocalDate.now().plusDays(2);

        String stringArrival = arrivalDate.toString();
        String stringLeaving = leavingDate.toString();

        param.put(PERSON_AMOUNT, CAPACITY_VALUE);
        param.put(APARTMENT, STANDARD);
        param.put(ARRIVAL_DATE, stringArrival);
        param.put(LEAVING_DATE, stringLeaving);

        sessionAttributes.put(USER_ID, USER_ID_VALUE);
        context = new RequestContext(param, new HashMap<>(), sessionAttributes);


    }

    @Test
    public void testExecuteShouldReturnRedirect() throws ServiceException, ParseException {
        //given
        doNothing().when(service).save(any());
        Command command = new BookingCommand(service);
        CommandResult expected = CommandResult.redirect(USER_HISTORY);
        //when
        CommandResult actual = command.execute(context);
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)//then
    public void testExecuteShouldThrowServiceException() throws ServiceException {
        //given
        ApplicationService service = Mockito.mock(ApplicationServiceImpl.class);
        doThrow(ServiceException.class).when(service).save(any());
        Command command = new BookingCommand(service);
        //when
        command.execute(context);
    }
}
