package com.ezderm.solution.exception;

import java.nio.file.AccessDeniedException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ezderm.solution.errors.RestErrors;
import com.ezderm.solution.errors.RestSubErrors;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
@ResponseBody
public class RestExceptionHandler {

	private static Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestErrors handleWrongMethodArgument(final BadRequestException e) {
		logException(e);
		String userMessage = StringUtils.isBlank(e.getMessage())
				? "Invalid request. Please check your input and try again."
				: e.getMessage();
		return new RestErrors("Bad Request", userMessage);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrors handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		logException(e);
		return new RestErrors("Duplicate Username", "The username already exists.");
	}

	@ExceptionHandler(InvalidUsernameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<RestErrors> handleInvalidUsernameException(InvalidUsernameException e) {
	    logException(e);
	    RestErrors restErrors = new RestErrors("Invalid userName for doctor", "Please check your input and try again");
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrors);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestErrors handleDbIndexException(final DuplicateKeyException e) {
		logException(e);
		return new RestErrors("Duplicate Entry", "The resource already exists. Please provide unique data.");
	}

	@ExceptionHandler(DuplicateUsernameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrors handleDuplicateUsernameException(DuplicateUsernameException e) {
		logException(e);
		return new RestErrors("Duplicate Username", e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestErrors handleConstraintViolationException(final ConstraintViolationException e) {
		logException(e);
		RestErrors error = new RestErrors("Validation Failed", "One or more fields are invalid.");
		if (e.getConstraintViolations() != null) {
			e.getConstraintViolations().forEach(cv -> {
				error.addError(new RestSubErrors(cv.getPropertyPath().toString(),
						cv.getMessage() + ": " + cv.getInvalidValue(), null));
			});
		}
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestErrors handleMethodArgumentException(final MethodArgumentNotValidException e) {
		logException(e);
		RestErrors error = new RestErrors("Invalid Input", "Some fields in the request are invalid.");
		if (e.getFieldErrors() != null) {
			e.getFieldErrors().forEach(fe -> {
				error.addError(new RestSubErrors(fe.getField(),
						fe.getDefaultMessage() + ": " + fe.getField() + " = " + fe.getRejectedValue(), null));
			});
		}
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestErrors handleBodyNotReadable(final HttpMessageNotReadableException e) {
		logException(e);
		return new RestErrors("Invalid Request Body",
				"The request body is malformed or missing. Please check your input.");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public RestErrors handleNotPermittedException(final AccessDeniedException e) {
		LOG.info("Unauthorized, message: {}", e.getMessage());
		logException(e);
		return new RestErrors("Unauthorized", "You are not authorized to access this resource. Please log in.");
	}

	@ExceptionHandler(value = { NotPermittedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public RestErrors handleNotPermittedException(final NotPermittedException e) {
		LOG.info("Operation not permitted, message: {}", e.getMessage());
		logException(e);
		return new RestErrors("Forbidden", "You do not have permission to perform this action.");
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public RestErrors handleNotFoundException(final ResourceNotFoundException e) {
		LOG.info("Resource not found, message: {}", e.getMessage());
		logException(e);
		return new RestErrors("Resource Not Found", "The requested resource was not found.");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public RestErrors handleServerError(final Exception e) {
		LOG.error("Unhandled exception, message: {}", e.getMessage());
		LOG.error("Unhandled exception, stacktrace: ", e);
		logException(e);
		return new RestErrors("Internal Server Error", "Something went wrong on our end. Please try again later.");
	}

	private void logException(Exception e) {
		LOG.error("Exception: {}", e);
	}
}
