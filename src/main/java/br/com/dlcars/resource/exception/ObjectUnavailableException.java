package br.com.dlcars.resource.exception;

public class ObjectUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectUnavailableException(String msg) {
		super(msg);
	}

}
