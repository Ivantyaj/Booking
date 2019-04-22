package com.booking.service.iface;

import com.booking.model.entity.HotelRoom;

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
