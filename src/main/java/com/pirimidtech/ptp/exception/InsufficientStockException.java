package com.pirimidtech.ptp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(Throwable cause) {
        super(cause);
    }
}
