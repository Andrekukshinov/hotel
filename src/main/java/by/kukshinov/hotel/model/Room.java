package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;

import java.math.BigDecimal;

public class Room implements Entity {
    private Long id;
    private int number;
    private ApartmentType roomType;
    private byte capacity;
    private Boolean isAvailable;
    private BigDecimal price;

    public Room(Long id, int number, ApartmentType roomType, byte capacity,
                Boolean isAvailable, BigDecimal price) {
        this.id = id;
        this.number = number;
        this.roomType = roomType;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public Room() {
    }

    public Long getId() {
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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
                ", roomStatus=" + isAvailable +
                ", price=" + price +
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

        Room room = (Room) o;

        if (getNumber() != room.getNumber()) {
            return false;
        }
        if (getCapacity() != room.getCapacity()) {
            return false;
        }
        if (getId() != null ? !getId().equals(room.getId()) : room.getId() != null) {
            return false;
        }
        if (getRoomType() != room.getRoomType()) {
            return false;
        }
        if (getIsAvailable() != null ? !getIsAvailable().equals(room.getIsAvailable()) : room.getIsAvailable() != null) {
            return false;
        }
        return getPrice() != null ? getPrice().equals(room.getPrice()) : room.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getNumber();
        result = 31 * result + (getRoomType() != null ? getRoomType().hashCode() : 0);
        result = 31 * result + (int) getCapacity();
        result = 31 * result + (getIsAvailable() != null ? getIsAvailable().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }
}
