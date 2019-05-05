package com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CancelBookingRequest {
    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CancelBookingRequest{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
