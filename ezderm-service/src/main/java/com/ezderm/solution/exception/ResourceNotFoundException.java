package com.ezderm.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown in case when resource could not be found.
 *
 * @author sbocanji
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource could not be found")
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3381250967767715304L;

    public ResourceNotFoundException() {
        super("Resource could not be found");
    }

    public ResourceNotFoundException(String msg) {

        super(msg);

    }

}
