package com.pirimidtech.ptp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ErrorHandler extends RuntimeException{
    public ErrorHandler() {
    }

    public ErrorHandler(String message) {
        super(message);
    }

    public ErrorHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorHandler(Throwable cause) {
        super(cause);
    }

    public ErrorHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
