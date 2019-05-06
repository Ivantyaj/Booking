package com.booking_maiseyenka_stepovoi.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookingRequest {

    Long roomId;

    Integer countVisitors;

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

    public Integer getCountVisitors() {
        return countVisitors;
    }

    public void setCountVisitors(Integer countVisitors) {
        this.countVisitors = countVisitors;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    public String getDataTo() {
        return dataTo;
    }

    public void setDataTo(String dataTo) {
        this.dataTo = dataTo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getCardData() {
        return cardData;
    }

    public void setCardData(String cardData) {
        this.cardData = cardData;
    }

    public boolean isNeedBackCall() {
        return needBackCall;
    }

    public void setNeedBackCall(boolean needBackCall) {
        this.needBackCall = needBackCall;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "roomId=" + roomId +
                ", countVisitors=" + countVisitors +
                ", dataFrom='" + dataFrom + '\'' +
                ", dataTo='" + dataTo + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", message='" + message + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", cardCVV='" + cardCVV + '\'' +
                ", cardData='" + cardData + '\'' +
                ", needBackCall=" + needBackCall +
                ", servises=" + servises +
                ", extraServises=" + extraServises +
                '}';
    }

}
