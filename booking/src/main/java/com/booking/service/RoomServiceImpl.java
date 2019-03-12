package com.booking.service;

import com.booking.exceptions.NotFoundException;
import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.RoomService;
import com.booking.service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public void save(HotelRoom object) {
        roomRepository.save(object);
    }

    @Override
    public HotelRoom getById(Long id) {
        return roomRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Номер не найден")
        );
    }

    @Override
    public List<HotelRoom> getAll() {
        return roomRepository.findAll();
    }


}
