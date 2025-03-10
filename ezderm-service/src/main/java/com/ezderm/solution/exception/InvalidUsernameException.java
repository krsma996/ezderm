package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid username format")
public class InvalidUsernameException extends RuntimeException {

    private static final long serialVersionUID = -5493553258413800721L;
    public InvalidUsernameException(String message) {
        super(message);
    }
}
