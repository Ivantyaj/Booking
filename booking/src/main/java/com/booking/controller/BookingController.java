package com.booking.controller;

import com.booking.model.entity.*;
import com.booking.service.iface.BookingService;
import com.booking.service.iface.ClientService;
import com.booking.service.iface.HotelRoomService;
import com.booking.service.iface.MailSenderService;
import com.booking.utils.DateUtils;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController(value = "BookingController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/hotel/booking")
@Api(tags = "booking")
public class BookingController extends BaseController {

    private final BookingService bookingService;
    private final HotelRoomService hotelRoomService;
    private final ClientService clientService;
    private final MailSenderService mailSenderService;

    @Autowired
    public BookingController(BookingService bookingService, HotelRoomService hotelRoomService, ClientService clientService, MailSenderService mailSenderService) {
        this.bookingService = bookingService;
        this.hotelRoomService = hotelRoomService;
        this.clientService = clientService;
        this.mailSenderService = mailSenderService;
    }

    @ApiOperation(value = "find free", response = GenericResponse.class, notes = "find_free")
    @GetMapping(value = "/searchFree")
    @ResponseBody
    public List<HotelRoom> getFreeRooms(@RequestParam String startDate,
                                        @RequestParam String endDate,
                                        @RequestParam String clients,
                                        @RequestParam String price) throws ParseException {
        System.err.println(startDate + " " + endDate + " " + clients + " " + price);

//todo date should be in format dd.MM.yyyy
        List<Booking> bookingList = bookingService.getAll();
        List<HotelRoom> hotelRoomList = hotelRoomService.getAll();
        List<HotelRoom> bookedRooms = bookingList.stream().map(Booking::getHotelRoom).collect(Collectors.toList());
        List<HotelRoom> freeRooms = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        Date date = dateFormat.parse(startDate);
        for (Booking booking : bookingList) {
            if (DateUtils.compareDates(date, Date.from(booking.getLeavingDate()))) {
                freeRooms.add(booking.getHotelRoom());
            }
        }
        freeRooms.addAll(hotelRoomList.stream()
                .filter(room -> !bookedRooms.contains(room))
                .filter(room -> room.getPrice() <= Double.parseDouble(price))
                .filter(room -> room.getPeopleAmount() >= Long.parseLong(clients))
                .collect(Collectors.toList())
        );
        System.err.println(freeRooms.size());
        System.err.println(freeRooms.stream().map(HotelRoom::getId).collect(Collectors.toList()));
        return freeRooms;
    }

    @PostMapping(value = "/booking", produces = "application/json", consumes = "application/json")
    public ResponseEntity bookingRoom(@RequestBody BookingRequest bookingRequest) throws ParseException {
        //новый клиент если такого нет
        Client client = clientService.findByEmailAndPhone(bookingRequest.getClientEmail(),
                bookingRequest.getClientPhone());
        if (!StringUtils.isEmpty(client.getEmail())
                || !StringUtils.isEmpty(client.getPhone())
                || StringUtils.isEmpty(client.getCardHolder())
                || StringUtils.isEmpty(client.getCardCVV())
                || StringUtils.isEmpty(client.getCardDate())
                || StringUtils.isEmpty(client.getFio())) {
            clientService.save(client);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        Date dateFrom = dateFormat.parse(bookingRequest.getDataFrom());
        Date dateTo = dateFormat.parse(bookingRequest.getDataTo());
        HotelRoom hotelRoom = hotelRoomService.findById(bookingRequest.getRoomId());
        Booking booking = new Booking(bookingRequest.getRoomId(),
                client,
                dateFrom.toInstant(),
                dateTo.toInstant(),
                hotelRoom.getPeopleAmount());
        System.err.println(client);
        Booking saved = bookingService.save(booking);
        //ссылка на подтверждение бронирования
        URI uri = URI.create("http://localhost:8080/hotel/booking/confirm");
        URI link = UriComponentsBuilder.fromUri(uri)
                .queryParam("id", saved.getId()).build().toUri();
        mailSenderService.sendEmail(booking.getClient().getEmail(), "Confirm booking", "Перейдите по ссылке, для подтверждения бронирования :" + link);

//        String message = "Благодарим вас за успешное бронирование номера. Информация о бронировании :\n" + bookingRequest + "\n С уважением, команда Booking Hotel.";
//        mailSenderService.sendEmail(client.getEmail(), "Booked Room", message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/confirm")
    public ResponseEntity confirmBooking(@RequestParam("id") Long id) {
        Booking booking = bookingService.findById(id);
        booking.setStatus(true);
        bookingService.save(booking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/cancel", produces = "application/json", consumes = "application/json")
    public ResponseEntity cancelBooking(@RequestBody CancelBookingRequest request) {
        Booking booking = bookingService.findById(request.getId());
        bookingService.deleteById(request.getId());
        mailSenderService.sendEmail(booking.getClient().getEmail(), "Cancel booking", request.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getAllBooking() {
        return bookingService.getAll();
    }
    //post  : id_booking , message(cause) - когда приходит удалить из бд бронь и прислать клиенту сообщение
}
