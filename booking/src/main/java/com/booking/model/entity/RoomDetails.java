package com.booking.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "hotel_room_details")
class RoomDetails {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "hotel_room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_comfort_type")
    private RoomComfortType roomComfortType;

    public RoomDetails() {
    }
    //TODO need to add a lot


}
