package com.booking_maiseyenka_stepovoi.controller;

import com.booking_maiseyenka_stepovoi.model.entity.Booking;
import com.booking_maiseyenka_stepovoi.model.entity.Client;
import com.booking_maiseyenka_stepovoi.model.entity.HotelRoom;
import com.booking_maiseyenka_stepovoi.service.iface.BookingService;
import com.booking_maiseyenka_stepovoi.service.iface.ClientService;
import com.booking_maiseyenka_stepovoi.service.iface.HotelRoomService;
import com.booking_maiseyenka_stepovoi.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController(value = "ClientController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/hotel/clients")
@Api(tags = "client")
public class ClientController extends BaseController {

    private final ClientService clientService;
    private final HotelRoomService hotelRoomService;
    private final BookingService bookingService;

    @Autowired
    public ClientController(ClientService clientService, HotelRoomService hotelRoomService, BookingService bookingService) {
        this.clientService = clientService;
        this.hotelRoomService = hotelRoomService;
        this.bookingService = bookingService;
    }

    @ApiOperation(value = "Create client", response = ResponseEntity.class, notes = "client_create")
    @PostMapping(value = "/create")
    public ResponseEntity<GenericResponse> addClient(@RequestBody Client client) {
        System.err.println(client);
        clientService.save(client);
        return new ResponseEntity<>(new GenericResponse("message"), HttpStatus.OK);
    }

    @ApiOperation(value = "Get client", response = ResponseEntity.class, notes = "get_client")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponse> getClient(@PathVariable("id") Long id) {
        Client client = clientService.getById(id);
        return new ResponseEntity<>(new GenericResponse("Client " + client), HttpStatus.OK);
    }

    @ApiOperation(value = "Get stats", response = List.class, notes = "get_stats")
    @GetMapping(value = "/statsClient")
    @ResponseBody
    public List<List> getStats(@RequestParam String startDate,
                               @RequestParam String endDate) {


        List<HotelRoom> hotelRoomList = hotelRoomService.getAll();
        List<Double> data1 = hotelRoomList.stream().map(HotelRoom::getPrice).collect(Collectors.toList());
        Set<String> headers = hotelRoomList.stream().map(room -> room.getHotelRoomType().getType_name()).collect(Collectors.toSet());
//        List<Long> data1 = clientList.stream().map(Client::getVisitingCount).collect(Collectors.toList());
//        List<Long> data2 = Collections.singletonList(clientList.stream().filter(client -> client.getDiscount() != null).count());


        ArrayList<List> toSend = new ArrayList<>();
        toSend.add(new ArrayList<>(headers));
        toSend.add(data1);
//        toSend.add(data2);

        return toSend;
    }

    @ApiOperation(value = "Get stats2", response = List.class, notes = "get_stats2")
    @GetMapping(value = "/statsClient2")
    @ResponseBody
    public List<List> getStats2(@RequestParam String startDate,
                                @RequestParam String endDate) {

        List<Booking> bookingList = bookingService.getAll();
        List<Date> dates = bookingList.stream()
                .map(booking -> Date.from(booking.getArrivalDate()))
                .sorted().collect(Collectors.toList());

        //dates.forEach(System.err::println);

        SimpleDateFormat dateFormat = new SimpleDateFormat("Mmm");

        List<String> monthes = Arrays.asList("Янв.", "Фев.", "Мар.", "Апр.", "Май.", "Июн.", "Июл.", "Авг.", "Сен.", "Окт.", "Ноя.", "Дек.");

        List<Integer> monthesNum = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        List<Long> counts = new LinkedList<>();

        for (Integer month : monthesNum) {
            counts.add(dates.stream().map(date -> date.getMonth() == month).filter(Boolean::booleanValue).count());
        }
        System.out.println("count\n" + counts);

        List<List> toSend = new ArrayList<>();
        toSend.add(monthes);

        toSend.add(counts);

        return toSend;
    }

    @ApiOperation(value = "get all clients", response = ResponseEntity.class, notes = "get_all_clients")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllClient() {
        return clientService.getAll();
    }

    @ApiOperation(value = "updating client", response = ResponseEntity.class, notes = "update_client")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        System.out.println("client  Hello update");
        if (client.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            Client clientNew = clientService.getById(client.getId());
            clientNew.setUser(client.getUser());
            clientNew.setFio(client.getFio());
            clientNew.setPhone(client.getPhone());
            clientNew.setEmail(client.getEmail());
            clientNew.setVisitingCount(client.getVisitingCount());
            clientNew.setDiscount(client.getDiscount());
            clientNew.setNeedCall(client.isNeedCall());
            clientNew.setLastMessage(client.getLastMessage());

            clientService.save(clientNew);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

