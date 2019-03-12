package com.booking.controller;


import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.RoomService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "RoomController")
@RequestMapping(value = "/hotel/rooms")
@Api(tags = "hotel")
public class HotelRoomController {

    private final RoomService roomService;

    @Autowired
    public HotelRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @ApiOperation(value = "Create hotelRoom", response = GenericResponse.class, notes = "room_create")
    @PostMapping(value = "/create")
    public ResponseEntity<GenericResponse> createRoom(@RequestBody HotelRoom hotelRoom) {
        System.err.println(hotelRoom);
        roomService.save(hotelRoom);
        return new ResponseEntity<>(new GenericResponse("message"), HttpStatus.OK);
    }

    @ApiOperation(value = "Get room", response = GenericResponse.class, notes = "get_room")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponse> getRoom(@PathVariable("id") Long id) {
        HotelRoom hotelRoom = roomService.getById(id);
        return new ResponseEntity<>(new GenericResponse("ROOM " + hotelRoom), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all rooms", response = GenericResponse.class, notes = "get_room")
    @GetMapping(value = "/")
    public ResponseEntity<GenericResponse> getAllRooms() {
        List<HotelRoom> hotelRoomList = roomService.getAll();
        return new ResponseEntity<>(new GenericResponse("ROOM " + hotelRoomList), HttpStatus.OK);
    }


}
