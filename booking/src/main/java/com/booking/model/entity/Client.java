package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "client")
public class Client implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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
        if (!Objects.equals(id, client.id)) return false;
        if (!Objects.equals(user, client.user)) return false;
        if (!Objects.equals(name, client.name)) return false;
        if (!Objects.equals(surname, client.surname)) return false;
        if (!Objects.equals(phone, client.phone)) return false;
        if (!Objects.equals(email, client.email)) return false;
        if (!Objects.equals(passportNumber, client.passportNumber))
            return false;
        if (!Objects.equals(visitingCount, client.visitingCount))
            return false;
        if (!Objects.equals(discount, client.discount)) return false;
        return Objects.equals(hotelRoomSet,
                client.hotelRoomSet);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (visitingCount != null ? visitingCount.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (hotelRoomSet != null ? hotelRoomSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", visitingCount=" + visitingCount +
                ", discount=" + discount +
                ", hotelRoomSet=" + hotelRoomSet +
                '}';
    }
}
