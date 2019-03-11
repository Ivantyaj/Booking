package com.booking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
public class Room {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_amount")
    private Long roomAmount;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Client client;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status;

    @Column(name = "price", length = 1000)
    private Double price;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type")
    private RoomType roomType;

    public Room() {
        this.status = RoomStatus.FREE;
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

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(roomAmount, room.roomAmount) &&
                Objects.equals(client, room.client) &&
                status == room.status &&
                Objects.equals(price, room.price) &&
                roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomAmount, client, status, price, roomType);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomAmount=" + roomAmount +
                ", client=" + client +
                ", status=" + status +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}
