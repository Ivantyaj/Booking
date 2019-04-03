package com.booking.model.repository;

import com.booking.model.entity.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<HotelRoom, Long> {
}