package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.creators.ApplicationCreator;
import by.kukshinov.hotel.model.creators.ApplicationCreatorImpl;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.impl.ApplicationServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
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
    private static final String USER_ID = "userId";
    private static final String CAPACITY_VALUE = "2";
    private static final String STANDARD = "standard";
    private static final String ARRIVAL_DATE_VALUE = "2020-12-12T20:23";
    private static final String LEAVING_DATE_VALUE = "2020-12-15T20:23";
    private static final String USER_ID_VALUE = "2";

    @Test
    public void testExecuteShouldReturnRedirect() throws ServiceException, ParseException {
        ApplicationService service = Mockito.mock(ApplicationServiceImpl.class);
        ApplicationCreator creator = Mockito.mock(ApplicationCreatorImpl.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(PERSON_AMOUNT, CAPACITY_VALUE);
        requestParameters.put(APARTMENT, STANDARD);
        requestParameters.put(ARRIVAL_DATE, ARRIVAL_DATE_VALUE);
        requestParameters.put(LEAVING_DATE, LEAVING_DATE_VALUE);
        requestParameters.put(USER_ID, USER_ID_VALUE);
        RequestContext context = new RequestContext(requestParameters, new HashMap<>(), new HashMap<>());
        when(creator.getApplication(any(), anyString(), anyString(), anyString(), anyString(), anyLong(), anyString(), anyString())).thenReturn(new Application());
        doNothing().when(service).save(any());
        Command command = new BookingCommand(service, creator);
        CommandResult expected = CommandResult.redirect(USER_HISTORY);

        CommandResult actual = command.execute(context);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testExecuteShouldThrowServiceException() throws ServiceException, ParseException {
        ApplicationService service = Mockito.mock(ApplicationServiceImpl.class);
        ApplicationCreator creator = Mockito.mock(ApplicationCreatorImpl.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(PERSON_AMOUNT, CAPACITY_VALUE);
        requestParameters.put(APARTMENT, STANDARD);
        requestParameters.put(ARRIVAL_DATE, ARRIVAL_DATE_VALUE);
        requestParameters.put(LEAVING_DATE, LEAVING_DATE_VALUE);
        requestParameters.put(USER_ID, USER_ID_VALUE);
        RequestContext context = new RequestContext(requestParameters, new HashMap<>(), new HashMap<>());
        when(creator.getApplication(any(), anyString(), anyString(), anyString(), anyString(), anyLong(), anyString(), anyString())).thenReturn(new Application());
        doThrow(ServiceException.class).when(service).save(any());
        Command command = new BookingCommand(service, creator);

        command.execute(context);
    }
}
