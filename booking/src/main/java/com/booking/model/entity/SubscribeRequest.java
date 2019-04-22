package com.booking.model.entity;

public class SubscribeRequest {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
