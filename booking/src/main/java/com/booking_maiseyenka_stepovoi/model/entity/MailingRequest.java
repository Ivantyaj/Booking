package com.booking_maiseyenka_stepovoi.model.entity;

public class MailingRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MailingRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}
