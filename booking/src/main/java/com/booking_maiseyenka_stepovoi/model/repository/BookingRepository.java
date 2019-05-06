package com.booking_maiseyenka_stepovoi.model.repository;

import com.booking_maiseyenka_stepovoi.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findById(Long id);
}
