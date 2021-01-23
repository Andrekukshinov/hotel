package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.impl.ApplicationServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingCommandTest {
    private static final String PATTERN = "(wrong arrival date!)|(Wrong leaving date!)|(Wrong capacity!)";
    private static final String USER_HISTORY = "/hotel/controller?command=profileHistory";
    private static final String PERSON_AMOUNT = "personAmount";
    private static final String APARTMENT = "apartment";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String LEAVING_DATE = "leavingDate";
    private static final String USER_ID = "user_id";
    private static final String CAPACITY_VALUE = "2";
    private static final String STANDARD = "standard";
    private static final Long USER_ID_VALUE = 2L;
    private static final String MINUS_ONE = "-1";

    private ApplicationService service;
    private RequestContext context;

    @DataProvider
    public Object[][] validationDataProvider() {
        return new Object[][]{
                {
                        CAPACITY_VALUE,
                        LocalDate.now().minusDays(1).toString(),
                        LocalDate.now().plusDays(1).toString()
                },
                {
                        CAPACITY_VALUE,
                        LocalDate.now().plusDays(1).toString(),
                        LocalDate.now().minusDays(1).toString()
                },
                {
                        MINUS_ONE,
                        LocalDate.now().plusDays(1).toString(),
                        LocalDate.now().plusDays(1).toString()
                }
        };
    }

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
    public void testExecuteShouldReturnRedirectToProfileHistoryWhenValidData() throws ServiceException, ParseException {
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
    public void testExecuteShouldThrowServiceExceptionWhenDaoExceptionIsCaught() throws ServiceException {
        //given
        ApplicationService service = Mockito.mock(ApplicationServiceImpl.class);
        doThrow(ServiceException.class).when(service).save(any());
        Command command = new BookingCommand(service);
        //when
        command.execute(context);
    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = PATTERN, dataProvider = "validationDataProvider")//then
    public void testExecuteShouldThrowServiceExceptionWhenArrivalDateFromPast(String capacity, String arrivalDate, String leavingDate)
            throws ServiceException {
        //given
        Map<String, String> param = new HashMap<>();
        Map<String, Object> sessionAttributes = new HashMap<>();
        param.put(PERSON_AMOUNT, capacity);
        param.put(APARTMENT, STANDARD);
        param.put(ARRIVAL_DATE, arrivalDate);
        param.put(LEAVING_DATE, leavingDate);
        sessionAttributes.put(USER_ID, USER_ID_VALUE);
        RequestContext context = new RequestContext(param, new HashMap<>(), sessionAttributes);
        ApplicationService service = Mockito.mock(ApplicationServiceImpl.class);
        Command command = new BookingCommand(service);
        //when
        command.execute(context);
    }
}
