package com.booking.controller;


import com.booking.model.entity.AuthorizationRequest;
import com.booking.model.entity.User;
import com.booking.service.iface.UserService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "AuthorizationController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/auth")
@Api(tags = "authorization")
public class AuthorizationController extends BaseController {

    private final UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity authorize(@RequestBody AuthorizationRequest authorizationRequest) {
        User user = userService.findByLoginAndPassword(authorizationRequest.getLogin(),
                authorizationRequest.getPassword());
        return new ResponseEntity<>(new GenericResponse("Success authorization"), HttpStatus.OK);
    }
}
