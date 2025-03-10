package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Forrbiden for current user")
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 8010975539490218852L;

	public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException() {
        super("Bad request");
    }

}
