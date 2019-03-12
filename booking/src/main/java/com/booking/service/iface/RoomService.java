package com.booking.service.iface;

import com.booking.model.entity.HotelRoom;

import java.util.List;

public interface RoomService {
    void save(HotelRoom object);

    HotelRoom getById(Long id);

    List<HotelRoom> getAll();
}
