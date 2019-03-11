package com.booking.service.iface;

import com.booking.model.entity.Room;

public interface RoomService {
    void save(Room object);

    Room getById(Long id);
}
