package com.booking.service.iface;

public interface MailSenderService {
    void sendEmail(String emailTo, String subject, String message);

    void mailAll(String message);
}
