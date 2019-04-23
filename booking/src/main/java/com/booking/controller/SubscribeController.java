package com.booking.controller;

import com.booking.model.entity.SubscribeRequest;
import com.booking.model.entity.Subscriber;
import com.booking.service.iface.SubscriberService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "SubscribeController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/news")
@Api(tags = "booking")
public class SubscribeController extends BaseController {

    private final SubscriberService subscriberService;

    public SubscribeController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping(value = "/subscribe")
    public ResponseEntity subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        Subscriber subscriber = new Subscriber(subscribeRequest.getEmail(), true);
        subscriberService.save(subscriber);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/unsubscribe")
    public ResponseEntity unsubscribe(@RequestBody SubscribeRequest subscribeRequest) {
        Subscriber subscriber = subscriberService.findByEmail(subscribeRequest.getEmail());
        subscriberService.deleteByEmail(subscriber.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}