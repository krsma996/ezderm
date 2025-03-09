package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when operation is not permitted for current user. 
 * Mapped to 403 FORBIDDEN HTTP status.
 * 
 * @author sbocanji
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Not permitted for current user")
public class NotPermittedException extends RuntimeException {
	
	private static final long serialVersionUID = 653752521080821187L;

	/**
	 * Instantiates a new not permitted exception.
	 *
	 * @param message the message
	 */
	public NotPermittedException(final String message) {
        super(message);
    }

	/**
	 * Instantiates a new not permitted exception.
	 */
	public NotPermittedException() {
		super("Operation is not permitted for current user");
	}

}
