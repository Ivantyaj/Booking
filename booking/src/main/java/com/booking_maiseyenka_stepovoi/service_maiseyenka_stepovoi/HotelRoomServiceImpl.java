package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.exceptions_maiseyenka_stepovoi.NotFoundException;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.HotelRoom;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.repository_maiseyenka_stepovoi.HotelRoomRepository;
import com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelRoomServiceImpl implements HotelRoomService {

    private HotelRoomRepository hotelRoomRepository;

    @Autowired
    public HotelRoomServiceImpl(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    @Override
    public HotelRoom getById(Long id) {
        return hotelRoomRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Номер не найден")
        );
    }

    @Override
    public List<HotelRoom> getAll() {
        return hotelRoomRepository.findAll();
    }

    @Override
    @Transactional
    public HotelRoom save(HotelRoom employee) {
        return hotelRoomRepository.save(employee);
    }

    @Override
    public HotelRoom update(HotelRoom employee) {
        return hotelRoomRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        hotelRoomRepository.deleteById(id);
    }

    @Override
    public HotelRoom findById(Long id) {
        return hotelRoomRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Пользователь не найден"));
    }

    @Override
    public boolean exists(Long id) {
        return hotelRoomRepository.existsById(id);
    }


}
