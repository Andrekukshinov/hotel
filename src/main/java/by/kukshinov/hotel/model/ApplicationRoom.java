package by.kukshinov.hotel.model;

import java.math.BigDecimal;

public class ApplicationRoom {
    private final Application application;
    private final Room room;

    public ApplicationRoom(Application application, Room room) {
        this.application = application;
        this.room = room;
    }

    public Application getApplication() {
        return application;
    }

    public Room getRoom() {
        return room;
    }

}
