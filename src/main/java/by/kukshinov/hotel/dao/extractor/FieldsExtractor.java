package by.kukshinov.hotel.dao.extractor;

import java.util.List;
import java.util.Map;

public interface FieldsExtractor<T> {
    List<Object> extract(T t);
}
