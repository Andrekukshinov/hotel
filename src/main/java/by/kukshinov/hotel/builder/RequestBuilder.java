package by.kukshinov.hotel.builder;

import by.kukshinov.hotel.model.Entity;

import java.util.Map;
import java.util.Set;

public class RequestBuilder<T extends Entity> {

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
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName);
        query.append(" (");
        int counter = 0;
        for (String fieldName : fieldNames) {
            counter++;
            query.append(fieldName);
            if (counter != fieldNames.size()) {
                query.append(", ");
            } else {
                query.append(")");
                counter = 0;
            }
        }
        query.append(" VALUES(");

        for (String ignored : fieldNames) {
            counter++;
            query.append("?");
            if (counter != fieldNames.size()) {
                query.append(", ");
            } else {
                query.append(")");
                counter = 0;
            }
        }
        return query.toString();
    }

    private String getUpdateQuery(String tableName, Map<String, Object> fields) {
        Set<String> fieldNames = fields.keySet();
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName);
        query.append(" SET ");
        int counter = 0;
        for (String fieldName : fieldNames) {
            counter++;
            if (fieldName.equals("id") && counter != fieldNames.size()) {
                continue;
            } else if (counter == fieldNames.size()) {
                query.append(" WHERE id=?");
                break;
            }
            query.append(fieldName);
            query.append("=?");
            if (counter + 1 != fieldNames.size()) {
                query.append(", ");
            }
        }
        return query.toString();
    }
}
