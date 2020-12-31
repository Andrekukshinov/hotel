package by.kukshinov.hotel.dao.mapper;

import by.kukshinov.hotel.model.enums.Role;
import by.kukshinov.hotel.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserObjectMapper implements ObjectMapper<User> {

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String IS_DISABLED = "is_disabled";
    private static final String ROLE = "role";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);
        boolean isDisabled = resultSet.getBoolean(IS_DISABLED);
        String roleString = resultSet.getString(ROLE);

        Role role = Role.valueOf(roleString);

        return new User(id, login, password, isDisabled, role);
    }
}
