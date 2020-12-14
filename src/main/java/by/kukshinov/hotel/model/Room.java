package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.RoomStatus;

import java.math.BigDecimal;

public class Room {
    private long id;
    private int number;
    private ApartmentType roomType;
    private byte capacity;
    private RoomStatus roomStatus;
    private BigDecimal price;
    private String pictureUrl;

    public Room(long id, int number, ApartmentType roomType, byte capacity,
                RoomStatus roomStatus, BigDecimal price, String pictureUrl) {
        this.id = id;
        this.number = number;
        this.roomType = roomType;
        this.capacity = capacity;
        this.roomStatus = roomStatus;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

    public Room() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApartmentType getRoomType() {
        return roomType;
    }

    public void setRoomType(ApartmentType roomType) {
        this.roomType = roomType;
    }

    public byte getCapacity() {
        return capacity;
    }

    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", roomType=" + roomType +
                ", capacity=" + capacity +
                ", roomStatus=" + roomStatus +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        Room thatRoom = (Room) that;

        if (getId() != thatRoom.getId()) {
            return false;
        }
        if (getNumber() != thatRoom.getNumber()) {
            return false;
        }
        if (getCapacity() != thatRoom.getCapacity()) {
            return false;
        }
        if (getRoomType() != thatRoom.getRoomType()) {
            return false;
        }
        if (getRoomStatus() != thatRoom.getRoomStatus()) {
            return false;
        }
        if (getPrice() != null ? !getPrice().equals(thatRoom.getPrice()) : thatRoom.getPrice() != null) {
            return false;
        }
        return getPictureUrl() != null ? getPictureUrl().equals(thatRoom.getPictureUrl()) : thatRoom.getPictureUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getNumber();
        result = 31 * result + (getRoomType() != null ? getRoomType().hashCode() : 0);
        result = 31 * result + (int) getCapacity();
        result = 31 * result + (getRoomStatus() != null ? getRoomStatus().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getPictureUrl() != null ? getPictureUrl().hashCode() : 0);
        return result;
    }
}
