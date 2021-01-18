package by.kukshinov.hotel.builder;

import by.kukshinov.hotel.model.Entity;

import java.util.Map;
import java.util.Set;

public class RequestBuilder<T extends Entity> {

    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String OPEN_BRACKET = " (";
    private static final String COMMA_SPACE = ", ";
    private static final String CLOSING_BRACKET = ")";
    private static final String VALUES = " VALUES(";
    private static final String QUESTION = "?";
    private static final String UPDATE = "UPDATE ";
    private static final String SET = " SET ";
    private static final String ID = "id";
    private static final String WHERE_ID = " WHERE id=?";
    private static final String EQUALS_QUESTION = "=?";

    public String buildQuery(T item, String tableName, Map<String, Object> fields) {
        Long id = item.getId();
        if (id == null || id == 0) {
            return getSaveQuery(tableName, fields);
        } else {
            return getUpdateQuery(tableName, fields);
        }
    }

    private String getSaveQuery(String tableName, Map<String, Object> fields) {
        Set<String> fieldNames = fields.keySet();
        StringBuilder query = new StringBuilder(INSERT_INTO);
        query.append(tableName);
        query.append(OPEN_BRACKET);
        int counter = 0;
        for (String fieldName : fieldNames) {
            counter++;
            query.append(fieldName);
            if (counter != fieldNames.size()) {
                query.append(COMMA_SPACE);
            } else {
                query.append(CLOSING_BRACKET);
                counter = 0;
            }
        }
        query.append(VALUES);

        for (String ignored : fieldNames) {
            counter++;
            query.append(QUESTION);
            if (counter != fieldNames.size()) {
                query.append(COMMA_SPACE);
            } else {
                query.append(CLOSING_BRACKET);
                counter = 0;
            }
        }
        return query.toString();
    }

    private String getUpdateQuery(String tableName, Map<String, Object> fields) {
        Set<String> fieldNames = fields.keySet();
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(tableName);
        query.append(SET);
        int counter = 0;
        for (String fieldName : fieldNames) {
            counter++;
            if (fieldName.equals(ID) && counter != fieldNames.size()) {
                continue;
            } else if (counter == fieldNames.size()) {
                query.append(WHERE_ID);
                break;
            }
            query.append(fieldName);
            query.append(EQUALS_QUESTION);
            if (counter + 1 != fieldNames.size()) {
                query.append(COMMA_SPACE);
            }
        }
        return query.toString();
    }
}
