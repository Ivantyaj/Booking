package com.booking.controller;

import com.booking.model.entity.Client;
import com.booking.model.entity.HotelRoom;
import com.booking.service.iface.ClientService;
import com.booking.service.iface.HotelRoomService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "ClientController")
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

    @ApiOperation(value = "Get all clients", response = GenericResponse.class, notes = "get_all_clients")
    @GetMapping(value = "/")
    public ResponseEntity<GenericResponse> getAllClients() {
        List<Client> clientList = clientService.getAll();
        return new ResponseEntity<>(new GenericResponse("ClientList " + clientList), HttpStatus.OK);
    }


}

