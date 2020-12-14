package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.sql.Date;

public class Application {
    private long id;
    private byte personAmount;
    private ApartmentType type;
    private Date arrivalDate;
    private Date leavingDate;
    private ApplicationStatus status;
    private long userId;

    public Application(long id, byte personAmount, ApartmentType type, Date arrivalDate, Date leavingDate, ApplicationStatus status,long userId) {
        this.id = id;
        this.personAmount = personAmount;
        this.type = type;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.userId = userId;
        this.status = status;
    }

    public Application() {
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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public ApartmentType getType() {
        return type;
    }

    public void setType(ApartmentType type) {
        this.type = type;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
// TODO: 14.12.2020 ask about if it's fine for update method 2 update only 1 field if if others cannot be updated acc 2 task
