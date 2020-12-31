package by.kukshinov.hotel.dao.extractor;

import by.kukshinov.hotel.model.Application;
import by.kukshinov.hotel.model.ApplicationRoom;
import by.kukshinov.hotel.model.Room;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApplicationRoomFieldsExtractor implements FieldsExtractor<ApplicationRoom> {

    private static final String APPLICATION_ID = "application_id";
    private static final String ROOM_ID = "room_id";
    private static final String TOTAL_COST = "total_cost";

    @Override
    public Map<String, Object> extract(ApplicationRoom applicationRoom) {
        Application application = applicationRoom.getApplication();
        BigDecimal totalPrice = applicationRoom.getTotalCost();
        Room room = applicationRoom.getRoom();
        Long applicationId = application.getId();
        Long roomId = room.getId();

        Map<String, Object> fields = new LinkedHashMap<>();

        fields.put(APPLICATION_ID, applicationId);
        fields.put(ROOM_ID, roomId);
        fields.put(TOTAL_COST, totalPrice);

        return fields;
    }
}
