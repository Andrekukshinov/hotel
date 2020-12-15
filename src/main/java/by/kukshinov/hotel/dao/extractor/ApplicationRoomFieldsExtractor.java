package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApplicationRoomFieldsExtractor implements FieldsExtractor<ApplicationRoom> {
    @Override
    public Map<String, Object> extract(ApplicationRoom applicationRoom) {
        Application application = applicationRoom.getApplication();
        Room room = applicationRoom.getRoom();
        Long applicationId = application.getId();
        Long roomId = room.getId();
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("application_id",applicationId);
        fields.put("room_id",roomId);
        return fields;
    }
}
