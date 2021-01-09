package by.kukshinov.hotel.model;

import by.kukshinov.hotel.model.enums.ApartmentType;
import by.kukshinov.hotel.model.enums.ApplicationStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Application implements Entity{
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
}
// TODO: 14.12.2020 ask about if it's fine for update method 2 update only 1 field if if others cannot be updated acc 2 task
