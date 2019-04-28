package com.booking.service.iface;

import com.booking.model.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();

    Booking save(Booking booking);
}
