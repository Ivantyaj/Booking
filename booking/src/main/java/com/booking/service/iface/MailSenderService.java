package com.booking.service.iface;

import java.net.URI;

public interface MailSenderService {
    void sendEmail(String emailTo, String subject, String message);

    void mailAll(String message, URI uri);
}
