package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class RejectApplicationCommandTest {
    private static final String APPLICATIONS = "/hotel/controller?command=admin_active_applications";
    private static final String ID = "id";
    private static final String ONE = "1";
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private static final Application IN_ORDER = new Application(1L, (byte) 4, ApartmentType.BUSINESS, TOMORROW, TOMORROW, ApplicationStatus.DENIED, new BigDecimal("505"), 1L, 1L);

    private RequestContext context;
    private ApplicationService service;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        service = Mockito.mock(ApplicationService.class);

        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        context.setRequestParameter(ID, ONE);
    }

    @Test
    public void testExecuteShouldReturnRedirectToActiveApplicationsWhenAppIsFound() throws ServiceException {
        RejectApplicationCommand command = new RejectApplicationCommand(service);
        doNothing().when(service).adminDenyOrderedApplication(any());
        CommandResult expected = CommandResult.redirect(APPLICATIONS);

        CommandResult actual = command.execute(context);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testExecuteThrowServiceExceptionWhenDenyError() throws ServiceException {
        RejectApplicationCommand command = new RejectApplicationCommand(service);
        doThrow(ServiceException.class).when(service).adminDenyOrderedApplication(any());


        command.execute(context);
    }
}
