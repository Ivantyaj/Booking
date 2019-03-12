package com.booking.service.repository;

import com.booking.model.entity.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<HotelRoom, Long> {
}
