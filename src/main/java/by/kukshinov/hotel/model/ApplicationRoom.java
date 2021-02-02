package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApplicationRoom {
    private final Long applicationId;
    private final byte personAmount;
    private final ApartmentType type;
    private final LocalDate arrivalDate;
    private final LocalDate leavingDate;
    private final ApplicationStatus applicationStatus;
    private final Long userId;
    private final BigDecimal totalPrice;

    private final Long roomId;
    private final int number;
    private final ApartmentType roomType;
    private final byte capacity;
    private final Boolean isAvailable;
    private final BigDecimal price;

    public ApplicationRoom(Application application, Room room) {
        this.applicationStatus = application.getStatus();
        this.personAmount = application.getPersonAmount();
        this.type = application.getType();
        this.arrivalDate = application.getArrivalDate();
        this.leavingDate = application.getLeavingDate();
        this.userId = application.getUserId();
        this.applicationId = application.getId();
        this.totalPrice = application.getTotalPrice();

        this.number = room.getNumber();
        this.roomType = room.getRoomType();
        this.capacity = room.getCapacity();
        this.isAvailable = room.getIsAvailable();
        this.price = room.getPrice();
        this.roomId = room.getId();
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public byte getPersonAmount() {
        return personAmount;
    }

    public ApartmentType getType() {
        return type;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getRoomId() {
        return roomId;
    }

    public int getNumber() {
        return number;
    }

    public ApartmentType getRoomType() {
        return roomType;
    }

    public byte getCapacity() {
        return capacity;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ApplicationRoom{" +
                "applicationId=" + applicationId +
                ", personAmount=" + personAmount +
                ", type=" + type +
                ", arrivalDate=" + arrivalDate +
                ", leavingDate=" + leavingDate +
                ", applicationStatus=" + applicationStatus +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", roomId=" + roomId +
                ", number=" + number +
                ", roomType=" + roomType +
                ", capacity=" + capacity +
                ", isAvailable=" + isAvailable +
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

        ApplicationRoom that = (ApplicationRoom) o;

        if (personAmount != that.personAmount) {
            return false;
        }
        if (number != that.number) {
            return false;
        }
        if (capacity != that.capacity) {
            return false;
        }
        if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null) {
            return false;
        }
        if (type != that.type) {
            return false;
        }
        if (arrivalDate != null ? !arrivalDate.equals(that.arrivalDate) : that.arrivalDate != null) {
            return false;
        }
        if (leavingDate != null ? !leavingDate.equals(that.leavingDate) : that.leavingDate != null) {
            return false;
        }
        if (applicationStatus != that.applicationStatus) {
            return false;
        }
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
            return false;
        }
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) {
            return false;
        }
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) {
            return false;
        }
        if (roomType != that.roomType) {
            return false;
        }
        if (isAvailable != null ? !isAvailable.equals(that.isAvailable) : that.isAvailable != null) {
            return false;
        }
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = applicationId != null ? applicationId.hashCode() : 0;
        result = 31 * result + (int) personAmount;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (leavingDate != null ? leavingDate.hashCode() : 0);
        result = 31 * result + (applicationStatus != null ? applicationStatus.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (int) capacity;
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
