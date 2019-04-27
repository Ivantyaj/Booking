package com.booking.service;

import com.booking.model.entity.Subscriber;
import com.booking.service.iface.MailSenderService;
import com.booking.service.iface.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
    public void mailAll(String message) {
        List<Subscriber> subscriberList = subscriberService.findAll();
        subscriberList.forEach(subscriber -> sendEmail(subscriber.getEmail(), "Mailing", message));
    }


}
