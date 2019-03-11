package com.booking.controller;


import com.booking.model.entity.Room;
import com.booking.service.iface.RoomService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "RoomController")
@RequestMapping(value = "/hotel/rooms")
@Api(tags = "hotel")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @ApiOperation(value = "Create room", response = GenericResponse.class, notes = "room_create")
    @PostMapping(value = "/create")
    public ResponseEntity<GenericResponse> createRoom(@RequestBody Room room) {
        roomService.create(room);
        return new ResponseEntity<>(new GenericResponse("message"), HttpStatus.OK);
    }


}
