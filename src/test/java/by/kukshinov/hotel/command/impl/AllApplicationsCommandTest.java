package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationUsernameDto;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
import by.kukshinov.hotel.service.api.ApplicationUsernameService;
import by.kukshinov.hotel.util.PageHelper;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AllApplicationsCommandTest {
    private static final String APPLICATIONS = "applications";
    private static final String PER_PAGE = "itemsPerPage";
    private static final String LAST_PAGE = "lastPage";

    private static final String LOGIN = "login";
    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final BigDecimal PRICE = new BigDecimal("505");
    private static final String CAPACITY_STRING = "1";

    private static final Application FIRST_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application SECOND_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.STANDARD, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final List<ApplicationUsernameDto> DTOS = Arrays.asList(new ApplicationUsernameDto(FIRST_APPLICATION, LOGIN), new ApplicationUsernameDto(SECOND_APPLICATION, LOGIN));


    private static final String ONE = "1";
    private static final String PAGE = "page";
    private static final Integer NUMBER = 1;
    private static final Integer ITEMS_PER_PAGE = 5;
    private ApplicationService applicationService;
    private ApplicationUsernameService applicationUsernameService;
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
    public void testExecuteShouldAddToContextDataAndReturnForwardToPageWhenValidData() throws ServiceException {

        AllApplicationsCommand command = new AllApplicationsCommand(applicationService, applicationUsernameService, validator);

        when(applicationUsernameService.findRange(anyInt(), anyInt())).thenReturn(DTOS);
        when(applicationService.findAllApplicationsAmount()).thenReturn(NUMBER);
        String url = "WEB-INF/view/AllApplications.jsp";
        CommandResult expected = CommandResult.forward(url);

        CommandResult actual = command.execute(context);

        List<ApplicationUsernameDto> actualAppUsernameList = (List<ApplicationUsernameDto>) context.getRequestAttribute(APPLICATIONS);
        Integer actualLastPage = (Integer) context.getRequestAttribute(LAST_PAGE);
        Integer actualPage = (Integer) context.getRequestAttribute(PAGE);
        Integer actualItemsPerPage = (Integer) context.getRequestAttribute(PER_PAGE);
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actualAppUsernameList, DTOS);
        Assert.assertEquals(actualLastPage, NUMBER);
        Assert.assertEquals(actualItemsPerPage, ITEMS_PER_PAGE);
        Assert.assertEquals(actualPage, NUMBER);
    }


    @Test(expectedExceptions = {ServiceException.class})//then
    public void testExecuteShouldThrowServiceException() throws ServiceException {
        //given
        when(applicationService.findAllApplicationsAmount()).thenThrow(ServiceException.class);
        AllApplicationsCommand command = new AllApplicationsCommand(applicationService, applicationUsernameService, validator);
        //when
        command.execute(context);
    }

}
