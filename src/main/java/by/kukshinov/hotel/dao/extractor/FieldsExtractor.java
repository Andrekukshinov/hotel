package by.kukshinov.hotel.dao.extractor;

import java.util.List;
import java.util.Map;

public interface FieldsExtractor<T> {
    Map<String, Object> extract(T t);
}
