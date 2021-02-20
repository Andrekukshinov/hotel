package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Application implements Entity {
    private Long id;
    private byte personAmount;
    private ApartmentType type;
    private LocalDate arrivalDate;
    private LocalDate leavingDate;
    private ApplicationStatus status;
    private Long userId;
    private BigDecimal totalPrice;
    private Long roomId;

    public Application() {
    }

    public Application(byte personAmount, ApartmentType type, LocalDate arrivalDate, LocalDate leavingDate, ApplicationStatus status, Long userId) {
        this.personAmount = personAmount;
        this.type = type;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.status = status;
        this.userId = userId;
    }

    public Application(Long id, byte personAmount, ApartmentType type, LocalDate arrivalDate, LocalDate leavingDate, ApplicationStatus status, BigDecimal totalPrice, Long roomId, Long userId) {
        this.id = id;
        this.personAmount = personAmount;
        this.type = type;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.status = status;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public byte getPersonAmount() {
        return personAmount;
    }

    public void setPersonAmount(byte personAmount) {
        this.personAmount = personAmount;
    }

    public ApartmentType getType() {
        return type;
    }

    public void setType(ApartmentType type) {
        this.type = type;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", personAmount=" + personAmount +
                ", type=" + type +
                ", arrivalDate=" + arrivalDate +
                ", leavingDate=" + leavingDate +
                ", status=" + status +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", roomId=" + roomId +
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

        Application that = (Application) o;

        if (getPersonAmount() != that.getPersonAmount()) {
            return false;
        }
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getType() != that.getType()) {
            return false;
        }
        if (getArrivalDate() != null ? !getArrivalDate().equals(that.getArrivalDate()) : that.getArrivalDate() != null) {
            return false;
        }
        if (getLeavingDate() != null ? !getLeavingDate().equals(that.getLeavingDate()) : that.getLeavingDate() != null) {
            return false;
        }
        if (getStatus() != that.getStatus()) {
            return false;
        }
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) {
            return false;
        }
        if (getTotalPrice() != null ? !getTotalPrice().equals(that.getTotalPrice()) : that.getTotalPrice() != null) {
            return false;
        }
        return getRoomId() != null ? getRoomId().equals(that.getRoomId()) : that.getRoomId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (int) getPersonAmount();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getArrivalDate() != null ? getArrivalDate().hashCode() : 0);
        result = 31 * result + (getLeavingDate() != null ? getLeavingDate().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
        result = 31 * result + (getRoomId() != null ? getRoomId().hashCode() : 0);
        return result;
    }
}
