package com.booking.controller;

import com.booking.model.entity.MailingRequest;
import com.booking.model.entity.SubscribeRequest;
import com.booking.model.entity.Subscriber;
import com.booking.service.iface.MailSenderService;
import com.booking.service.iface.SubscriberService;
import com.booking.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @PostMapping(value = "/subscribe", produces = "application/json", consumes = "application/json")
    public ResponseEntity subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        System.err.println(subscribeRequest.getEmail());
        List<Subscriber> subscriberList = subscriberService.findAll();
        for (Subscriber subscriber : subscriberList){
            if (subscriber.getEmail().equals(subscribeRequest.getEmail())){
                return new ResponseEntity<>(new GenericResponse("Такой подписчик уже есть"),HttpStatus.BAD_REQUEST);
            }
        }
        URI uri = URI.create("http://localhost:8080/news/unsubscribe");
        URI link = UriComponentsBuilder.fromUri(uri)
                .queryParam("email", subscribeRequest.getEmail()).build().toUri();

        String message = link.toString();
        System.err.println(message);
        mailSenderService.sendEmail(subscribeRequest.getEmail(), "Subscribing", message);
        Subscriber subscriber = new Subscriber(subscribeRequest.getEmail(), true);
        subscriberService.save(subscriber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/unsubscribe")
    public ResponseEntity unsubscribe(@RequestParam("email") String email) {
        System.err.println(email);
        Subscriber subscriber = subscriberService.findByEmail(email);
        subscriberService.deleteById(subscriber.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/mailing", produces = "application/json", consumes = "application/json")
    public ResponseEntity mailing(@RequestBody MailingRequest mailingRequest) {
        mailSenderService.mailAll(mailingRequest.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}