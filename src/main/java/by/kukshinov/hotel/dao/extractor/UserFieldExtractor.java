package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Role;
import by.kukshinov.hotel.model.User;

import java.util.*;

public class UserFieldExtractor implements FieldsExtractor<User> {


    @Override
    public List<Object> extract(User user) {
        List<Object> result = new ArrayList<>();
        Long userId = user.getUserId();
        String login = user.getLogin();
        String password = user.getPassword();
        boolean isDisabled = user.getIsDisabled();
        Role role = user.getRole();
        int falseInt = parseBooleanToInt(isDisabled);
        if(password != null) {
            result.add(password);
        }
        result.add(login);
        result.add(falseInt);
        result.add(role.toString());
        result.add(userId);
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
