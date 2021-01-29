package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserCancelCommandTest {
    private static final String PROFILE_HISTORY = "controller?command=profileHistory";
    private static final String USER_ID = "user_id";
    private static final long ONE_LONG = 1L;
    private static final String APPLICATION_ID = "id";
    private ApplicationService service;
    private RequestContext context;

    @BeforeMethod
    public void setUpServiceAndContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        context.setSessionAttribute(USER_ID, ONE_LONG);
        context.setRequestParameter(APPLICATION_ID, Long.toString(ONE_LONG));

        service = Mockito.mock(ApplicationService.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectToHistoryPageWhenDataIsValid() throws ServiceException {
        when(service.findInOrderUserApplicationById(anyLong(), anyLong())).thenReturn(Optional.of(new Application()));
        UserCancelCommand command = new UserCancelCommand(service);
        CommandResult expected = CommandResult.redirect(PROFILE_HISTORY);

        CommandResult actual = command.execute(context);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testExecuteShouldThrowServiceExceptionWhenApplicationIsNotFound() throws ServiceException {
        when(service.findInOrderUserApplicationById(anyLong(), anyLong())).thenReturn(Optional.empty());
        UserCancelCommand command = new UserCancelCommand(service);

        command.execute(context);

    }
}
