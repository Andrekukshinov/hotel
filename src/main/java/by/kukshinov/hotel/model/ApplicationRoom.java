package by.kukshinov.hotel.model;

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

    @Override
    public String toString() {
        return "ApplicationRoom{" +
                "application=" + application +
                ", room=" + room +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApplicationRoom that = (ApplicationRoom) o;

        if (getApplication() != null ? !getApplication().equals(that.getApplication()) : that.getApplication() != null) {
            return false;
        }
        return getRoom() != null ? getRoom().equals(that.getRoom()) : that.getRoom() == null;
    }

    @Override
    public int hashCode() {
        int result = getApplication() != null ? getApplication().hashCode() : 0;
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        return result;
    }
}
