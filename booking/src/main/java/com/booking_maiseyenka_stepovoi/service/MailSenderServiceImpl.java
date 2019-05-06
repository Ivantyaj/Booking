package com.booking_maiseyenka_stepovoi.service;

import com.booking_maiseyenka_stepovoi.model.entity.Subscriber;
import com.booking_maiseyenka_stepovoi.service.iface.MailSenderService;
import com.booking_maiseyenka_stepovoi.service.iface.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SubscriberService subscriberService;

    @Value("${spring.mail.username}")
    private String username;


    @Override
    public void sendEmail(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public void mailAll(String message, URI uri) {
        List<Subscriber> subscriberList = subscriberService.findAll();

        System.err.println(Arrays.toString(subscriberList.toArray()));

        for (Subscriber subscriber : subscriberList) {
            URI link = UriComponentsBuilder.fromUri(uri)
                    .queryParam("email", subscriber.getEmail()).build().toUri();
            String messageText = "Перейдите по ссылке :" + link + ", если хотите отписаться от рассылки.С уважением, команда Booking Hotel.";
            sendEmail(subscriber.getEmail(), "Mailing", message + messageText);
        }
    }


}
