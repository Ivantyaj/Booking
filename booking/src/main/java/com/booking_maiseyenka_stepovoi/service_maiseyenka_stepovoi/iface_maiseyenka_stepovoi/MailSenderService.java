package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi;

import java.net.URI;

public interface MailSenderService {
    void sendEmail(String emailTo, String subject, String message);

    void mailAll(String message, URI uri);
}
