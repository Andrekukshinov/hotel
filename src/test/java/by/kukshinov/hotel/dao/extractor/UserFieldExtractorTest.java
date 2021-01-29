package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.User;
import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.Role;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class UserFieldExtractorTest {

    private static final String LOGIN = "login";
    private static final String IS_DISABLED = "is_disabled";
    private static final String ID = "id";

    private static final long ID_VALUE = 1L;
    private static final String LOGIN_VALUE = "login";

    private static final User USER = new User(ID_VALUE, LOGIN_VALUE, false, Role.ADMIN);

    @Test
    public void testExtractShouldExtractAllFieldsFromUserAndPutToMap() {
        Map<String, Object> expected = new HashMap<>();
        expected.put(LOGIN, LOGIN_VALUE);
        expected.put(IS_DISABLED, false);
        expected.put(ID, ID_VALUE);
        UserFieldExtractor fieldsExtractor = new UserFieldExtractor();

        Map<String, Object> actual = fieldsExtractor.extract(USER);

        Assert.assertEquals(actual, expected);
    }
}
