package com.booking.controller;

import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.BookingService;
import com.booking.service.iface.HotelRoomService;
import com.booking.utils.BookingRequest;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "BookingController")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
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

//    @ApiOperation(value = "Get all rooms", response = GenericResponse.class, notes = "get_room")
//    @PostMapping(value = "/")
//    public List<HotelRoom> checkRooms(@RequestBody BookingRequest request) {
//        System.err.println(request);
//        return hotelRoomService.getAll();
//    }


    @ApiOperation(value = "Get all asdclients", response = GenericResponse.class, notes = "get_all_casdlients")
    @GetMapping(value = "/")
    @ResponseBody
    public Map<String, String> getFoos(@RequestParam String startDate,
                                       @RequestParam String endDate,
                                       @RequestParam String clients) {
        System.err.println(startDate + endDate + clients);

            HashMap<String, String> map = new HashMap<>();
            map.put("URL1", "https://avatars.mds.yandex.net/get-pdb/989257/b9eafc85-bb75-4cc6-b720-beccb7702466/s375");
            map.put("URL2", "https://i.pinimg.com/originals/fc/ed/b7/fcedb7a0c25a7ff37a4b6eddfe9b6225.jpg");
            map.put("URL3", "https://i.pinimg.com/736x/16/f8/b7/16f8b785f80f7e30c5fc60f4a3578cc8.jpg");
            return map;
    }

    @ApiOperation(value = "find free", response = GenericResponse.class, notes = "find_free")
    @GetMapping(value = "/searchFree")
    @ResponseBody
    public Map<String, String> getFreeRooms(@RequestParam String startDate,
                                       @RequestParam String endDate,
                                       @RequestParam String clients) {
        System.err.println(startDate + endDate + clients);

        HashMap<String, String> map = new HashMap<>();
        map.put("URL1", "https://avatars.mds.yandex.net/get-pdb/989257/b9eafc85-bb75-4cc6-b720-beccb7702466/s375");
        map.put("URL2", "https://i.pinimg.com/originals/fc/ed/b7/fcedb7a0c25a7ff37a4b6eddfe9b6225.jpg");
        map.put("URL3", "https://i.pinimg.com/736x/16/f8/b7/16f8b785f80f7e30c5fc60f4a3578cc8.jpg");
        return map;
    }

}
