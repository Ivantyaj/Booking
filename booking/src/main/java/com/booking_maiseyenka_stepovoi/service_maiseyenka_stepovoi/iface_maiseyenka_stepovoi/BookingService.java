package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();

    Booking save(Booking booking);

    Booking findById(Long id);

    void deleteById(Long id);
}
