package com.booking.service;

import com.booking.model.entity.HotelRoom;
import com.booking.model.repository.BookingRepository;
import com.booking.service.iface.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<HotelRoom> getAll() {
        return bookingRepository.findAll();
    }
}
