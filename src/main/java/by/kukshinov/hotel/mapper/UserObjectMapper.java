package by.kukshinov.hotel.mapper;

import by.kukshinov.hotel.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserObjectMapper implements ObjectMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("pass");
        boolean isDisabled = resultSet.getBoolean("is_disabled");
        return new User(id, login, password, isDisabled);
    }
}
