package com.booking.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "hotel_room")
public class Room extends BaseEntity {

    @Id
    private Long id;

    @OneToOne
    private RoomDetails roomDetails;

    public Room() {
    }
    //TODO need to add a lot
}
