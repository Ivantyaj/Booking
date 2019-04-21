package com.booking.controller;

import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.HotelRoomService;
import com.booking.utils.logging.GenericResponse;

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
import java.net.URISyntaxException;
import java.util.List;

@RestController(value = "RoomController")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
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
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HotelRoom> getAllRoom() {
        System.out.println("buckle");
        return hotelRoomService.getAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelRoom> createRoom(@RequestBody HotelRoom employee) throws URISyntaxException {
        try {
            HotelRoom result = hotelRoomService.save(employee);
            return ResponseEntity.created(new URI("http://localhost:8080/hotel/rooms" + result.getId())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<HotelRoom>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelRoom> updateRoom(@RequestBody HotelRoom employee) throws URISyntaxException {
        if (employee.getId() == null) {
            System.out.println("ne  Hello update");
            return new ResponseEntity<HotelRoom>(HttpStatus.NOT_FOUND);
        }

        try {
            System.out.println("Hello update");
            HotelRoom result = hotelRoomService.update(employee);

            return ResponseEntity.created(new URI("http://localhost:8080/hotel/rooms" + result.getId())).body(result);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<HotelRoom>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        hotelRoomService.delete(id);

        return ResponseEntity.ok().build();
    }
}
