package br.com.dlcars.resource.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<StandardError> invalidParam(IllegalArgumentException error, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError stError = new StandardError(Instant.now(), status.value(), "Invalid parameter", error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}
	
}
