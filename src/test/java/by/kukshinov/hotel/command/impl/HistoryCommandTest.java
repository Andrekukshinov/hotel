package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.util.PageHelper;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class HistoryCommandTest {
    private static final String ONE = "1";
    private static final String PAGE = "page";
    private static final Integer NUMBER = 1;
    private static final Long USER_ID_VALUE = 1L;
    private static final String USER_ID = "user_id";
    private static final String PER_PAGE = "itemsPerPage";
    private static final String LAST_PAGE = "lastPage";
    private static final Integer ITEMS_PER_PAGE = 5;
    private static final Application APPLICATION = new Application();
    private static final String PROFILE_HISTORY = "WEB-INF/view/profileHistory.jsp";
    private static final List<Application> APPLICATIONS = Arrays.asList(APPLICATION, APPLICATION);
    private static final String APPLICATIONS_NAME = "applications";


    private ApplicationService applicationService;
    private PageHelper validator;
    private RequestContext context;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());

        validator = Mockito.mock(PageHelper.class);

        applicationService = Mockito.mock(ApplicationService.class);

        context.setRequestParameter(PAGE, ONE);
        when(validator.getValidPage(anyString(), anyInt(), anyInt())).thenReturn(NUMBER);
        when(validator.getLastPage(anyInt(), anyInt())).thenReturn(NUMBER);
    }

    @Test
    public void testExecuteShouldAddToContextDataAndReturnForwardToPageWhenValidData() throws ServiceException {
        context.setSessionAttribute(USER_ID, USER_ID_VALUE);
        when(applicationService.findUserApplicationsAmount(anyLong())).thenReturn(NUMBER);
        when(applicationService.findRangeUserApplications(anyLong(), anyInt(), anyInt())).thenReturn(APPLICATIONS);
        CommandResult expected = CommandResult.forward(PROFILE_HISTORY);
        HistoryCommand command = new HistoryCommand(applicationService, validator);

        CommandResult actual = command.execute(context);

        List<Application> actualAppUsernameList = (List<Application>) context.getRequestAttribute(APPLICATIONS_NAME);
        Integer actualLastPage = (Integer) context.getRequestAttribute(LAST_PAGE);
        Integer actualPage = (Integer) context.getRequestAttribute(PAGE);
        Integer actualItemsPerPage = (Integer) context.getRequestAttribute(PER_PAGE);
        Assert.assertEquals(actualAppUsernameList, APPLICATIONS);
        Assert.assertEquals(actualLastPage, NUMBER);
        Assert.assertEquals(actualPage, NUMBER);
        Assert.assertEquals(actualItemsPerPage, ITEMS_PER_PAGE);
        Assert.assertEquals(actual, expected);
    }
}
