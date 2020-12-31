package by.kukshinov.hotel.model;

import java.math.BigDecimal;

public class ApplicationRoom {
    private final Application application;
    private final Room room;
    private final BigDecimal totalCost;

    public ApplicationRoom(Application application, Room room, BigDecimal totalCost) {
        this.application = application;
        this.room = room;
        this.totalCost = totalCost;
    }

    public Application getApplication() {
        return application;
    }

    public Room getRoom() {
        return room;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        ApplicationRoom thatApplicationRoom = (ApplicationRoom) that;

        Application thatApplication = thatApplicationRoom.getApplication();
        if (getApplication() != null ? !getApplication().equals(thatApplication) : thatApplication != null) {
            return false;
        }
        Room thatRoom = thatApplicationRoom.getRoom();
        if (getRoom() != null ? !getRoom().equals(thatRoom) : thatRoom != null) {
            return false;
        }
        BigDecimal thatTotalCost = thatApplicationRoom.getTotalCost();
        return getTotalCost() != null ? getTotalCost().equals(thatTotalCost) : thatTotalCost == null;
    }

    @Override
    public int hashCode() {
        int result = getApplication() != null ? getApplication().hashCode() : 0;
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        result = 31 * result + (getTotalCost() != null ? getTotalCost().hashCode() : 0);
        return result;
    }
}
