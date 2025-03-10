package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request")
public class ValidationErrorException extends RuntimeException {

    private static final long serialVersionUID = -5493553258413800721L;
    public ValidationErrorException(String message) {
        super(message);
    }
}
