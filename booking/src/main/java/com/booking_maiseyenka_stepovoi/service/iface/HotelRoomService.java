package com.booking_maiseyenka_stepovoi.service.iface;

import com.booking_maiseyenka_stepovoi.model.entity.HotelRoom;

import java.util.List;

public interface HotelRoomService {
    //void save(HotelRoom object);
    HotelRoom save(HotelRoom object);

    HotelRoom getById(Long id);

    List<HotelRoom> getAll();

    HotelRoom update(HotelRoom employee);

    void delete(Long id);

    HotelRoom findById(Long id);

    boolean exists(Long id);
}
