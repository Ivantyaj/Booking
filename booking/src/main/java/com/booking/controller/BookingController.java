package com.booking.controller;

import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.BookingService;
import com.booking.service.iface.HotelRoomService;
import com.booking.utils.BookingRequest;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "BookingController")
@RequestMapping(value = "/hotel/booking")
@Api(tags = "booking")
public class BookingController extends BaseController {

    private final BookingService bookingService;
    private HotelRoomService hotelRoomService;

    @Autowired
    public BookingController(BookingService bookingService, HotelRoomService hotelRoomService) {
        this.bookingService = bookingService;
        this.hotelRoomService = hotelRoomService;
    }

    @ApiOperation(value = "Get all rooms", response = GenericResponse.class, notes = "get_room")
    @PostMapping(value = "/")
    public List<HotelRoom> checkRooms(@RequestBody BookingRequest request) {
        return hotelRoomService.getAll();
    }
}
