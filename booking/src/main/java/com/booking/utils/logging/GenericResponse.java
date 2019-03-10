package com.booking.utils.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericResponse {
    private Logger logger = LoggerFactory.getLogger(GenericResponse.class);

    public GenericResponse(String message) {
        logger.info(message);
    }
}
