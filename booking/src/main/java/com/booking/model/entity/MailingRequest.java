package com.booking.model.entity;

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
