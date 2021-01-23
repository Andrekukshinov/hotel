package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.ApplicationUsernameService;
import by.kukshinov.hotel.util.PageHelper;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class HistoryCommandTest {
    private static final String ONE = "1";
    private static final String PAGE = "page";
    private static final Integer NUMBER = 1;

    private ApplicationUsernameService applicationUsernameService;
    private ApplicationService applicationService;
    private PageHelper validator;
    private RequestContext context;


    @BeforeMethod
    public void mockServicesAndRequestContext() {
        context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());

        validator = Mockito.mock(PageHelper.class);

        applicationService = Mockito.mock(ApplicationService.class);
        applicationUsernameService = Mockito.mock(ApplicationUsernameService.class);

        context.setRequestParameter(PAGE, ONE);
        when(validator.getValidPage(anyString(), anyInt(), anyInt())).thenReturn(NUMBER);
        when(validator.getLastPage(anyInt(), anyInt())).thenReturn(NUMBER);
    }
    @Test
    public void testExecuteShouldAddToContextDataAndReturnForwardToPageWhenValidData() {

    }
}
