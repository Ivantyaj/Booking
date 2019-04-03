package com.booking.service;

import com.booking.exceptions.NotFoundException;
import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.HotelRoomService;
import com.booking.model.repository.HotelRoomRepository;

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
    @Transactional
    public void save(HotelRoom object) {
        hotelRoomRepository.save(object);
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


}
