package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "client")
public class Client implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "passport_num")
    private String passportNumber;
    @Column(name = "count_visiting")
    private Long visitingCount;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "client_discount",
            joinColumns = {@JoinColumn(name = "id_client")},
            inverseJoinColumns = {@JoinColumn(name = "id_discount")}
    )
    private Set<Discount> discounts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<HotelRoom> hotelRoomSet;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Long getVisitingCount() {
        return visitingCount;
    }

    public void setVisitingCount(Long visitingCount) {
        this.visitingCount = visitingCount;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Set<HotelRoom> getHotelRoomSet() {
        return hotelRoomSet;
    }

    public void setHotelRoomSet(Set<HotelRoom> hotelRoomSet) {
        this.hotelRoomSet = hotelRoomSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(user, client.user) &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(email, client.email) &&
                Objects.equals(passportNumber, client.passportNumber) &&
                Objects.equals(visitingCount, client.visitingCount) &&
                Objects.equals(discounts, client.discounts) &&
                Objects.equals(hotelRoomSet, client.hotelRoomSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, surname, phone, email, passportNumber, visitingCount, discounts, hotelRoomSet);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", hotelRoomSet=" + hotelRoomSet +
                '}';
    }
}
