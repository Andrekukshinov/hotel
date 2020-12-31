package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.sql.Date;
import java.time.LocalDate;

public class Application {
    private long id;
    private byte personAmount;
    private ApartmentType type;
    private LocalDate arrivalDate;
    private LocalDate leavingDate;
    private ApplicationStatus status;
    private long userId;

    public Application() {
    }

    public Application(byte personAmount, ApartmentType type, LocalDate arrivalDate, LocalDate leavingDate, ApplicationStatus status, long userId) {
        this.personAmount = personAmount;
        this.type = type;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.status = status;
        this.userId = userId;
    }

    public Application(long id, byte personAmount, ApartmentType type, LocalDate arrivalDate, LocalDate leavingDate, ApplicationStatus status, long userId) {
        this.id = id;
        this.personAmount = personAmount;
        this.type = type;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.status = status;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

        Application thatApplication = (Application) that;

        if (getId() != thatApplication.getId()) {
            return false;
        }
        if (getPersonAmount() != thatApplication.getPersonAmount()) {
            return false;
        }
        if (getUserId() != thatApplication.getUserId()) {
            return false;
        }
        if (getType() != thatApplication.getType()) {
            return false;
        }
        LocalDate thatApplicationArrivalDate = thatApplication.getArrivalDate();
        if (getArrivalDate() != null ? !getArrivalDate().equals(thatApplicationArrivalDate) : thatApplicationArrivalDate != null) {
            return false;
        }
        LocalDate thatApplicationLeavingDate = thatApplication.getLeavingDate();
        if (getLeavingDate() != null ? !getLeavingDate().equals(thatApplicationLeavingDate) : thatApplicationLeavingDate != null) {
            return false;
        }
        return getStatus() == thatApplication.getStatus();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) getPersonAmount();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getArrivalDate() != null ? getArrivalDate().hashCode() : 0);
        result = 31 * result + (getLeavingDate() != null ? getLeavingDate().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }
}
// TODO: 14.12.2020 ask about if it's fine for update method 2 update only 1 field if if others cannot be updated acc 2 task
