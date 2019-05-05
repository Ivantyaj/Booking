package com.booking_maiseyenka_stepovoi.controller;

import com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi.HotelRoom;
import com.booking_maiseyenka_stepovoi.service.iface_maiseyenka_stepovoi.HotelRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
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

    @ApiOperation(value = "get all rooms", response = ResponseEntity.class, notes = "get_all_room")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelRoom> getAllRoom() {
        return hotelRoomService.getAll();
    }

    @ApiOperation(value = "create room", response = ResponseEntity.class, notes = "create_room")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelRoom> createRoom(@RequestBody HotelRoom employee) {
        try {
            HotelRoom result = hotelRoomService.save(employee);
            URI uri = URI.create("http://localhost:8080/hotel/rooms" + result.getId());
            return ResponseEntity.created(uri).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @ApiOperation(value = "updating room", response = ResponseEntity.class, notes = "update_room")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelRoom> updateRoom(@RequestBody HotelRoom employee) {
        if (employee.getId() == null) {
            System.out.println("ne  Hello update");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            HotelRoom hotelRoom = hotelRoomService.findById(employee.getId());
            hotelRoom.setPrice(employee.getPrice());
            hotelRoom.setPeopleAmount(employee.getPeopleAmount());
            hotelRoom.setUrl(employee.getUrl());
            hotelRoom.setDescription(employee.getDescription());
            hotelRoomService.save(hotelRoom);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "deleting room", response = ResponseEntity.class, notes = "delete_room")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        hotelRoomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
