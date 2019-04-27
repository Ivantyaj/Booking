package com.booking.controller;

import com.booking.model.entity.SubscribeRequest;
import com.booking.model.entity.Subscriber;
import com.booking.service.iface.MailSenderService;
import com.booking.service.iface.SubscriberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "SubscribeController")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping(value = "/news")
@Api(tags = "booking")
public class SubscribeController extends BaseController {

    private final SubscriberService subscriberService;
    private final MailSenderService mailSenderService;

    @Autowired
    public SubscribeController(SubscriberService subscriberService, MailSenderService mailSenderService) {
        this.subscriberService = subscriberService;
        this.mailSenderService = mailSenderService;

    }

    //@PostMapping(value = "/subscribe")
    @PostMapping(value = "/subscribe", produces = "application/json", consumes = "application/json")
    public ResponseEntity subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        System.err.println(subscribeRequest.getEmail());

        String message = "message";
        mailSenderService.sendEmail(subscribeRequest.getEmail(), "Subscribing", message);
        Subscriber subscriber = new Subscriber(subscribeRequest.getEmail(), true);
        subscriberService.save(subscriber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PostMapping(value = "/unsubscribe")
    @PostMapping(value = "/unsubscribe", produces = "application/json", consumes = "application/json")
    public ResponseEntity unsubscribe(@RequestBody SubscribeRequest subscribeRequest) {
        Subscriber subscriber = subscriberService.findByEmail(subscribeRequest.getEmail());
        subscriberService.deleteByEmail(subscriber.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}