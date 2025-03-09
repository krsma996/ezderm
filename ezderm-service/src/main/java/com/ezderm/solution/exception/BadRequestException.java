package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when resource supposed to exist could not be found.
 *
 * For example, when searching object by its relation column and relation column does not exist itself.
 * Mapped to 400 Bad Request HTTP status.
 *
 * @author sbocanji
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Not permitted for current user")
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 8010975539490218852L;

	public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException() {
        super("Bad request");
    }

}
