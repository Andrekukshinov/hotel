package by.kukshinov.hotel.command.impl;

import by.kukshinov.hotel.context.RequestContext;
import by.kukshinov.hotel.model.CommandResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

public class LogoutCommandTest {
    private static final String DOMAIN_PAGE = "/hotel";


    @Test
    public void testExecuteShouldSetInvalidSessionAndReturnRedirectToDomainPage() {
        RequestContext context = new RequestContext(new HashMap<>(), new HashMap<>(), new HashMap<>());
        LogoutCommand command = new LogoutCommand();
        CommandResult expected = CommandResult.redirect(DOMAIN_PAGE);

        CommandResult actual = command.execute(context);

//        a
        Assert.assertEquals(actual, expected);
    }
}
