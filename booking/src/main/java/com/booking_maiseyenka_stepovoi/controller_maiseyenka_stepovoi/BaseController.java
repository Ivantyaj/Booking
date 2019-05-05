package com.booking_maiseyenka_stepovoi.controller_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.exceptions_maiseyenka_stepovoi.NotFoundException;
import com.booking_maiseyenka_stepovoi.utils_maiseyenka_stepovoi.logging_maiseyenka_stepovoi.GenericResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse handleException(Exception e) {
        logger.error("Внутренняя ошибка системы", e);
        return new GenericResponse("Внутренняя ошибка системы",
                "");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GenericResponse handleNotFoundException(NotFoundException e) {
        return new GenericResponse("Ошибка",
                e.getMessage());
    }


}

