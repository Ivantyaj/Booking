package com.booking.controller;

import com.booking.model.entity.Booking;
import com.booking.model.entity.Client;
import com.booking.model.entity.HotelRoom;
import com.booking.model.entity.HotelRoomType;
import com.booking.service.iface.BookingService;
import com.booking.service.iface.ClientService;
import com.booking.service.iface.HotelRoomService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @ApiOperation(value = "Create client", response = GenericResponse.class, notes = "client_create")
    @PostMapping(value = "/create")
    public ResponseEntity<GenericResponse> addClient(@RequestBody Client client) {
        System.err.println(client);
        clientService.save(client);
        return new ResponseEntity<>(new GenericResponse("message"), HttpStatus.OK);
    }

    @ApiOperation(value = "Get client", response = GenericResponse.class, notes = "get_client")
    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponse> getClient(@PathVariable("id") Long id) {
        Client client = clientService.getById(id);
        return new ResponseEntity<>(new GenericResponse("Client " + client), HttpStatus.OK);
    }

//    @ApiOperation(value = "Get all clients", response = GenericResponse.class, notes = "get_all_clients")
//    @GetMapping(value = "/")
//    public ResponseEntity<GenericResponse> getAllClients() {
//        List<Client> clientList = clientService.getAll();
//        return new ResponseEntity<>(new GenericResponse("ClientList " + clientList), HttpStatus.OK);
//    }

    @ApiOperation(value = "Get stats", response = GenericResponse.class, notes = "get_stats")
    @GetMapping(value = "/statsClient")
    @ResponseBody
    public List<List> getStats(@RequestParam String startDate,
                               @RequestParam String endDate) {


        List<Client> clientList = clientService.getAll();

        List<String> headers = clientList.stream().map(Client::getFio).collect(Collectors.toList());
        List<Long> data1 = clientList.stream().map(Client::getVisitingCount).collect(Collectors.toList());
//        List<Long> data2 = Collections.singletonList(clientList.stream().filter(client -> client.getDiscount() != null).count());


        ArrayList<List> toSend = new ArrayList<>();
        toSend.add(headers);
        toSend.add(data1);
//        toSend.add(data2);

        return toSend;
    }

    //Пока хз по чем сделать статистику для второго графика, потом решим
    @ApiOperation(value = "Get stats2", response = GenericResponse.class, notes = "get_stats2")
    @GetMapping(value = "/statsClient2")
    @ResponseBody
    public Set<Set> getStats2(@RequestParam String startDate,
                                @RequestParam String endDate) {


        List<HotelRoom> hotelRoomList = hotelRoomService.getAll();
        Set<String> headers = hotelRoomList.stream()
                .map(HotelRoom::getHotelRoomType)
                .map(HotelRoomType::getType_name)
                .collect(Collectors.toSet());
        List<Booking> bookingList = bookingService.getAll();


//        List<Long> data2 = bookingList.stream().map(Booking::getHumanAmount).collect(Collectors.toList());
        List<Double> data1 = hotelRoomList.stream().map(HotelRoom::getPrice).collect(Collectors.toList());


//        System.err.println(Arrays.toString(data2.toArray()));


        Set<Set> toSend = new HashSet<>();
        toSend.add(headers);
        toSend.add(data1.stream().collect(Collectors.toSet()));
//        toSend.add(data2);

        return toSend;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllClient() {
        return clientService.getAll();
    }

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

