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

    @Column(name = "fio")
    private String fio;
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

    @Column(name = "need_call")
    private boolean needCall = false;

    @Column(name = "last_message", length = 1000)
    private String lastMessage;

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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public boolean isNeedCall() {
        return needCall;
    }

    public void setNeedCall(boolean needCall) {
        this.needCall = needCall;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return needCall == client.needCall &&
                Objects.equals(id, client.id) &&
                Objects.equals(user, client.user) &&
                Objects.equals(fio, client.fio) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(email, client.email) &&
                Objects.equals(passportNumber, client.passportNumber) &&
                Objects.equals(visitingCount, client.visitingCount) &&
                Objects.equals(discount, client.discount) &&
                Objects.equals(lastMessage, client.lastMessage) &&
                Objects.equals(hotelRoomSet, client.hotelRoomSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, fio, phone, email, passportNumber, visitingCount, discount, needCall, lastMessage, hotelRoomSet);
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
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
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", user=" + user +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", visitingCount=" + visitingCount +
                ", discount=" + discount +
                ", needCall=" + needCall +
                ", lastMessage='" + lastMessage + '\'' +
                ", hotelRoomSet=" + hotelRoomSet +
                '}';
    }
}
