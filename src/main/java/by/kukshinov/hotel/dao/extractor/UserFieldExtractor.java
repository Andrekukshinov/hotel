package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserFieldExtractor implements FieldsExtractor<User> {


    private static final String LOGIN = "login";
    private static final String IS_DISABLED = "is_disabled";
    private static final String ID = "id";

    @Override
    public Map<String, Object> extract(User user) {
        long userId = user.getId();
        String login = user.getLogin();
        boolean isDisabled = user.getIsDisabled();


        Map<String, Object> result = new LinkedHashMap<>();

        result.put(LOGIN, login);
        result.put(IS_DISABLED, isDisabled);
        result.put(ID, userId);

        return result;
    }

//    private int parseBooleanToInt(boolean isDisabled) {
//        int falseInt;
//        if (isDisabled) {
//            falseInt = 0;
//        } else {
//            falseInt = 1;
//        }
//        return falseInt;
//    }
}
