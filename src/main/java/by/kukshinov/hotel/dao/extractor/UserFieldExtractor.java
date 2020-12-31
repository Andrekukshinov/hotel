package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.model.User;

import java.util.*;

public class UserFieldExtractor implements FieldsExtractor<User> {


    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String IS_DISABLED = "is_disabled";
    private static final String ROLE = "role";
    private static final String ID = "id";

    @Override
    public Map<String, Object> extract(User user) {
        long userId = user.getUserId();
        String login = user.getLogin();
        String password = user.getPassword();
        boolean isDisabled = user.getIsDisabled();
        Role role = user.getRole();
        int falseInt = parseBooleanToInt(isDisabled);

        Map<String, Object> result = new LinkedHashMap<>();

        if(password != null) {
            result.put(PASSWORD, password);
        }
        result.put(LOGIN, login);
        result.put(IS_DISABLED, falseInt);
        result.put(ROLE, role.toString());
        if(userId > 0) {
            result.put(ID, userId);
        }

        return result;
    }

    private int parseBooleanToInt(boolean isDisabled) {
        int falseInt;
        if (isDisabled) {
            falseInt = 0;
        } else {
            falseInt = 1;
        }
        return falseInt;
    }
}
