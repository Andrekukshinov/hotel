package by.kukshinov.hotel.command.impl;


import by.kukshinov.hotel.command.Command;
import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.CommandResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForwardCommandTest {
    private static final String URL = "some url";

     @Test
     public void testExecuteReturnForwardToSpecifiedPage () throws ServiceException {
         Command command = new ForwardCommand(URL);
         CommandResult expected = CommandResult.forward(URL);

         CommandResult actual = command.execute(null);

         Assert.assertEquals(actual, expected);
     }
}
