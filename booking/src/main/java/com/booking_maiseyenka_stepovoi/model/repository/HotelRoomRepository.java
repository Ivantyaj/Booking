package com.booking_maiseyenka_stepovoi.model.repository;

import com.booking_maiseyenka_stepovoi.model.entity.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
}
