package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "client")
public class Client implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<HotelRoom> hotelRoomList;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<HotelRoom> getHotelRoomList() {
        return hotelRoomList;
    }

    public void setHotelRoomList(Set<HotelRoom> hotelRoomList) {
        this.hotelRoomList = hotelRoomList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(hotelRoomList, client.hotelRoomList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotelRoomList);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", hotelRoomList=" + hotelRoomList +
                '}';
    }
}
