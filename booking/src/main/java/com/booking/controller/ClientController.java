package com.booking.controller;

import com.booking.model.entity.Client;
import com.booking.service.iface.ClientService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "ClientController")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RequestMapping(value = "/hotel/clients")
@Api(tags = "client")
public class ClientController extends BaseController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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

        ArrayList<String> headers = new ArrayList<>();
        headers.add("one");
        headers.add("two");
        headers.add("three");
        headers.add("4");
        headers.add("5");

        ArrayList<Integer> data1 = new ArrayList<>();
        data1.add(1);
        data1.add(2);
        data1.add(5);
        data1.add(11);
        data1.add(3);

        ArrayList<Integer> data2 = new ArrayList<>();
        data2.add(5);
        data2.add(6);
        data2.add(7);
        data2.add(12);
        data2.add(9);

        ArrayList<List> toSend = new ArrayList<>();
        toSend.add(headers);
        toSend.add(data1);
        toSend.add(data2);

        return toSend;
    }

    //Пока хз по чем сделать статистику для второго графика, потом решим
    @ApiOperation(value = "Get stats2", response = GenericResponse.class, notes = "get_stats2")
    @GetMapping(value = "/statsClient2")
    @ResponseBody
    public List<List> getStats2(@RequestParam String startDate,
                               @RequestParam String endDate) {

        ArrayList<String> headers = new ArrayList<>();
        headers.add("one");
        headers.add("two");
        headers.add("three");
        headers.add("4");
        headers.add("5");

        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(12);
        data.add(9);

        ArrayList<Integer> data2 = new ArrayList<>();
        data2.add(5);
        data2.add(6);
        data2.add(12);
        data2.add(9);
        data2.add(7);

        ArrayList<List> toSend = new ArrayList<>();
        toSend.add(headers);
        toSend.add(data);
        toSend.add(data2);

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
            clientNew.setPassportNumber(client.getPassportNumber());
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

