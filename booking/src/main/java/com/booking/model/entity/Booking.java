package com.booking.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "booking")
@EntityListeners(AuditingEntityListener.class)
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    private HotelRoom hotelRoom;


    //@JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "arrival_date")
    private Instant arrivalDate;

    @Column(name = "leaving_date")
    private Instant leavingDate;

    @Column(name = "human_amount")
    private Long humanAmount;

    @Column(name = "status")
    private Boolean status = false;

    public Booking() {
    }

    public Booking(Long roomId, Client client, Instant arrivalDate, Instant leavingDate, Long humanAmount) {
        this.hotelRoom = new HotelRoom(roomId);
        this.client = client;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.humanAmount = humanAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Instant getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Instant getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Instant leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Long getHumanAmount() {
        return humanAmount;
    }

    public void setHumanAmount(Long humanAmount) {
        this.humanAmount = humanAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (id != null ? !id.equals(booking.id) : booking.id != null) return false;
        if (hotelRoom != null ? !hotelRoom.equals(booking.hotelRoom) : booking.hotelRoom != null) return false;
        if (client != null ? !client.equals(booking.client) : booking.client != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(booking.arrivalDate) : booking.arrivalDate != null) return false;
        if (leavingDate != null ? !leavingDate.equals(booking.leavingDate) : booking.leavingDate != null) return false;
        if (humanAmount != null ? !humanAmount.equals(booking.humanAmount) : booking.humanAmount != null) return false;
        return status != null ? status.equals(booking.status) : booking.status == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (hotelRoom != null ? hotelRoom.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (leavingDate != null ? leavingDate.hashCode() : 0);
        result = 31 * result + (humanAmount != null ? humanAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
//                ", hotelRoom=" + hotelRoom +
                ", client=" + client +
                ", arrivalDate=" + arrivalDate +
                ", leavingDate=" + leavingDate +
                ", humanAmount=" + humanAmount +
                ", status=" + status +
                '}';
    }
}