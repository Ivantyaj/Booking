package com.booking.service;

import com.booking.service.repository.RoomRepository;
import com.booking.model.entity.Room;
import com.booking.service.iface.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void create(Room object) {
        roomRepository.save(object);
    }

}
