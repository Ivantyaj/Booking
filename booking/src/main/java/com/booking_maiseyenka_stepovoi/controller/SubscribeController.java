package com.booking_maiseyenka_stepovoi.controller;

import com.booking_maiseyenka_stepovoi.model.entity.MailingRequest;
import com.booking_maiseyenka_stepovoi.model.entity.SubscribeRequest;
import com.booking_maiseyenka_stepovoi.model.entity.Subscriber;
import com.booking_maiseyenka_stepovoi.service.iface.MailSenderService;
import com.booking_maiseyenka_stepovoi.service.iface.SubscriberService;
import com.booking_maiseyenka_stepovoi.utils.logging.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "news")
public class SubscribeController extends BaseController {

    private final SubscriberService subscriberService;
    private final MailSenderService mailSenderService;

    @Autowired
    public SubscribeController(SubscriberService subscriberService, MailSenderService mailSenderService) {
        this.subscriberService = subscriberService;
        this.mailSenderService = mailSenderService;

    }

    @ApiOperation(value = "subscribe", response = ResponseEntity.class, notes = "subscribing")
    @PostMapping(value = "/subscribe", produces = "application/json", consumes = "application/json")
    public ResponseEntity subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        System.err.println(subscribeRequest.getEmail());
        List<Subscriber> subscriberList = subscriberService.findAll();
        for (Subscriber subscriber : subscriberList) {
            if (subscriber.getEmail().equals(subscribeRequest.getEmail())) {
                return new ResponseEntity<>(new GenericResponse("Такой подписчик уже есть"), HttpStatus.BAD_REQUEST);
            }
        }
        URI uri = URI.create("http://localhost:8080/news/unsubscribe");
        URI link = UriComponentsBuilder.fromUri(uri)
                .queryParam("email", subscribeRequest.getEmail()).build().toUri();
        String message = "Благодарим вас за подписку на обновления! Пройдите по ссылке : " +
                link.toString() + ", если хотите отписаться от рассылки!С уважением, команда Booking Hotel.";
        System.err.println(message);
        mailSenderService.sendEmail(subscribeRequest.getEmail(), "Subscribing", message);
        Subscriber subscriber = new Subscriber(subscribeRequest.getEmail(), true);
        subscriberService.save(subscriber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "unsubscribe", response = ResponseEntity.class, notes = "unsubscribing")
    @GetMapping(value = "/unsubscribe")
    public ResponseEntity unsubscribe(@RequestParam("email") String email) {
        System.err.println(email);
        Subscriber subscriber = subscriberService.findByEmail(email);
        subscriberService.deleteById(subscriber.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "mailing", response = ResponseEntity.class, notes = "mailing")
    @PostMapping(value = "/mailing", produces = "application/json", consumes = "application/json")
    public ResponseEntity mailing(@RequestBody MailingRequest mailingRequest) {
        URI uri = URI.create("http://localhost:8080/news/unsubscribe");
        mailSenderService.mailAll(mailingRequest.getMessage(),uri);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}