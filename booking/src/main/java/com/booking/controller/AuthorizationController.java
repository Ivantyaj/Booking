package com.booking.controller;


import com.booking.model.entity.AuthorizationRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "AuthorizationController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/auth")
@Api(tags = "booking")
public class AuthorizationController extends BaseController {

    //@PostMapping("/login")
    @PostMapping(value = "/login", produces = "application/json", consumes="application/json")
    public ResponseEntity authorize(@RequestBody AuthorizationRequest authorizationRequest) {
//add authorization here
        System.out.println("AUTH =>>> " + authorizationRequest);

        if(authorizationRequest.getLogin().equals("1") && authorizationRequest.getPassword().equals("1")){
            System.out.println("OK");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
