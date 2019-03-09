package com.booking.controller;


import com.booking.model.entity.Room;
import com.booking.service.iface.BaseService;
import com.booking.utils.logging.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel/room")
public class RoomController {

    private final BaseService<Room, Long> baseService;

    @Autowired
    public RoomController(BaseService<Room, Long> baseService) {
        this.baseService = baseService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<GenericResponse> createRoom(@RequestBody Room room) {
        baseService.create(room);
        return new ResponseEntity<>(new GenericResponse("message"), HttpStatus.OK);
    }


}
