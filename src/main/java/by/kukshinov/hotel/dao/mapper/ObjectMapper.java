package by.kukshinov.hotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
