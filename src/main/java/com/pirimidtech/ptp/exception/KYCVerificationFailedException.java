package com.pirimidtech.ptp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class KYCVerificationFailedException extends RuntimeException {
    public KYCVerificationFailedException() {
        super();
    }

    public KYCVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public KYCVerificationFailedException(String message) {
        super(message);
    }

    public KYCVerificationFailedException(Throwable cause) {
        super(cause);
    }
}
