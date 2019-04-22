package com.booking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
@EntityListeners(AuditingEntityListener.class)
public class HotelRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_amount", nullable = false)
    private Long roomAmount;

    @JsonIgnore
    //Было OneToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private HotelRoomType hotelRoomType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;


    @Column(name = "price", length = 1000)
    private Double price;

    @Column(name = "picture_url", length = 1000)
    private String url;

    @Column(name = "description", length = 1000)
    private String description;

    public HotelRoom() {
    }

    //For tests
    public HotelRoom(Long id, Double price, String url, String description) {
        this.id = id;
        this.price = price;
        this.url = url;
        this.description = description;
    }
    public HotelRoom(Double price, String url, String description) {
        this.price = price;
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    //НАДО фиксить поля
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return Objects.equals(id, hotelRoom.id) &&
                Objects.equals(roomAmount, hotelRoom.roomAmount) &&
                Objects.equals(client, hotelRoom.client) &&
                Objects.equals(price, hotelRoom.price) &&
                hotelRoomType == hotelRoom.hotelRoomType;
    }



    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", roomAmount=" + roomAmount +
                ", hotelRoomType=" + hotelRoomType +
                ", client=" + client +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
