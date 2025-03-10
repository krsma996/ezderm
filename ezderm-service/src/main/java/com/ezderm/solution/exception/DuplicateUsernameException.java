package com.ezderm.solution.exception;

public class DuplicateUsernameException extends RuntimeException {

	private static final long serialVersionUID = -8511912728064078148L;

	public DuplicateUsernameException(String message) {
		super(message);
	}

}
