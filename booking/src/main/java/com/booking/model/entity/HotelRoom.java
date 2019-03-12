package com.booking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
public class HotelRoom implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_hotel_room")
    @SequenceGenerator(name = "seq_gen_hotel_room",
            sequenceName = "seq_hotel_room", allocationSize = 1)
    private Long id;

    @Column(name = "room_amount", nullable = false)
    private Long roomAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private HotelRoomStatus status;

    @Column(name = "price", length = 1000)
    private Double price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type")
    private HotelRoomType hotelRoomType;

    public HotelRoom() {
        this.status = HotelRoomStatus.FREE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(Long roomAmount) {
        this.roomAmount = roomAmount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HotelRoomStatus getStatus() {
        return status;
    }

    public void setStatus(HotelRoomStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public HotelRoomType getHotelRoomType() {
        return hotelRoomType;
    }

    public void setHotelRoomType(HotelRoomType hotelRoomType) {
        this.hotelRoomType = hotelRoomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return Objects.equals(id, hotelRoom.id) &&
                Objects.equals(roomAmount, hotelRoom.roomAmount) &&
                Objects.equals(client, hotelRoom.client) &&
                status == hotelRoom.status &&
                Objects.equals(price, hotelRoom.price) &&
                hotelRoomType == hotelRoom.hotelRoomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomAmount, client, status, price, hotelRoomType);
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", roomAmount=" + roomAmount +
                ", status=" + status +
                ", price=" + price +
                ",hotelRoomType=" + hotelRoomType +
                '}';

    }
}
