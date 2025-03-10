package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Not permitted for current user")
public class NotPermittedException extends RuntimeException {
	
	private static final long serialVersionUID = 653752521080821187L;
	public NotPermittedException(final String message) {
        super(message);
    }
}
