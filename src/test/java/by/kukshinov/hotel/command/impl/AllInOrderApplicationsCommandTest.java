package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationUsernameDto;
import by.kukshinov.hotel.model.CommandResult;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;
import by.kukshinov.hotel.service.api.ApplicationService;
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

public class AllInOrderApplicationsCommandTest {
    private static final String APPLICATIONS = "applications";
    private static final String PER_PAGE = "itemsPerPage";
    private static final String LAST_PAGE = "lastPage";

    private static final long USER_ID = 1L;
    private static final long ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final BigDecimal PRICE = new BigDecimal("505");
    private static final String CAPACITY_STRING = "1";

    private static final Application FIRST_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.BUSINESS, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final Application SECOND_APPLICATION = new Application(ID, new Byte(CAPACITY_STRING), ApartmentType.STANDARD, LocalDate.now(), LocalDate.now(), ApplicationStatus.APPROVED, PRICE, ROOM_ID, USER_ID);
    private static final List<Application> APPLICATIONS_VALUE = Arrays.asList(FIRST_APPLICATION, SECOND_APPLICATION);


    private static final String ONE = "1";
    private static final String PAGE = "page";
    private static final Integer NUMBER = 1;
    private static final Integer ITEMS_PER_PAGE = 7;

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
        AllInOrderApplicationsCommand command = new AllInOrderApplicationsCommand(applicationService, validator);
        String url = "WEB-INF/view/userApplications.jsp";
        CommandResult expected = CommandResult.forward(url);
        when(applicationService.findRangeOrderedEntities(anyInt(), anyInt())).thenReturn(APPLICATIONS_VALUE);
        when(applicationService.findInOrderApplicationsAmount()).thenReturn(NUMBER);

        CommandResult actual = command.execute(context);

        List<ApplicationUsernameDto> actualAppUsernameList = (List<ApplicationUsernameDto>) context.getRequestAttribute(APPLICATIONS);
        Integer actualLastPage = (Integer) context.getRequestAttribute(LAST_PAGE);
        Integer actualPage = (Integer) context.getRequestAttribute(PAGE);
        Integer actualItemsPerPage = (Integer) context.getRequestAttribute(PER_PAGE);
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actualAppUsernameList, APPLICATIONS_VALUE);
        Assert.assertEquals(actualLastPage, NUMBER);
        Assert.assertEquals(actualPage, NUMBER);
        Assert.assertEquals(actualItemsPerPage, ITEMS_PER_PAGE);
    }

    @Test(expectedExceptions = {ServiceException.class})//then
    public void testExecuteShouldThrowServiceException () throws ServiceException {
        //given
        when(applicationService.findInOrderApplicationsAmount()).thenThrow(ServiceException.class);
        AllInOrderApplicationsCommand command = new AllInOrderApplicationsCommand(applicationService, validator);

        //when
        command.execute(context);
    }
}
