package com.booking.controller;

import com.booking.model.entity.*;
import com.booking.service.iface.*;
import com.booking.utils.DateUtils;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private final UserService userService;

    @Autowired
    public BookingController(BookingService bookingService, HotelRoomService hotelRoomService, ClientService clientService, MailSenderService mailSenderService, UserService userService) {
        this.bookingService = bookingService;
        this.hotelRoomService = hotelRoomService;
        this.clientService = clientService;
        this.mailSenderService = mailSenderService;
        this.userService = userService;
    }

    @ApiOperation(value = "find free", response = GenericResponse.class, notes = "find_free")
    @GetMapping(value = "/searchFree")
    @ResponseBody
    public Set<HotelRoom> getFreeRooms(@RequestParam String startDate,
                                       @RequestParam String endDate,
                                       @RequestParam String clients,
                                       @RequestParam String price) throws ParseException {
        System.err.println(startDate + " " + endDate + " " + clients + " " + price);

//todo date should be in format dd.MM.yyyy
        List<Booking> bookingList = bookingService.getAll();
        List<HotelRoom> hotelRoomList = hotelRoomService.getAll();
        Set<HotelRoom> bookedRooms = bookingList.stream().map(Booking::getHotelRoom).collect(Collectors.toSet());
        List<HotelRoom> freeRooms = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        Date date = dateFormat.parse(startDate);
        for (Booking booking : bookingList) {
            if (DateUtils.compareDates(date, Date.from(booking.getLeavingDate()))) {
                freeRooms.add(booking.getHotelRoom());
            }
        }
        System.err.println(freeRooms.stream().map(HotelRoom::getId).collect(Collectors.toSet()));
        freeRooms.addAll(hotelRoomList);
        System.err.println(freeRooms.stream().map(HotelRoom::getId).collect(Collectors.toSet()));
        return freeRooms.stream()
                .filter(room -> !bookedRooms.contains(room))
                .filter(room -> room.getPeopleAmount() >= Long.parseLong(clients))
                .filter(room -> room.getPrice() <= Double.parseDouble(price))
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/booking", produces = "application/json", consumes = "application/json")
    public ResponseEntity bookingRoom(@RequestBody BookingRequest bookingRequest) throws ParseException {
        //новый клиент если такого нет
        System.err.println(bookingRequest);
        Client client = clientService.findByEmailAndPhone(bookingRequest.getClientEmail(),
                bookingRequest.getClientPhone());
        System.err.println(client);
        if (client.getVisitingCount() == null) {
            client.setVisitingCount(1L);
            client.setFio(bookingRequest.getClientName());
            User user = userService.save(new User(client.getEmail(), client.getEmail(), 2L));
            client.setUser(user);
            client.setLastMessage(bookingRequest.getMessage());
            client.setNeedCall(bookingRequest.isNeedBackCall());
            client.setCardCVV(bookingRequest.getCardCVV());
            client.setCardDate(bookingRequest.getCardData());
            client.setCardHolder(bookingRequest.getCardHolder());
            clientService.save(client);
        } else {
            client.setCardCVV(bookingRequest.getCardCVV());
            client.setCardDate(bookingRequest.getCardData());
            client.setCardHolder(bookingRequest.getCardHolder());
            clientService.save(client);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
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
