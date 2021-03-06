package by.kukshinov.hotel.util;

import java.util.Map;
import java.util.Set;

/**
 * Utility class for building save and update query (MySql)
 */
public class RequestCreator {

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


    /**
     * Method that builds save query based on table name and object parameters received
     * @param tableName map where key is a name of the table for updating, and value is an actual value to be saved
     * @param fields fields of the database
     * @return save query
     */
    public String getSaveQuery(String tableName, Map<String, Object> fields) {
        Set<String> fieldNames = fields.keySet();
        StringBuilder query = new StringBuilder(INSERT_INTO);
        query.append(tableName);
        query.append(OPEN_BRACKET);
        int counter = 0;
        for (String fieldName : fieldNames) {
            counter = buildQueryPart(fieldNames, query, counter, fieldName);
        }
        query.append(VALUES);
        for (String ignored : fieldNames) {
            counter = buildQueryPart(fieldNames, query, counter, QUESTION);
        }
        return query.toString();
    }

    private int buildQueryPart(Set<String> fieldNames, StringBuilder query, int counter, String fieldName) {
        counter++;
        query.append(fieldName);
        if (counter != fieldNames.size()) {
            query.append(COMMA_SPACE);
        } else {
            query.append(CLOSING_BRACKET);
            counter = 0;
        }
        return counter;
    }

    /**
     * Method that builds update query based on table name and object parameters received
     * @param tableName map where key is a name of the table for updating, and value is an actual value to be updated by
     * @param fields fields of the database
     * @return updating query
     */
    public String getUpdateQuery(String tableName, Map<String, Object> fields) {
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
