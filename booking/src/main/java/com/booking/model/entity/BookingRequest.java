package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookingRequest {

    //Надо сделать такую таблицу
    //Либо переделать таблицу букинг
    //Либо на запрос отдавать часть таблицы, а фанные клиента искать по айди
    //Или все это распихать по нужным таблицам и сделать таблицу с клиентами
    //ожидающими звонка, но не логично, мб добавить поле в клиенту
    //Тип булеан ждет / не ждет звонка
    //Да вот так збс
    //Короче из этого класса надо распихивать все по соответствующим таблицам
    //В клиента по карте нужно добавить данные или + таблица и айди

    //Обнови свой скрипт БД (Надеюсь не забуду сменить скрипт) пока меняю только клиента

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
