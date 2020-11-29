package by.kukshinov.hotel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface ObjectMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
