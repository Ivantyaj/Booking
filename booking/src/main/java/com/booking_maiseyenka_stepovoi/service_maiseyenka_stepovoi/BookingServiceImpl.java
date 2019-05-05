package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.exceptions_maiseyenka_stepovoi.NotFoundException;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.Booking;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.repository_maiseyenka_stepovoi.BookingRepository;
import com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi.BookingService;
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
