package by.kukshinov.hotel.model;

public class ApplicationRoom {
    private final Application application;
    private final Room room;

    public ApplicationRoom(Application application, Room room) {
        this.application = application;
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public Application getApplication() {
        return application;
    }
}
