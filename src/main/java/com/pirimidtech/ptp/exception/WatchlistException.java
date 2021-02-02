package com.pirimidtech.ptp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WatchlistException extends RuntimeException{
    public WatchlistException() {
        super();
    }
    public WatchlistException(String message, Throwable cause) {
        super(message, cause);
    }
    public WatchlistException(String message) {
        super(message);
    }
    public WatchlistException(Throwable cause) {
        super(cause);
    }
}
