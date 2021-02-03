package com.pirimidtech.ptp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExceptionHandler extends RuntimeException{
    public ExceptionHandler() {
        super();
    }
    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
    public ExceptionHandler(String message) {
        super(message);
    }
    public ExceptionHandler(Throwable cause) {
        super(cause);
    }
}