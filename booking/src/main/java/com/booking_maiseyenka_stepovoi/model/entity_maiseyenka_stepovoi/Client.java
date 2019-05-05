package com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne//(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "fio")
    private String fio;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;

    @Column(name = "count_visiting")
    private Long visitingCount;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

    @Column(name = "need_call")
    private boolean needCall = false;

    @Column(name = "last_message", length = 1000)
    private String lastMessage;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "card_cvv")
    private String cardCVV;
    @Column(name = "card_date")
    private String cardDate;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
//    private Set<HotelRoom> hotelRoomSet;

    public Client() {
    }

    public Client(String email, String phone) {
        this.email = email;
        this.phone = phone;
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

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (needCall != client.needCall) return false;
        if (cardCVV != client.cardCVV) return false;
        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (user != null ? !user.equals(client.user) : client.user != null) return false;
        if (fio != null ? !fio.equals(client.fio) : client.fio != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;

        if (visitingCount != null ? !visitingCount.equals(client.visitingCount) : client.visitingCount != null)
            return false;
//        if (discount != null ? !discount.equals(client.discount) : client.discount != null) return false;
        if (lastMessage != null ? !lastMessage.equals(client.lastMessage) : client.lastMessage != null) return false;
        if (cardHolder != null ? !cardHolder.equals(client.cardHolder) : client.cardHolder != null) return false;
        return cardDate != null ? cardDate.equals(client.cardDate) : client.cardDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (visitingCount != null ? visitingCount.hashCode() : 0);
//        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (needCall ? 1 : 0);
        result = 31 * result + (lastMessage != null ? lastMessage.hashCode() : 0);
        result = 31 * result + (cardHolder != null ? cardHolder.hashCode() : 0);
        result = 31 * result + (cardCVV != null ? cardCVV.hashCode() : 0);
        result = 31 * result + (cardDate != null ? cardDate.hashCode() : 0);
        return result;
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
//
//    public Set<HotelRoom> getHotelRoomSet() {
//        return hotelRoomSet;
//    }
//
//    public void setHotelRoomSet(Set<HotelRoom> hotelRoomSet) {
//        this.hotelRoomSet = hotelRoomSet;
//    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
             //   ", user=" + user +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", visitingCount=" + visitingCount +
//                ", discount=" + discount +
                ", needCall=" + needCall +
                ", lastMessage='" + lastMessage + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", cardCVV=" + cardCVV +
                ", cardDate='" + cardDate + '\'' +
                '}';
    }
}
