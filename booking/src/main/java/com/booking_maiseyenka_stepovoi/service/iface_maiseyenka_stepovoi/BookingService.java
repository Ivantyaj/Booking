package com.booking_maiseyenka_stepovoi.service.iface_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();

    Booking save(Booking booking);

    Booking findById(Long id);

    void deleteById(Long id);
}
