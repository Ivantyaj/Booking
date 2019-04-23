package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "room_type")
public class HotelRoomType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "human_amount")
    private Long humanAmount;

    @Column(name = "name", nullable = false)
    private String type_name;

    @Column(name = "description", length = 1000)
    private String description;


//    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Answer> answer;
//}
    //@JsonIgnore
    //@OneToOne(mappedBy = "hotelRoomType", cascade = CascadeType.ALL, orphanRemoval = true)
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private HotelRoom hotelRoom;

    public HotelRoomType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHumanAmount() {
        return humanAmount;
    }

    public void setHumanAmount(Long humanAmount) {
        this.humanAmount = humanAmount;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public HotelRoom getHotelRoom() {
//        return hotelRoom;
//    }

    //public void setHotelRoom(HotelRoom hotelRoom) {
    //    this.hotelRoom = hotelRoom;
    //}

    @Override
    public String toString() {
        return "HotelRoomType{" +
                "id=" + id +
                ", humanAmount=" + humanAmount +
                ", type_name=" + type_name +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoomType hotelRoomType = (HotelRoomType) o;
        return Objects.equals(id, hotelRoomType.id) &&
                Objects.equals(humanAmount, hotelRoomType.humanAmount) &&
                type_name.equals(hotelRoomType.type_name) &&
                Objects.equals(description, hotelRoomType.description); //&&
                //Objects.equals(hotelRoom, hotelRoomType.hotelRoom);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, humanAmount, type_name, description, hotelRoom);
//    }

}
