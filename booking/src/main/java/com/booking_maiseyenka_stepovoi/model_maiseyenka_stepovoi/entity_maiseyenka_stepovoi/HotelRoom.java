package com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
@EntityListeners(AuditingEntityListener.class)
public class HotelRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "people_amount", nullable = false)
    private Long peopleAmount;

    //@JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private HotelRoomType hotelRoomType;

    @OneToMany(mappedBy = "hotelRoom")
    private List<Booking> booking;

    @Column(name = "price", length = 1000)
    private Double price;

    @Column(name = "picture_url", length = 1000)
    private String url = "https://pp.userapi.com/c854028/v854028525/2b94f/DcPoMxV66Xk.jpg";

    @Column(name = "description", length = 1000)
    private String description = "Пока нет описания";

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

    public HotelRoom(Long roomId) {
        this.id = roomId;
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

    public Long getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(Long peopleAmount) {
        this.peopleAmount = peopleAmount;
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
                Objects.equals(peopleAmount, hotelRoom.peopleAmount) &&
                Objects.equals(hotelRoomType, hotelRoom.hotelRoomType) &&
                Objects.equals(booking, hotelRoom.booking) &&
                Objects.equals(price, hotelRoom.price) &&
                Objects.equals(url, hotelRoom.url) &&
                Objects.equals(description, hotelRoom.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, peopleAmount, hotelRoomType, booking, price, url, description);
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", peopleAmount=" + peopleAmount +
                ", hotelRoomType=" + hotelRoomType +
                ", booking_maiseyenka_stepovoi=" + booking +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
