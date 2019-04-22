package com.booking.controller;


import com.booking.model.entity.AuthorizationRequest;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "AuthorizationController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/auth")
@Api(tags = "booking")
public class AuthorizationController extends BaseController {

    @PostMapping("/login")
    public ResponseEntity authorize(@RequestBody AuthorizationRequest authorizationRequest) {
//add authorization here

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
