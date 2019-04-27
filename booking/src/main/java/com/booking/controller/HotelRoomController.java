package com.booking.controller;

import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.HotelRoomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController(value = "RoomController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/hotel/rooms")
@Api(tags = "hotel")
public class HotelRoomController extends BaseController {

    private final HotelRoomService hotelRoomService;

    @Autowired
    public HotelRoomController(HotelRoomService hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
    }

//    @ApiOperation(value = "Create hotelRoom", response = GenericResponse.class, notes = "room_create")
//    @PostMapping(value = "/create")
//    public ResponseEntity<GenericResponse> createRoom(@RequestBody HotelRoom hotelRoom) {
//        System.err.println(hotelRoom);
//        hotelRoomService.save(hotelRoom);
//        return new ResponseEntity<>(new GenericResponse("message"), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Get room", response = GenericResponse.class, notes = "get_room")
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<GenericResponse> getRoom(@PathVariable("id") Long id) {
//        HotelRoom hotelRoom = hotelRoomService.getById(id);
//        return new ResponseEntity<>(new GenericResponse("ROOM " + hotelRoom), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Get all rooms", response = GenericResponse.class, notes = "get_room")
//    @GetMapping(value = "/")
//    public ResponseEntity<GenericResponse> getAllRooms() {
//        List<HotelRoom> hotelRoomList = hotelRoomService.getAll();
//        return new ResponseEntity<>(new GenericResponse("ROOM " + hotelRoomList), HttpStatus.OK);
//    }

    //ADD to test crud

    //GetAll не правильно отрабатывает
    //Response body: [{"id":1,"roomAmount":1,"status":"FREE","price":1.0},{"id":2,"roomAmount":2,"status":"FREE","price":2.0}
    //Там два параметра с Json ignore, если их убрать, то запросы не работают
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelRoom> getAllRoom() {
        System.out.println("buckle");
        return hotelRoomService.getAll();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelRoom> createRoom(@RequestBody HotelRoom employee) throws URISyntaxException {
        try {
            HotelRoom result = hotelRoomService.save(employee);
            URI uri = URI.create("http://localhost:8080/hotel/rooms" + result.getId());
            return ResponseEntity.created(uri).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelRoom> updateRoom(@RequestBody HotelRoom employee) {
        if (employee.getId() == null) {
            System.out.println("ne  Hello update");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            HotelRoom hotelRoom = hotelRoomService.findById(employee.getId());
            hotelRoom.setPrice(employee.getPrice());
            hotelRoom.setRoomAmount(employee.getRoomAmount());
            hotelRoom.setUrl(employee.getUrl());
            hotelRoom.setDescription(employee.getDescription());
            hotelRoomService.save(hotelRoom);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        hotelRoomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
