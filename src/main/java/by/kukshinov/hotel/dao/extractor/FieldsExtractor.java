package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Entity;

import java.util.Map;

/**
 * This interface is designed for extracting fields data from Entity class object
 * and building Map that keys correspond with table field name in the database. When
 * value of this Map is actual object value
 *
 * @param <T> any entity class of the application
 */
public interface FieldsExtractor<T extends Entity> {
    /**
     * Extracts fields from accepted object and creates Map which holds values
     * of the object for the database, and its keys correspond to filed name
     * of the table in the specified db
     *
     * @param t entity class object
     * @return Map of table filed name and the value to be inserted
     */
    Map<String, Object> extract(T t);
}
