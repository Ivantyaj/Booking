package com.booking_maiseyenka_stepovoi.utils_maiseyenka_stepovoi.logging_maiseyenka_stepovoi;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {

    private String error;
    private String message;

    public GenericResponse() {
    }

    public GenericResponse(String message) {
        this.message = message;
    }

    public GenericResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
