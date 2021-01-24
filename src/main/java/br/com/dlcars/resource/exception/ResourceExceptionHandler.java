package br.com.dlcars.resource.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dlcars.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(value = ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError stError = new StandardError(Instant.now(), status.value(), "Not found", error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<StandardError> invalidParam(IllegalArgumentException error, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError stError = new StandardError(Instant.now(), status.value(), "Invalid parameter", error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}
	
}
