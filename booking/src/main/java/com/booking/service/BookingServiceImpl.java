package com.booking.service;

import com.booking.exceptions.NotFoundException;
import com.booking.model.entity.Booking;
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
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Запись о бронировании не найдена"));
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }


}
