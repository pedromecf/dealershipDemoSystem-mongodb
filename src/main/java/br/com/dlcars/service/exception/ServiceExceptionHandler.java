package br.com.dlcars.service.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dlcars.resource.exception.StandardError;

@ControllerAdvice
public class ServiceExceptionHandler {

	@ExceptionHandler(value = ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError stError = new StandardError(Instant.now(), status.value(), "Not found", error.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}

}
