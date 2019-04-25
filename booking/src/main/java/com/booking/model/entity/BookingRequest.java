package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookingRequest {



    Long roomId;

    Integer countVisitors;
    //Пока стринг, я хз как тут лучше, можешь сам подумать
    String dataFrom;
    String dataTo;

    String clientName;
    String clientEmail;
    String clientPhone;

    String message;

    String cardHolder;
    String cardCVV;
    String cardData;

    boolean needBackCall;

    ArrayList<String> servises;
    ArrayList<String> extraServises;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public ArrayList<String> getServises() {
        return servises;
    }

    public void setServises(ArrayList<String> servises) {
        this.servises = servises;
    }

    public ArrayList<String> getExtraServises() {
        return extraServises;
    }

    public void setExtraServises(ArrayList<String> extraServises) {
        this.extraServises = extraServises;
    }


    @Override
    public String toString() {
        return "BookingRequest{" +
                "roomId=" + roomId +
                ", servises=" + servises +
                ", extraServises=" + extraServises +
                '}';
    }

}
